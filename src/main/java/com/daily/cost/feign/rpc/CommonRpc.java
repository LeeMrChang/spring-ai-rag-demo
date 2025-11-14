package com.daily.cost.feign.rpc;

import com.daily.cost.feign.req.BaseReq;
import com.daily.cost.feign.resp.BaseResp;
import com.daily.cost.feign.resp.CityResp;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 公共服务RPC
 */
@FeignClient("common")
public interface CommonRpc {

    /**
     * 获取系统配置
     * @param req
     * @return
     */
    @PostMapping("rpc/CommonRpcService/getSysConfig")
    BaseResp<Map<String, Object>> getSysConfig(@RequestBody BaseReq req);

    /**
     * 获取系统配置新接口
     * @param req
     * @return
     */
    @Cacheable(cacheNames = "CommonRpc:getSysConfigNew", key = "#req.arguments[0]", unless = "#result.code!=1")
    @PostMapping("rpc/CommonRpcService/getSysConfig_new")
    BaseResp<Map<String, Object>> getSysConfigNew(@RequestBody BaseReq req);

    /**
     * 获取开放城市列表
     * @param req
     * @return
     */
    @PostMapping("rpc/CommonRpcService/getOpenCity")
    BaseResp<List<CityResp>> getOpenCity(@RequestBody BaseReq req);

    /**
     * 获取私有桶文件签名链接
     * @param privateFileUrl
     * @return
     */
    @GetMapping("common/private/cdn/url")
    BaseResp<String> getSignatureUrl(@RequestParam("url") String privateFileUrl);

}
