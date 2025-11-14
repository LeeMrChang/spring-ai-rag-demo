package com.djdj.sect.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.djdj.sect.entity.Sect;
import org.apache.ibatis.annotations.Update;

/**
 * @author lichanghao
 * @date 2025/1/17
 * @Desc
 */
public interface SectMapper extends BaseMapper<Sect> {

    @Update("TRUNCATE TABLE t_sect;")
    void truncateTable();
}
