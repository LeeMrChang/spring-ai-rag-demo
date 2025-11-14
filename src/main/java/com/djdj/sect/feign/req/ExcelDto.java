package com.djdj.sect.feign.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author lichanghao
 * @date 2024/12/9
 * @Desc
 */
@Data
public class ExcelDto {

    @Schema(description = "字段的名称，例如：name")
    private String fieldName;

    @Schema(description = "字段在excel中的注释,例如：姓名")
    private List<String> fieldText;
}
