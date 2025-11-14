package com.daily.cost.converter;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.function.Function;

/**
 * 分页转换器
 */
public class PageConverter {

    private PageConverter() {}

    public static <B, A> IPage<B> convert(IPage<A> page, Class<B> clazz) {
        List<B> list = BeanUtil.copyToList(page.getRecords(), clazz);
        return new Page<B>()
                .setCurrent(page.getCurrent())
                .setPages(page.getPages())
                .setSize(page.getSize())
                .setTotal(page.getTotal())
                .setRecords(list);
    }

    public static <B, A> IPage<B> convert(IPage<A> page, Function<A, B> func) {
        List<B> list = page.getRecords().stream().map(func).toList();
        return new Page<B>()
                .setCurrent(page.getCurrent())
                .setPages(page.getPages())
                .setSize(page.getSize())
                .setTotal(page.getTotal())
                .setRecords(list);
    }
}
