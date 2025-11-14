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
 * 借贷记录表
 * </p>
 *
 * @author lichanghao
 * @since 2025-11-14
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_loan_record")
public class LoanRecord extends Model<LoanRecord> {

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id(t_user.id)
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 类型（1-借入/2-借出）
     */
    @TableField("type")
    private Short type;

    /**
     * 金额
     */
    @TableField("amount")
    private BigDecimal amount;

    /**
     * 对方名称
     */
    @TableField("target_name")
    private String targetName;

    /**
     * 状态（1-未还/2-已还/3-部分还）
     */
    @TableField("status")
    private Short status;

    /**
     * 到期日期
     */
    @TableField("due_date")
    private Date dueDate;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

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
