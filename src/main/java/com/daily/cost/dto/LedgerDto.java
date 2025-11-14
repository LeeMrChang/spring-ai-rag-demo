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
 * 账本表 DTO
 * </p>
 *
 * @author lichanghao
 * @since 2025-11-14
 */
@Getter
@Setter
@Accessors(chain = true)
@Schema(description = "账本表 DTO")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LedgerDto {

    @Schema(description = "主键id")
    private Long id;

    @Schema(description = "用户id(t_user.id)")
    private Long userId;

    @Schema(description = "图标id(t_icon.id)")
    private Long iconId;

    @Schema(description = "账本名称")
    private String name;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "是否默认账本：0-否；1-是")
    private Boolean isDefault;

    @Schema(description = "创建人")
    private Long createId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新人")
    private Long updateId;

    @Schema(description = "更新时间")
    private Date updateTime;
}