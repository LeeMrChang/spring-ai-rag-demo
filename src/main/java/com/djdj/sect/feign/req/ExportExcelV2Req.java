package com.djdj.sect.feign.req;

import com.djdj.sect.utils.BizException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;


/**
 * @author lichanghao
 * @date 2024/12/9
 * @Desc
 */
@Data
public class ExportExcelV2Req {

    /*********************特别注意，以下字段命名不要修改***************************************/

    @Schema(description = "请求服务来源")
    private String requestSource;

    @Schema(description = "文件下载请求参数")
    private FileApiReqDto fileApiRequestDto;

    @Schema(description = "必填：excel文件的名称（不要加后缀）  例：订单详情")
    private String excleFileName;

    @Schema(description = "创建人的名字")
    private String createUserName;

    @Schema(description = "系统自定义的业务类型")
    private String busType;

    @Schema(description = "必填：创建人的userid")
    private Long createUserId;

    public void check() {
        if (StringUtils.isEmpty(busType)) {
            throw new BizException("busType  is null");
        }
        if (StringUtils.isEmpty(excleFileName)) {
            throw new BizException("excelFileName  is null");
        }

        if (ObjectUtils.isEmpty(createUserId)) {
            throw new BizException("createUserId  is null");
        }
        if (ObjectUtils.isEmpty(createUserName)) {
            throw new BizException("createUserName  is null");
        }
        if (ObjectUtils.isEmpty(requestSource)) {
            throw new BizException("requestSource  is null");
        }

        if (ObjectUtils.isEmpty(fileApiRequestDto)) {
            throw new BizException("fileApiRequestDto  is null");
        } else {
            fileApiRequestDto.check();
        }

    }
}
