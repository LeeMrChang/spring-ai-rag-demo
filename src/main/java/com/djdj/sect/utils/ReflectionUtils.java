package com.djdj.sect.utils;

import com.djdj.sect.feign.req.ExcelHeaderReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lichanghao
 * @date 2024/12/9
 * @Desc
 */
@Slf4j
public class ReflectionUtils {

    /**
     * @description: 获取表头描述的字段
     * @author: 游咏
     * @date: 2024/8/2 16:32
     * @param: tClass
     **/
    public static <T> List<ExcelHeaderReq> getDeclaration(Class<T> tClass) {

        // 处理父类字段
        Class<?> superclass = tClass.getSuperclass();
        List<Field> fieldList = new ArrayList<>();
        if (superclass != null && superclass != Object.class) {
            fieldList.addAll(Arrays.asList(superclass.getDeclaredFields()));
        }
        // 处理当前类字段
        fieldList.addAll(Arrays.asList(tClass.getDeclaredFields()));

        List<ExcelHeaderReq> requests = new ArrayList<>();
        int sortIndex = 0;
        for (Field field : fieldList) {
            if(field.isAnnotationPresent(Schema.class) ) {
                ExcelHeaderReq request = createRequest(field, sortIndex++);
                request.setFieldText(field.getAnnotation(Schema.class).description());
                requests.add(request);
            }
        }
        return requests;
    }


    private static ExcelHeaderReq createRequest(Field field, int sortIndex) {
        ExcelHeaderReq request = new ExcelHeaderReq();
        request.setFieldName(field.getName());
        request.setFieldSort(sortIndex);
        return request;
    }
}
