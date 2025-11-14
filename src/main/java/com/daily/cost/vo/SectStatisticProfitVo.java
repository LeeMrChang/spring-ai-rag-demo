package com.daily.cost.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author lichanghao
 * @date 2024/6/14
 * @Desc
 */
@Data
@EqualsAndHashCode
@ContentRowHeight(20)
@HeadRowHeight(30)
@Schema(description = "门派统计-门派收支数据统计 VIEW")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SectStatisticProfitVo {

    @ExcelIgnore
    private Long id;

    @ExcelProperty(value = {"月份"})
    @ColumnWidth(value = 20)
    @Schema(description = "年月份")
    private String month;

    @ExcelIgnore
    @Schema(description = "运营中心id")
    private Long merchantId;

    @ExcelProperty(value = {"运营中心"})
    @ColumnWidth(value = 20)
    @Schema(description = "运营中心")
    private String merchantName;

    @ExcelIgnore
    @Schema(description = "门派id")
    private Long sectId;

    @ExcelProperty(value = {"门派"})
    @ColumnWidth(value = 20)
    @Schema(description = "门派")
    private String sectName;

    /**
     * 技师承担管理费(来源rpc资金服务)
     */
    @ExcelProperty(value = {"支出","商户承担基础管理费"})
    @ColumnWidth(value = 20)
    @Schema(description = "技师承担管理费")
    private BigDecimal serverAmount;

    /**
     * 平台承担的管理费(来源rpc资金服务)
     */
    @ExcelIgnore
    @Schema(description = "平台承担的管理费")
    private BigDecimal platformAmount;

    /**
     * 支出-贡献值权益-基础
     */
    @ExcelProperty(value = {"支出","贡献值权益","基础"})
    @ColumnWidth(value = 20)
    @Schema(description = "支出-贡献值权益-基础")
    private BigDecimal salesMoneyProfitBase;

    /**
     * 支出-贡献值权益-加成
     */
    @ExcelProperty(value = {"支出","贡献值权益","加成"})
    @ColumnWidth(value = 20)
    @Schema(description = "支出-贡献值权益-加成")
    private BigDecimal salesMoneyProfit;

    /**
     * 支出-贡献值权益-5%税前
     */
    @ExcelProperty(value = {"支出","贡献值权益","5.8%税前"})
    @ColumnWidth(value = 20)
    @Schema(description = "支出-贡献值权益-5.8%税前")
    private BigDecimal salesMoneyPreTax;

    /**
     * 支出-门派总经费
     */
    @ExcelProperty(value = {"支出","门派总经费"})
    @ColumnWidth(value = 20)
    @Schema(description = "支出-门派总经费")
    private BigDecimal sectMoney;

    /**
     * 支出-公益贡献值
     */
    @ExcelProperty(value = {"支出","公益贡献额"})
    @ColumnWidth(value = 20)
    @Schema(description = "支出-公益贡献值")
    private BigDecimal welfareMoney;

    /**
     * 收入-项目实付金额(来源rpc资金服务)
     */
    @ExcelProperty(value = {"收入","项目实付交额"})
    @ColumnWidth(value = 20)
    @Schema(description = "收入-项目实付金额")
    private BigDecimal itemMoney;

    @ExcelIgnore
    @Schema(description = "项目分成金额（车费）")
    private BigDecimal guaranteeAmount;

    /**
     * 支出合计-平台
     *  贡献值权益5%税前 + 本月门派总经费 + 公益贡献值
     */
    @ExcelProperty(value = {"支出合计","平台"})
    @ColumnWidth(value = 20)
    @Schema(description = "支出合计-平台")
    private BigDecimal expendPlatformTotal;

    /**
     * 支出合计-合计
     *  商户承担管理费 + 平台合计
     */
    @ExcelProperty(value = {"支出合计","合计"})
    @ColumnWidth(value = 20)
    @Schema(description = "支出合计-合计")
    private BigDecimal expendTotal;

    /**
     * 支出占比-商户
     *  商户承担管理费 / 总项目成交额
     */
    @ExcelProperty(value = {"支出占比","商户"})
    @ColumnWidth(value = 20)
    @Schema(description = "支出占比-商户")
    private String expendProportionServer;

    /**
     * 支出占比-平台
     *  平台支出合计 / 总项目成交额
     */
    @ExcelProperty(value = {"支出占比","平台"})
    @ColumnWidth(value = 20)
    @Schema(description = "支出占比-平台")
    private String expendProportionPlatform;

    /**
     * 支出占比-合计
     *  支出合计 / 总项目成交额
     */
    @ExcelProperty(value = {"支出占比","合计"})
    @ColumnWidth(value = 20)
    @Schema(description = "支出占比-合计")
    private String expendProportionTotal;
}
