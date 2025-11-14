package com.djdj.sect.feign.req;

import com.djdj.sect.utils.BizException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author lichanghao
 * @date 2024/12/9
 * @Desc
 */
@Data
public class FileApiReqDto {

    @Schema(description = "必填：回调请求地址，请保证该接口必须支持分页功能，例 server:8000/server/info/status  或者 https://api.test.djdjapp.com/server/server/info/status")
    private String requestUrl;
    @Schema(description = "必填：回调请求方式，例 post 或 get")
    private String requestType;
    @Schema(description = "非必填：回调请求Header，例 [{\"token\": \"123\"}]")
    private Map<String, String> requestHeader;
    @Schema(description = "必填：回调请求参数需要是json对象，例 {\"id\": \"123\"}")
    private String requestParam;
    @Schema(description = "非必填：请求参数的中文含义")
    private String requestParamTextList;
    @Schema(description = "必填：回调字段名称（这个字段会放入requestParam对象里面回调）当前页数 例 page")
    private String pageFieldName;
    @Schema(description = "必填：回调字段名称（这个字段会放入requestParam对象里面回调）每页数量 例 size")
    private String pageSizeFieldName;
    @Schema(description = "必填：业务数据excel头部字段的注释和排序")
    private List<ExcelHeaderReq> excelHeaderRequestList;

    @Schema(description = "选填:复杂excel头部字段的注释和排序")
    private List<ComplexHeaderReq> complexHeaderRequestList;

    @Schema(description = "excel 最后面的数据")
    private List<Map<String, String>> lastExcelDataList;

    @Schema(description = "必填：回调请求参数返回json解析关键字路径 例 $data.records 或 $data")
    private String responseDynamicKey;
    @Schema(description = "非必填：每页数量，如果不填默认为 5000条")
    private Integer sizeNumber;
    @Schema(description = "非必填：接口超时时间，如果不填，默认100s")
    private Integer timeOut;

    public void check() {

        if (StringUtils.isEmpty(requestUrl)) {
            throw new BizException("requestUrl  is null");
        }
        if (StringUtils.isEmpty(requestType)) {
            throw new BizException("requestType  is null");
        }
        requestType = requestType.trim();
        if (StringUtils.isEmpty(requestParam)) {
            throw new BizException("requestParam  is null");
        }
        if (StringUtils.isEmpty(pageFieldName)) {
            throw new BizException("pageFieldName  is null");
        }
        if (StringUtils.isEmpty(pageSizeFieldName)) {
            throw new BizException("pageSizeFieldName  is null");
        }
        if (CollectionUtils.isEmpty(excelHeaderRequestList)) {
            throw new BizException("excelHeaderRequestList  is null");
        }
        if (StringUtils.isEmpty(responseDynamicKey)) {
            throw new BizException("responseDynamicKey  is null");
        }
    }
}
