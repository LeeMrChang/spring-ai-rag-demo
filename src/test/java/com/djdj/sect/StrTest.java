package com.djdj.sect;

import cn.hutool.json.JSONUtil;
import com.djdj.sect.utils.ReflectionUtils;
import com.djdj.sect.vo.SectStatisticProfitVo;

/**
 * @author lichanghao
 * @date 2025/1/22
 * @Desc
 */
public class StrTest {


    public static void main(String[] args) {
        System.out.println(JSONUtil.toJsonStr(
                ReflectionUtils.buildComplexHeaderRequest(SectStatisticProfitVo.class.getDeclaredFields())));
    }
}
