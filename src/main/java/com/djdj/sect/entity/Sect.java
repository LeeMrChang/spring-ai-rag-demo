package com.djdj.sect.entity;

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
 * 门派主表
 * </p>
 *
 * @author djdj
 * @since 2024-04-09
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_sect")
public class Sect extends Model<Sect> {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 门派名称
     */
    @TableField("name")
    private String name;

    /**
     * 门派头像
     */
    @TableField("profile")
    private String profile;

    /**
     * 门派等级
     */
    @TableField("grade")
    private String grade;

    /**
     * 门派宣言
     */
    @TableField("declaration")
    private String declaration;

    /**
     * 城市类型（1-A类，2-B类）
     */
    @TableField("city_type")
    private Integer cityType;

    /**
     * 城市ID
     */
    @TableField("city_id")
    private Long cityId;

    /**
     * 运营中心ID
     */
    @TableField("merchant_id")
    private Long merchantId;

    /**
     * 状态（0-隐藏，1-正常）
     */
    @TableField("status")
    private Boolean status;

    /**
     * 当期贡献值
     */
    @TableField("contribution_value")
    private Integer contributionValue;

    /**
     * 经费
     */
    @TableField("fund")
    private BigDecimal fund;

    /**
     * 荣耀分
     */
    @TableField("glory_score")
    private BigDecimal gloryScore;

    /**
     * 累计公益积分
     */
    @TableField("welfare_score")
    private BigDecimal welfareScore;

    /**
     * 是否测试门派（1-是，0-否）
     */
    @TableField("test_flag")
    private Boolean testFlag;

    /**
     * 是否删除
     */
    @TableField("del_flag")
    private Boolean delFlag;

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

    /**
     * 3.0自身新增修改时，字段值为0，4.0同步新增修改时，字段值为1，待门派4.0完全切换到3.0之后废弃该字段
     */
    @TableField(value = "old_flag", fill = FieldFill.INSERT_UPDATE, select = false, insertStrategy = FieldStrategy.ALWAYS, updateStrategy = FieldStrategy.ALWAYS)
    private Short oldFlag = 0;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
