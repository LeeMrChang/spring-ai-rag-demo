package com.djdj.sect.aspect.anno;

import java.lang.annotation.*;

/**
 * 防止同个用户重复请求校验注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepetitionValid {

    String keyParam() default "";

}
