package com.djdj.sect.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.djdj.sect.entity.Sect;

/**
 * @author lichanghao
 * @date 2025/1/17
 * @Desc
 */
public interface ISectService extends IService<Sect> {

    void truncateTable();
}
