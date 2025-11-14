package com.daily.cost.config;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.daily.cost.constant.RedisConstant;
import feign.Logger;
import feign.RequestInterceptor;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.MDC;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;

/**
 * 公共配置类
 */
@Configuration
public class CommonConfig {

    /**
     * 注册分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 如果配置多个插件,切记分页最后添加
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.POSTGRE_SQL));
        return interceptor;
    }

    /**
     * 通用配置，如需按缓存名进行定制化配置，通过注入RedisCacheManagerBuilderCustomizer来完成
     *
     * @return RedisCacheConfiguration
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .prefixCacheNameWith("CommonCache:")
                .entryTtl(Duration.ofSeconds(RedisConstant.SEC_60))
                .disableCachingNullValues()
                .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        Jackson2JsonRedisSerializer<String> stringJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(String.class);
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(stringJackson2JsonRedisSerializer);
        template.setHashKeySerializer(stringJackson2JsonRedisSerializer);
        template.setValueSerializer(genericJackson2JsonRedisSerializer);
        template.setHashValueSerializer(genericJackson2JsonRedisSerializer);
        return template;
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RedissonClient redissonClient(RedisProperties properties) {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + properties.getHost() + ":" + properties.getPort())
                .setDatabase(properties.getDatabase())
                .setPassword(properties.getPassword());
        return Redisson.create(config);
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            Map<String, String> contextMap = MDC.getCopyOfContextMap();
            if (MapUtil.isNotEmpty(contextMap)) {
                contextMap.forEach((k, v) -> template.header(k, URLEncoder.encode(v, StandardCharsets.UTF_8)));
            }
        };
    }

    @Bean
    public MethodValidationPostProcessor validationPostProcessor() {
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        processor.setAdaptConstraintViolations(true);
        return processor;
    }

    @Bean
    public okhttp3.OkHttpClient.Builder okHttpClientBuilder() {
        return new okhttp3.OkHttpClient.Builder()
                .retryOnConnectionFailure(false)
                .writeTimeout(Duration.ofSeconds(5))
                .callTimeout(Duration.ofSeconds(300));
    }

}
