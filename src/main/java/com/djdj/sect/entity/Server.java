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
 * 成员表
 * </p>
 *
 * @author djdj
 * @since 2024-04-09
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_server")
public class Server extends Model<Server> {

    /**
     * 成员ID
     */
    @TableId("id")
    private Long id;

    /**
     * 成员名称
     */
    @TableField("name")
    private String name;

    /**
     * 成员实名
     */
    @TableField("real_name")
    private String realName;

    /**
     * 成员头像
     */
    @TableField("profile")
    private String profile;

    /**
     * 成员等级
     */
    @TableField("grade")
    private String grade;

    /**
     * 性别（1男，2女）
     */
    @TableField("sex")
    private Short sex;

    /**
     * 总贡献分
     */
    @TableField("contribution_score")
    private BigDecimal contributionScore;

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
