package com.daily.cost.desensitive;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解--脱敏
 */
@JacksonAnnotationsInside
// 指定序列化器
@JsonSerialize(using = DeSensitiveProcessor.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DeSensitive {
    int prefix() default 0; // 头部不打码长度

    int suffix() default 0; // 尾部不打码长度

    char mask() default '*'; // 遮掩字符
}

