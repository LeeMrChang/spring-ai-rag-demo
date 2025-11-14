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
 * 借贷记录表 DTO
 * </p>
 *
 * @author lichanghao
 * @since 2025-11-14
 */
@Getter
@Setter
@Accessors(chain = true)
@Schema(description = "借贷记录表 DTO")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LoanRecordDto {

    @Schema(description = "主键id")
    private Long id;

    @Schema(description = "用户id(t_user.id)")
    private Long userId;

    @Schema(description = "类型（1-借入/2-借出）")
    private Boolean type;

    @Schema(description = "金额")
    private BigDecimal amount;

    @Schema(description = "对方名称")
    private String targetName;

    @Schema(description = "状态（1-未还/2-已还/3-部分还）")
    private Boolean status;

    @Schema(description = "到期日期")
    private Date dueDate;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建人")
    private Long createId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新人")
    private Long updateId;

    @Schema(description = "更新时间")
    private Date updateTime;
}