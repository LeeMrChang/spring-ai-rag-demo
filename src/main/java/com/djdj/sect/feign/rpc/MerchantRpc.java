package com.djdj.sect.feign.rpc;

import com.djdj.sect.feign.req.BaseReq;
import com.djdj.sect.feign.resp.BaseResp;
import com.djdj.sect.feign.resp.MerchantChapterInfoResp;
import com.djdj.sect.feign.resp.MerchantResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 运营中心服务RPC
 */
@FeignClient("merchant")
public interface MerchantRpc {

    /**
     * 获取所有运营中心列表
     * @return
     */
    @PostMapping(value = "rpc/MerchantRpcForSect/getAllMerchantList")
    BaseResp<List<MerchantResp>> getAllMerchantList();

    /**
     * 通过id获取运营中心信息
     * @param req
     * @return
     */
    @PostMapping("rpc/MerchantRpcForSect/getSectMerchantById")
    BaseResp<MerchantResp> getMerchantById(@RequestBody BaseReq req);

    /**
     * 获取支付公司信息
     * @param req
     * @return
     */
    @PostMapping("rpc/MerchantRpcService/getMerchantChapterInfo")
    BaseResp<List<MerchantChapterInfoResp>> getMerchantChapterInfo(@RequestBody BaseReq req);

    @PostMapping("rpc/MerchantRpcNewService/meetingContent")
    BaseResp<Object> getMerchantMeetingContent(@RequestBody BaseReq req);
}
