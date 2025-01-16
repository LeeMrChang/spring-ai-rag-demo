package com.djdj.sect.feign.rpc;

import com.djdj.sect.feign.req.OrderStatisticReq;
import com.djdj.sect.feign.resp.BaseResp;
import com.djdj.sect.feign.resp.OrderStatisticResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 订单统计RPC
 */
@FeignClient("bi")
public interface OrderStatisticRpc {

    /**
     * 按指定时间获取门派的订单统计数据
     *
     * @param req req
     * @return BaseResp
     */
    @PostMapping("/rpc/order/getSectList")
    BaseResp<List<OrderStatisticResp>> getOrderStatisticBySects(@RequestBody OrderStatisticReq req);

}

