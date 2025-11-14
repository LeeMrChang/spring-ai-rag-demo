package com.daily.cost.feign.common;

import cn.hutool.core.text.CharSequenceUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Target;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RpcRequestInterceptor implements RequestInterceptor {

    @Value("${djdj.domain}")
    private String domain;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Target<?> target = requestTemplate.feignTarget();
        // 修改请求域名
        String server = target.name();
        String host = CharSequenceUtil.isNotBlank(domain)
                ? domain + "/" + server // 外网地址
                : "http://" + server + ".default.svc.cluster.local:8000"; // k8s内网地址
        requestTemplate.target(host);
    }
}

