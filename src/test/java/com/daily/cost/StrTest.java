package com.daily.cost;

import cn.hutool.json.JSONUtil;
import com.daily.cost.vo.SectStatisticProfitVo;

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
