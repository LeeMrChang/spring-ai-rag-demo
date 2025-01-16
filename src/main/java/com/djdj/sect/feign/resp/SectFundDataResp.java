package com.djdj.sect.feign.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author lichanghao
 * @date 2024/11/26
 * @Desc  门派资金数据-rpc
 */
@Data
@Accessors(chain = true)
@Schema(description = "门派资金数据 Resp")
public class SectFundDataResp {

    /**
     *  门派id
     */
    private Long sectId;
    /**
     * 订单结算时间
     */
    private String orderSettlementDate;
    /**
     * 首单渠道补贴
     */
    private BigDecimal firstChannel;
    /**
     * 加钟渠道补贴
     */
    private BigDecimal addChannel;
    /**
     * 师尊B类城市分成
     */
    private BigDecimal masterManage;
    /**
     * 师傅B类城市分成
     */
    private BigDecimal topMasterManage;
    /**
     * 支出合计 = 师尊分成+师傅分成
     */
    private BigDecimal totalAmount;
    /**
     * 项目分成金额
     */
    private BigDecimal srIncomeMoney;
    /**
     * B类-收入-项目实付金额
     */
    private BigDecimal srItemMoney;
    /**
     * 技师承担管理费
     */
    private BigDecimal serverAmount;
    /**
     * 平台承担的管理费
     */
    private BigDecimal platformAmount;
    /**
     * 收入-项目实付金额
     */
    private BigDecimal itemMoney;
}
