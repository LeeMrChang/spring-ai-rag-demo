package com.djdj.sect.rag.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class OllamaConfig {

    @Bean
    public RestClient.Builder restClientBuilder() {
        // 配置带超时的 HttpClient
        SimpleClientHttpRequestFactory factory =
                new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(10_000);   // 连接超时 10秒
        factory.setReadTimeout(300_000);     // 读取超时 5分钟
        return RestClient.builder()
                .requestFactory(factory);
    }
}
