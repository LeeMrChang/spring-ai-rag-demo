package com.djdj.sect.feign.rpc;

import com.djdj.sect.feign.req.AuthReq;
import com.djdj.sect.feign.resp.BaseResp;
import com.djdj.sect.feign.resp.UserResp;
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
