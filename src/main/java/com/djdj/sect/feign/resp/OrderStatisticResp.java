package com.djdj.sect.feign.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;


/**
 * 订单统计RPC返回对象
 */
@Data
public class OrderStatisticResp {

    /**
     * 门派ID
     */
    @JsonProperty("sect_id")
    private Long sectId;

    /**
     * 总营业分
     */
    @JsonProperty("sales")
    private BigDecimal sales;

    /**
     * 首单营业分
     */
    @JsonProperty("first_sales")
    private BigDecimal firstSales;

    /**
     * 续购营业分
     */
    @JsonProperty("add_sales")
    private BigDecimal addSales;

    /**
     * 升级营业分
     */
    @JsonProperty("up_sales")
    private BigDecimal upSales;

    /**
     * 首单量
     */
    @JsonProperty("first_order_count")
    private Long orderFirstCount;

}
