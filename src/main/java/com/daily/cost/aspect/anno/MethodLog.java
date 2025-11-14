package com.daily.cost.aspect.anno;

import java.lang.annotation.*;

/**
 * 记录方法执行注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodLog {

    /**
     * methodDesc 方法描述
     * @return
     */
    String value();

    /**
     * 记录场景
     * @return
     */
    Scene scene() default Scene.ALL;

    /**
     * 记录方式
     * @return
     */
    Mode mode() default Mode.DATABASE;

    enum Scene {
        ALL,// 所有场景
        SUCCESS,// 方法成功时记录
        FAILURE,// 方法失败时记录
    }

    enum Mode {
        DATABASE,// 记录到数据库表
        CONSOLE,// 打印到控制台
    }

}
