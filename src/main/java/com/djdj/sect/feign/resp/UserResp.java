package com.djdj.sect.feign.resp;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lichanghao
 * @date 2024/12/10
 * @Desc
 */
@Data
@Accessors(chain = true)
@Schema(description = "管理员信息 Resp")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserResp {

    private Long id;
    private Integer type;
    private String orgIds;
    private String orgNames;
    private Long pid;
    private String pname;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 头像
     */
    private String avatar;
    private String loginfailure;
    private String logintime;
    private String loginip;
    private String ipToAddress;
    private String incMenuIds;
    private String decMenuIds;
    private Integer status;
    private String cityIds;
    private String cityNames;
    /**
     * 运营中心id
     */
    private Long merchantId;
    /**
     * 运营中心名称
     */
    private String merchantName;
    private String equipmentLog;
    private String createtime;
    private String updatetime;
    private String deletetime;
    private Integer isBoss;
    private String isKefu;
    private Integer enterStatus;
    private Integer assistantType;
    private String rate;
    private String money;
    private String frozenMoney;
    private Long roleId;
}
