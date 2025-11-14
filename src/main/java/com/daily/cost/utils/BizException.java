package com.daily.cost.utils;

import cn.hutool.core.text.CharSequenceUtil;
import com.daily.cost.enums.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义业务异常
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BizException extends RuntimeException {

    private final Integer code;
    private final String msg;

    public BizException(String msg, Object... arg) {
        super(CharSequenceUtil.format(msg, arg));
        this.code = 0;
        this.msg = getMessage();
    }

    public BizException(ResultEnum resultEnum, String msg, Object... arg) {
        super(CharSequenceUtil.format(msg, arg));
        this.code = resultEnum.getCode();
        this.msg = getMessage();
    }

    public BizException(Integer code, String msg, Object... arg) {
        super(CharSequenceUtil.format(msg, arg));
        this.code = code;
        this.msg = getMessage();
    }

    public BizException(Throwable t, Integer code, String msg, Object... arg) {
        super(CharSequenceUtil.format(msg, arg), t);
        this.code = code;
        this.msg = getMessage();
    }

    public BizException(Throwable t) {
        super(t);
        this.code = 0;
        this.msg = getMessage();
    }

    public BizException(Throwable t, String msg, Object... arg) {
        super(CharSequenceUtil.format(msg, arg), t);
        this.code = 0;
        this.msg = getMessage();
    }

}
