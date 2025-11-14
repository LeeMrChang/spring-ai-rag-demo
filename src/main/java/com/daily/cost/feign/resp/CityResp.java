package com.daily.cost.feign.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * 公共服务RPC-开放城市resp
 */
@Data
public class CityResp {

    private Long id;
    private Integer type;
    @JsonProperty("city_id")
    private Long cityId;
    @JsonProperty("city_name")
    private String cityName;
    @JsonProperty("province_name")
    private String provinceName;
    @JsonProperty("area_code")
    private String areaCode;
    private String bond;
    private String fee;
    private Integer status;
    @JsonProperty("is_clock_time")
    private Integer isClockTime;
    @JsonProperty("createtime")
    private Date createTime;
    @JsonProperty("updatetime")
    private Date updateTime;
    @JsonProperty("oper_id")
    private Long operId;
    @JsonProperty("oper_name")
    private String operName;
    @JsonProperty("pay_type")
    private String payType;
    @JsonProperty("city_type")
    private Integer cityType;
    private String lng;
    private String lat;
    @JsonProperty("type_txt")
    private String typeTxt;

}
