package com.daily.cost.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 分类表
 * </p>
 *
 * @author lichanghao
 * @since 2025-11-14
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_categorize")
public class Categorize extends Model<Categorize> {

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
     * 图标id(t_icon.id)
     */
    @TableField("icon_id")
    private Long iconId;

    /**
     * 分类名称
     */
    @TableField("name")
    private String name;

    /**
     * 类型（1-收入/2-支出/3-转账）
     */
    @TableField("type")
    private Boolean type;

    /**
     * 父级分类id
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 是否系统预设，0表示否，1表示是
     */
    @TableField("is_system")
    private Boolean isSystem;

    /**
     * 排序顺序
     */
    @TableField("sort_order")
    private Integer sortOrder;

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
