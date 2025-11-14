//package com.djdj.sect.config;
//
//import jakarta.annotation.Resource;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.validation.Validator;
//import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * 拦截器配置类
// */
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Resource
//    private UserInterceptor userInterceptor;
//
//    /**
//     * 注册拦截器
//     *
//     * @param registry registry
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(userInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/error", "/html/*", "/v3/api-docs/**", "/rpc/**", "/swagger-ui/**");
//    }
//
//    @Override
//    public Validator getValidator() {
//        return new LocalValidatorFactoryBean();
//    }
//
//}
