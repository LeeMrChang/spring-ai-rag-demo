package com.djdj.sect.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.djdj.sect.entity.Sect;
import com.djdj.sect.mapper.SectMapper;
import com.djdj.sect.service.ISectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lichanghao
 * @date 2025/1/17
 * @Desc
 */
@Service
@Slf4j
public class SectServiceImpl extends ServiceImpl<SectMapper, Sect> implements ISectService {

    @Override
    public void truncateTable() {
        baseMapper.truncateTable();
    }
}
