package com.daily.cost.feign.req;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Schema(description = "Excel表头")
public class ExcelHeaderReq {

    @Schema(description = "字段的名称，例如：name")
    private String fieldName;

    @Schema(description = "字段在excel中的注释,例如：姓名")
    private String fieldText;

    @Schema(description = "字段在excel中的排序，越小，越在前面")
    private Integer fieldSort;

    public static ExcelHeaderReq create(ExcelHeaderReq req) {
        return new ExcelHeaderReq()
                .setFieldName(req.getFieldName())
                .setFieldText(req.getFieldText());
    }
}
