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
 * 分类表 DTO
 * </p>
 *
 * @author lichanghao
 * @since 2025-11-14
 */
@Getter
@Setter
@Accessors(chain = true)
@Schema(description = "分类表 DTO")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CategorizeDto {

    @Schema(description = "主键id")
    private Long id;

    @Schema(description = "用户id(t_user.id)")
    private Long userId;

    @Schema(description = "图标id(t_icon.id)")
    private Long iconId;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "类型（1-收入/2-支出/3-转账）")
    private Boolean type;

    @Schema(description = "父级分类id")
    private Long parentId;

    @Schema(description = "是否系统预设，0表示否，1表示是")
    private Boolean isSystem;

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