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
 * 资产快照表 DTO
 * </p>
 *
 * @author lichanghao
 * @since 2025-11-14
 */
@Getter
@Setter
@Accessors(chain = true)
@Schema(description = "资产快照表 DTO")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AssetSnapshotDto {

    @Schema(description = "主键id")
    private Long id;

    @Schema(description = "用户id(t_user.id)")
    private Long userId;

    @Schema(description = "账户名称（支付宝/微信/银行卡等）")
    private String name;

    @Schema(description = "账户类型（1-现金/2-银行卡/3-信用卡/4-投资）")
    private Boolean type;

    @Schema(description = "当前余额")
    private BigDecimal balance;

    @Schema(description = "是否隐藏，0表示不隐藏，1表示隐藏")
    private Boolean isHidden;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "银行id")
    private Long bankId;

    @Schema(description = "银行名称")
    private String bankName;

    @Schema(description = "银行支行")
    private String bankSubbranch;

    @Schema(description = "银行卡号")
    private String bankNo;

    @Schema(description = "创建人")
    private Long createId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新人")
    private Long updateId;

    @Schema(description = "更新时间")
    private Date updateTime;
}