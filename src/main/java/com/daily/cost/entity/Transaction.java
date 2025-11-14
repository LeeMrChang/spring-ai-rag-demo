package com.daily.cost.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 账单表
 * </p>
 *
 * @author lichanghao
 * @since 2025-11-14
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_transaction")
public class Transaction extends Model<Transaction> {

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 账本id(t_ledger.id)
     */
    @TableField("ledger_id")
    private Long ledgerId;

    /**
     * 用户id(t_user.id)
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 分类id（t_categorie.id）
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 类型（1-收入/2-支出/3-转账/4-借入/5-借出）
     */
    @TableField("type")
    private Boolean type;

    /**
     * 金额
     */
    @TableField("amount")
    private BigDecimal amount;

    /**
     * 支付方式（1-支付宝/2-微信/3-现金等）
     */
    @TableField("payment_method")
    private Boolean paymentMethod;

    /**
     * 记录日期（含时分秒）
     */
    @TableField("transaction_date")
    private Date transactionDate;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 报销状态（0-无报销,1-待报销,2-已报销,3-报销入账）
     */
    @TableField("reimburse_status")
    private Boolean reimburseStatus;

    /**
     * 报销关联id（关联原始报销记录）
     */
    @TableField("reimburse_relation")
    private Long reimburseRelation;

    /**
     * 创建人
     */
    @TableField(value = "create_id", fill = FieldFill.INSERT)
    private Long createId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新人
     */
    @TableField(value = "update_id", fill = FieldFill.INSERT_UPDATE)
    private Long updateId;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
