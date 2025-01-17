package com.djdj.sect.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.djdj.sect.entity.Server;
import org.apache.ibatis.annotations.Update;

/**
 * @author lichanghao
 * @date 2025/1/17
 * @Desc
 */
public interface ServerMapper extends BaseMapper<Server> {

    @Update("TRUNCATE TABLE t_server;")
    void truncateTable();
}
