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
 * 资产快照表
 * </p>
 *
 * @author lichanghao
 * @since 2025-11-14
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_asset_snapshot")
public class AssetSnapshot extends Model<AssetSnapshot> {

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
     * 账户名称（支付宝/微信/银行卡等）
     */
    @TableField("name")
    private String name;

    /**
     * 账户类型（1-现金/2-银行卡/3-信用卡/4-投资）
     */
    @TableField("type")
    private Boolean type;

    /**
     * 当前余额
     */
    @TableField("balance")
    private BigDecimal balance;

    /**
     * 是否隐藏，0表示不隐藏，1表示隐藏
     */
    @TableField("is_hidden")
    private Boolean isHidden;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 银行id
     */
    @TableField("bank_id")
    private Long bankId;

    /**
     * 银行名称
     */
    @TableField("bank_name")
    private String bankName;

    /**
     * 银行支行
     */
    @TableField("bank_subbranch")
    private String bankSubbranch;

    /**
     * 银行卡号
     */
    @TableField("bank_no")
    private String bankNo;

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
