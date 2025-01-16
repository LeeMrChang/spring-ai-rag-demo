package com.djdj.sect.config;

import com.djdj.sect.controller.resp.Result;
import com.djdj.sect.utils.BizException;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.stream.Collectors;


/**
 * 统一异常处理类
 */
@RestControllerAdvice("com.djdj.sect.controller")
public class GlobalExceptionConfig {

    /**
     * 参数格式错误异常处理器
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<Void> exceptionHandler(HttpMessageNotReadableException ex) {
        return Result.fail("参数格式错误");
    }

    /**
     * 参数缺失异常处理器
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<Void> exceptionHandler(MissingServletRequestParameterException ex) {
        return Result.fail(ex.getParameterName() + "是必传的");
    }

    /**
     * 接口参数校验异常处理器
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> exceptionHandler(MethodArgumentNotValidException ex) {
        String msg = ex.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining("；"));
        return Result.fail(msg);
    }

    /**
     * 接口参数校验异常处理器
     */
    @ExceptionHandler(HandlerMethodValidationException.class)
    public Result<Void> exceptionHandler(HandlerMethodValidationException ex) {
        String msg = ex.getAllErrors().stream().map(MessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("；"));
        return Result.fail(msg);
    }

    /**
     * 方法参数校验异常处理器
     */
    @ExceptionHandler(MethodValidationException.class)
    public Result<Void> exceptionHandler(MethodValidationException ex) {
        String msg = ex.getAllErrors().stream().map(MessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("；"));
        return Result.fail(msg);
    }

    /**
     * 业务异常处理器
     */
    @ExceptionHandler(BizException.class)
    public Result<Void> exceptionHandler(BizException ex) {
        return Result.of(ex.getCode(), ex.getMsg());
    }

    /**
     * 系统异常处理器
     */
    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public Result<Void> exceptionHandler(Throwable ex) {
        return Result.fail(ex.getMessage());
    }

}
