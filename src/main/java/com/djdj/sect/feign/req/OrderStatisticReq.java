package com.djdj.sect.feign.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 订单统计RPC
 * 请求体示例
 * {
 * "start_time": "2024-07-16 08:00:00",
 * "end_time": "2024-08-01 07:59:59",
 * "sect_id":"" //门派ID  逗号隔开
 * }
 */
@Getter
@Setter
@Accessors(chain = true)
public class OrderStatisticReq {
    @JsonProperty("start_time")
    private String startTime;
    @JsonProperty("end_time")
    private String endTime;
    @JsonProperty("sect_id")
    private String sectId;
}
