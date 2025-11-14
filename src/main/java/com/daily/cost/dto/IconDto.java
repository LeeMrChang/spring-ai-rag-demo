package com.daily.cost.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 图标表 DTO
 * </p>
 *
 * @author lichanghao
 * @since 2025-11-14
 */
@Getter
@Setter
@Accessors(chain = true)
@Schema(description = "图标表 DTO")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class IconDto {

    @Schema(description = "主键id")
    private Long id;

    @Schema(description = "图标名称")
    private String name;

    @Schema(description = "icon链接")
    private String url;

    @Schema(description = "类型：1-收入/2-支出")
    private Boolean type;

    @Schema(description = "父级图标id（0表示无父级）")
    private Long parentId;

    @Schema(description = "子图标名称")
    private String subIconName;

    @Schema(description = "排序顺序")
    private Integer sortOrder;

    @Schema(description = "创建人")
    private Long createId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新人")
    private Long updateId;

    @Schema(description = "更新时间")
    private Date updateTime;
}