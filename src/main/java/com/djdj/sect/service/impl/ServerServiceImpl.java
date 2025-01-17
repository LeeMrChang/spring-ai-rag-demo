package com.djdj.sect.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.djdj.sect.entity.Server;
import com.djdj.sect.mapper.ServerMapper;
import com.djdj.sect.service.IServerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lichanghao
 * @date 2025/1/17
 * @Desc
 */
@Service
@Slf4j
public class ServerServiceImpl extends ServiceImpl<ServerMapper, Server> implements IServerService {

    @Override
    public void truncateTable() {
        baseMapper.truncateTable();
    }
}
