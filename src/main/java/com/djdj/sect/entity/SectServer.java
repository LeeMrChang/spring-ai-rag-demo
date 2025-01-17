package com.djdj.sect.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;



/**
 * <p>
 * 门派成员表
 * </p>
 *
 * @author djdj
 * @since 2024-04-09
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_sect_server")
public class SectServer extends Model<SectServer> {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 门派ID（t_sect.id）
     */
    @TableField("sect_id")
    private Long sectId;

    /**
     * 成员类型（1-大师尊，2-二师尊，3-师父，4-弟子）
     */
    @TableField("type")
    private Short type;

    /**
     * 成员ID
     */
    @TableField("server_id")
    private Long serverId;

    /**
     * 所属师尊ID
     */
    @TableField("top_parent_id")
    private Long topParentId;

    /**
     * 父级ID
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 加入门派时间
     */
    @TableField("join_time")
    private Date joinTime;

    /**
     * 当期贡献值
     */
    @TableField("contribution_value")
    private Integer contributionValue;

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
