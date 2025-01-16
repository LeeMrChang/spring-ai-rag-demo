package com.djdj.sect.feign.common;

import cn.hutool.core.convert.Convert;
import com.djdj.sect.feign.resp.BaseResp;
import com.djdj.sect.utils.BizException;
import feign.Response;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import feign.optionals.OptionalDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RpcErrorDecoder extends ErrorDecoder.Default {

    public RpcErrorDecoder(ObjectFactory<HttpMessageConverters> messageConverters, ObjectProvider<HttpMessageConverterCustomizer> customizers) {
        decoder = new OptionalDecoder(new ResponseEntityDecoder(new SpringDecoder(messageConverters, customizers)));
    }

    private final Decoder decoder;

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            Object decode = decoder.decode(response, BaseResp.class);
            BaseResp<?> baseResp = Convert.convert(BaseResp.class, decode);
            return new BizException(baseResp.getCode(), baseResp.getMsg());
        } catch (Exception e) {
            return super.decode(methodKey, response);
        }
    }

}
