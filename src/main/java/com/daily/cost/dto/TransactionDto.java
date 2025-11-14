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
 * 账单表 DTO
 * </p>
 *
 * @author lichanghao
 * @since 2025-11-14
 */
@Getter
@Setter
@Accessors(chain = true)
@Schema(description = "账单表 DTO")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransactionDto {

    @Schema(description = "主键id")
    private Long id;

    @Schema(description = "账本id(t_ledger.id)")
    private Long ledgerId;

    @Schema(description = "用户id(t_user.id)")
    private Long userId;

    @Schema(description = "分类id（t_categorie.id）")
    private Long categoryId;

    @Schema(description = "类型（1-收入/2-支出/3-转账/4-借入/5-借出）")
    private Boolean type;

    @Schema(description = "金额")
    private BigDecimal amount;

    @Schema(description = "支付方式（1-支付宝/2-微信/3-现金等）")
    private Boolean paymentMethod;

    @Schema(description = "记录日期（含时分秒）")
    private Date transactionDate;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "报销状态（0-无报销,1-待报销,2-已报销,3-报销入账）")
    private Boolean reimburseStatus;

    @Schema(description = "报销关联id（关联原始报销记录）")
    private Long reimburseRelation;

    @Schema(description = "创建人")
    private Long createId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新人")
    private Long updateId;

    @Schema(description = "更新时间")
    private Date updateTime;
}