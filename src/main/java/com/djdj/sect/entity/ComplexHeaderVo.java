package com.djdj.sect.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author lichanghao
 * @date 2025/1/22
 * @Desc
 */
@Accessors(chain = true)
@Data
public class ComplexHeaderVo {

    /**字段的名称，例如：name*/
    private String fieldName;

    /**字段在excel中的注释,例如：姓名*/
    private String fieldText;

    /**复杂表头的子集*/
    private List<ComplexHeaderVo> children;
}
