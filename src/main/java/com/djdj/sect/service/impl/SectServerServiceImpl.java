package com.djdj.sect.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.djdj.sect.entity.SectServer;
import com.djdj.sect.mapper.SectServerMapper;
import com.djdj.sect.service.ISectServerService;
import org.springframework.stereotype.Service;

/**
 * @author lichanghao
 * @date 2025/1/17
 * @Desc
 */
@Service
public class SectServerServiceImpl extends ServiceImpl<SectServerMapper, SectServer> implements ISectServerService {

    @Override
    public void truncateTable() {
        baseMapper.truncateTable();
    }
}
