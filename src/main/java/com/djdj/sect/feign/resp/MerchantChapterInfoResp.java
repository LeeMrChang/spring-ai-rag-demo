package com.djdj.sect.feign.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 运营中心服务RPC-获取支付公司信息resp
 */
@Data
public class MerchantChapterInfoResp {

    private Long id;
    @JsonProperty("merchant_id")
    private Long merchantId;
    @JsonProperty("area_id")
    private Long areaId;
    @JsonProperty("company_name")
    private String companyName;
    @JsonProperty("company_addr")
    private String companyAddr;
    @JsonProperty("legal_person")
    private String legalPerson;
    @JsonProperty("chapter_id")
    private String chapterId;
    @JsonProperty("createtime")
    private String createTime;
    private String committee;
    @JsonProperty("true_area_id")
    private Long trueAreaId;
    @JsonProperty("now_num")
    private Integer nowNum;
    @JsonProperty("warning_num")
    private Integer warningNum;
    @JsonProperty("is_default")
    private Integer isDefault;
    @JsonProperty("flexible_is_default")
    private Integer flexibleIsDefault;
    private Integer status;
    @JsonProperty("total_amount")
    private String totalAmount;

}
