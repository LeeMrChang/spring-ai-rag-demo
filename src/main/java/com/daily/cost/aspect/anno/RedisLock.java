package com.daily.cost.aspect.anno;

import java.lang.annotation.*;

/**
 * redis分布式锁
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisLock {

    String redisKey();

    String[] keyParams() default {};

}
