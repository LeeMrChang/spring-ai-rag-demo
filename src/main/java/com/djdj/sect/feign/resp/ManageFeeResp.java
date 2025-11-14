package com.djdj.sect.feign.resp;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 门派管理费统计Resp
 */
@Data
public class ManageFeeResp {

    // 门派id
    private Long sectId;

    // 管理费
    private BigDecimal amount;

}
