package com.djdj.sect;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.djdj.sect.mapper")
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "spring项目脚手架", description = "spring项目脚手架", version = "v1"))
@EnableTransactionManagement
@EnableCaching
public class SpringAIApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAIApplication.class, args);
    }
}
