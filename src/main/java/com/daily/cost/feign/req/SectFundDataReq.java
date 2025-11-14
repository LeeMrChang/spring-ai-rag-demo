package com.daily.cost.feign.req;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lichanghao
 * @date 2024/11/26
 * @Desc rpc请求入参对象
 */
@Accessors(chain = true)
@Data
public class SectFundDataReq {

    /**
     * 门派id
     */
    private Long sectId;
    /**
     * 开始时间 yyyy-mm-dd hh:mm:ss
     */
    private String startTime;
    /**
     * 结束时间  yyyy-mm-dd hh:mm:ss
     */
    private String endTime;
}
