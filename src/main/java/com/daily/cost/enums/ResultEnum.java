package com.daily.cost.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应枚举
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {

    SUCCESS(1, "成功"),
    FAILURE(0, "失败"),
    LOGOUT(2, "退出到登录页面"),

    ;

    private final Integer code;
    private final String desc;

}
