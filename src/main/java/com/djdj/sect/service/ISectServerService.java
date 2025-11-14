package com.djdj.sect.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.djdj.sect.entity.SectServer;

/**
 * @author lichanghao
 * @date 2025/1/17
 * @Desc
 */
public interface ISectServerService extends IService<SectServer> {

    void truncateTable();
}
