package com.djdj.sect.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.djdj.sect.entity.SectServer;
import org.apache.ibatis.annotations.Update;

/**
 * @author lichanghao
 * @date 2025/1/17
 * @Desc
 */
public interface SectServerMapper extends BaseMapper<SectServer> {

    @Update("TRUNCATE TABLE t_sect_server;")
    void truncateTable();
}
