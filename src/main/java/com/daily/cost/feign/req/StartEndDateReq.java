package com.daily.cost.feign.req;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class StartEndDateReq {

    // 开始时间 yyyy-MM-dd
    private String startTime;

    // 结束时间  yyyy-MM-dd
    private String endTime;

}
