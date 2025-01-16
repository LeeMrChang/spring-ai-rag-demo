package com.djdj.sect.feign.rpc;

import com.djdj.sect.feign.req.ExportExcelV2Req;
import com.djdj.sect.feign.resp.BaseResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lichanghao
 * @date 2024/12/9
 * @Desc 文件服务rpc
 */
@FeignClient("file-api")
public interface FileRpc {

    /**
     * 根据任务id获取下载的文件
     * @param taskId 任务id
     */
    @GetMapping("/back/TaskFileController/downFile")
    byte[] getDownFileByTaskId(@RequestParam(value = "taskId")Long taskId);

    /**
     * 推送数据的接口
     * @param excelV2Req 入参
     * @return 任务id
     */
    @PostMapping("/api/ExcelController/v2/addExcelTaskV2")
    BaseResp<Long> addExcelTask(@RequestBody ExportExcelV2Req excelV2Req);
}
