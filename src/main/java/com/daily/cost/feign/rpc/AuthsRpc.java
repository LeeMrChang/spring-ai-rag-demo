package com.daily.cost.feign.rpc;

import com.daily.cost.feign.req.AuthReq;
import com.daily.cost.feign.resp.BaseResp;
import com.daily.cost.feign.resp.UserResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author lichanghao
 * @date 2024/12/10
 * @Desc
 */
@FeignClient("auth-api")
public interface AuthsRpc {

    @PostMapping("/public/admin/by/token")
    BaseResp<UserResp> getUserInfoByToken(@RequestBody AuthReq authReq);
}
