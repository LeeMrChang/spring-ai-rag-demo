package com.daily.cost.controller.resp;

import com.daily.cost.constant.SectConstant;
import com.daily.cost.enums.ResultEnum;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.MDC;

@Schema(description = "响应结果统一包装类")
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Result<T> {

    @Schema(description = "响应状态码")
    private Integer code;

    @Schema(description = "响应描述")
    private String msg;

    @Schema(description = "响应数据")
    @Setter
    private T data;

    @Schema(description = "附加数据")
    @Setter
    private Object additional;

    @Schema(description = "追溯标识")
    private final String traceId = MDC.get(SectConstant.TRACE_ID);

    private Result() {}

    private Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(Integer code, String msg, T data, Object additional) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.additional = additional;
    }

    /**
     * 业务成功
     * @return 响应结果
     * @param <T> 响应数据类型
     */
    public static <T> Result<T> success() {
        return new Result<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getDesc());
    }

    /**
     * 业务成功
     * @param msg 响应描述
     * @return 响应结果
     * @param <T> 响应数据类型
     */
    public static <T> Result<T> success(String msg) {
        return new Result<>(ResultEnum.SUCCESS.getCode(), msg);
    }

    /**
     * 业务成功
     * @param data 响应数据
     * @return 响应结果
     * @param <T> 响应数据类型
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getDesc(), data);
    }

    /**
     * 业务失败
     * @param msg 响应描述
     * @return 响应结果
     * @param <T> 响应数据类型
     */
    public static <T> Result<T> fail(String msg) {
        return new Result<>(ResultEnum.FAILURE.getCode(), msg);
    }

    /**
     * 构建结果
     * @param code 响应编码
     * @param msg 响应描述
     * @return 响应结果
     * @param <T> 响应数据类型
     */
    public static <T> Result<T> of(Integer code, String msg) {
        return new Result<>(code, msg);
    }

    /**
     * 添加附加数据
     *
     * @param data       响应数据
     * @param additional 附加数据
     * @param <T>        响应数据类型
     * @return 响应结果
     */
    public static <T> Result<T> successWithAdditional(T data, Object additional) {
        return new Result<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getDesc(), data, additional);
    }
}
