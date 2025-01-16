package com.djdj.sect.feign.rpc;

import com.djdj.sect.feign.req.SectFundDataReq;
import com.djdj.sect.feign.req.StartEndDateReq;
import com.djdj.sect.feign.resp.BaseResp;
import com.djdj.sect.feign.resp.ManageFeeResp;
import com.djdj.sect.feign.resp.SectFundDataResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author lichanghao
 * @date 2024/11/26
 * @Desc 资金数仓rpc
 */
@FeignClient("financial-java-service")
public interface FinancialJavaServiceRpc {

    /**
     * 门派获取资金服务数据的rpc接口
     *
     * @param req 请求入参
     * @return resp
     */
    @PostMapping("/api/sect/v1/getSectBiStatistics")
    BaseResp<List<SectFundDataResp>> getSectBiStatistics(@RequestBody SectFundDataReq req);

    /**
     * 资金BI服务获取门派管理费统计数据
     *
     * @param req 请求入参
     * @return resp
     */
    @PostMapping("/api/sect/v1/getSectAmountStatistics")
    BaseResp<List<ManageFeeResp>> getBiSectManageFeeStatistics(@RequestBody StartEndDateReq req);
}
