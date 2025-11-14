package com.djdj.sect.feign.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 运营中心服务RPC-获取运营中心信息resp
 */
@Data
public class MerchantResp {

    @JsonProperty("show_name")
    private String showName;
    private Long id;
    private String name;
    @JsonProperty("company_name")
    private String companyName;
    @JsonProperty("company_address")
    private String companyAddress;
    private String logo;
    private String mobile;
    @JsonProperty("merchant_type")
    private Integer merchantType;
    @JsonProperty("brief_info")
    private String briefInfo;
    @JsonProperty("enter_type")
    private Integer enterType;
    @JsonProperty("city_ids")
    private String cityIds;
    @JsonProperty("city_names")
    private String cityNames;
    @JsonProperty("office_images")
    private String officeImages;
    @JsonProperty("licence_image")
    private String licenceImage;
    @JsonProperty("credit_code")
    private String creditCode;
    @JsonProperty("licence_period")
    private String licencePeriod;
    @JsonProperty("licence_address")
    private String licenceAddress;
    @JsonProperty("legal_person")
    private String legalPerson;
    @JsonProperty("id_card_images")
    private String idCardImages;
    @JsonProperty("month_tax_report")
    private String monthTaxReport;
    @JsonProperty("elec_tax_num")
    private String elecTaxNum;
    @JsonProperty("elec_tax_pwd")
    private String elecTaxPwd;
    @JsonProperty("contact_name")
    private String contactName;
    @JsonProperty("contact_phone")
    private String contactPhone;
    @JsonProperty("contract_id")
    private Long contractId;
    @JsonProperty("purchase_information")
    private String purchaseInformation;
    @JsonProperty("service_phone")
    private String servicePhone;
    @JsonProperty("service_hours")
    private String serviceHours;
    private Object workday;
    @JsonProperty("order_receive_mode")
    private Integer orderReceiveMode;
    @JsonProperty("order_count")
    private Integer orderCount;
    private String score;
    @JsonProperty("praise_rate")
    private String praiseRate;
    @JsonProperty("praise_count")
    private Integer praiseCount;
    @JsonProperty("criticize_count")
    private Integer criticizeCount;
    @JsonProperty("middle_count")
    private Integer middleCount;
    @JsonProperty("collect_count")
    private Integer collectCount;
    @JsonProperty("order_receive_rate")
    private String orderReceiveRate;
    private Integer status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("enter_time")
    private Date enterTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("expire_time")
    private Date expireTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createtime")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatetime")
    private Date updateTime;
    @JsonProperty("deletetime")
    private Date deleteTime;
    @JsonProperty("user_id")
    private Long userTd;
    @JsonProperty("service_hours_night")
    private String serviceHoursNight;
    @JsonProperty("service_cost_night")
    private String serviceCostNight;
    @JsonProperty("service_cost_night_unit")
    private Integer serviceCostNightUnit;
    @JsonProperty("advance_subscribe")
    private String advanceSubscribe;
    @JsonProperty("advance_subscribe_unit")
    private Integer advanceSubscribeUnit;
    @JsonProperty("available_subscribe")
    private String availableSubscribe;
    @JsonProperty("available_subscribe_unit")
    private Integer availableSubscribeUnit;
    @JsonProperty("is_recommend")
    private Integer isRecommend;
    @JsonProperty("recommend_weight")
    private String recommendWeight;
    @JsonProperty("fastest_arrival")
    private String fastestArrival;
    private String balance;
    @JsonProperty("already_balance")
    private String alreadyBalance;
    @JsonProperty("has_server")
    private Integer hasServer;
    @JsonProperty("merchant_lonlat")
    private String merchantLonlat;
    @JsonProperty("apply_code")
    private String applyCode;
    @JsonProperty("receipt_code")
    private String receiptCode;
    @JsonProperty("receipt_image")
    private String receiptImage;
    @JsonProperty("is_competitive_participator")
    private Integer isCompetitiveParticipator;
    @JsonProperty("is_open_sect_withdraw")
    private Integer isOpenSectWithdraw;
    @JsonProperty("dj_city_id")
    private Long djCityId;
    @JsonProperty("legal_id_card_num")
    private String legalIdCardNum;
    @JsonProperty("status_txt")
    private String statusTxt;
    @JsonProperty("enter_type_txt")
    private String enterTypeTxt;

}
