package com.daily.cost.config;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.text.CharSequenceUtil;
import com.djdj.common.api.filter.DJApiFilter;
import com.djdj.common.api.filter.DefaultFilter;
import com.daily.cost.constant.SectConstant;
import com.daily.cost.utils.AspectUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.Filter;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Resource
    private ObjectMapper objectMapper;
    @Value("${djdj.aes.key:#{null}}")
    private String key;

    @Bean
    public FilterRegistrationBean<Filter> dJApiFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();

        if (CharSequenceUtil.isNotBlank(key)) {
            registrationBean.setFilter(new DJApiFilter(objectMapper, key));
            registrationBean.addUrlPatterns("/statistic/*", "/common/*"); // 过滤统计，公共下拉
            registrationBean.setOrder(1); // 设置过滤器顺序
        } else {
            registrationBean.setFilter(new DefaultFilter());
            registrationBean.addUrlPatterns("/*");
            registrationBean.setOrder(1);
        }

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<Filter> traceFilter() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter((request, response, chain) -> {
            MDC.put(SectConstant.TRACE_ID, UUID.fastUUID().toString(true));
            MDC.put(SectConstant.IP, AspectUtil.getRequestIp());
            try {
                chain.doFilter(request, response);
            } finally {
                MDC.remove(SectConstant.TRACE_ID);
                MDC.remove(SectConstant.IP);
            }
        });
        registration.addUrlPatterns("/*");
        registration.setOrder(0);
        return registration;
    }

}
