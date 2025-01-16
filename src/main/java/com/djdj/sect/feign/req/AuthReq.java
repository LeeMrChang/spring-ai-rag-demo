package com.djdj.sect.feign.req;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lichanghao
 * @date 2024/12/10
 * @Desc
 */
@Data
@Accessors(chain = true)
public class AuthReq {

    private String token;
}
