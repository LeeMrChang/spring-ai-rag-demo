package com.djdj.sect.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.djdj.sect.entity.Server;

/**
 * @author lichanghao
 * @date 2025/1/17
 * @Desc
 */
public interface IServerService extends IService<Server> {

    void truncateTable();
}
