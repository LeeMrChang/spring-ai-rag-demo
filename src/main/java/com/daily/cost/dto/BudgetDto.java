package com.daily.cost.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 预算表 DTO
 * </p>
 *
 * @author lichanghao
 * @since 2025-11-14
 */
@Getter
@Setter
@Accessors(chain = true)
@Schema(description = "预算表 DTO")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BudgetDto {

    @Schema(description = "主键id")
    private Long id;

    @Schema(description = "用户id(t_user.id)")
    private Long userId;

    @Schema(description = "账本id(t_ledger.id)")
    private Long ledgerId;

    @Schema(description = "分类id（t_categorie.id）")
    private Long categoryId;

    @Schema(description = "预算金额")
    private BigDecimal amount;

    @Schema(description = "预算月份")
    private String budgetMonth;

    @Schema(description = "是否滚存到下月")
    private Boolean rolloverFlag;

    @Schema(description = "创建人")
    private Long createId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新人")
    private Long updateId;

    @Schema(description = "更新时间")
    private Date updateTime;
}