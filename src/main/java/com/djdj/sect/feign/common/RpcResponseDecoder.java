package com.djdj.sect.feign.common;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.djdj.sect.utils.BizException;
import feign.FeignException;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;

import java.io.IOException;
import java.lang.reflect.Type;

@Slf4j
public class RpcResponseDecoder extends SpringDecoder {

    public RpcResponseDecoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        super(messageConverters);
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {
        if (response.status() != 200) {
            return super.decode(response, type);
        }
        String responseBody = new String(response.body().asInputStream().readAllBytes());
        JSONObject responseData = JSONUtil.parseObj(responseBody);
        if (responseData.getInt("code") != 1) {
            throw new BizException(responseData.getInt("code"), responseData.getStr("msg"));
        }
        Response modifiedResponse = response.toBuilder().body(responseData.getJSONObject("data").toString().getBytes()).build();
        return super.decode(modifiedResponse, type);
    }
}