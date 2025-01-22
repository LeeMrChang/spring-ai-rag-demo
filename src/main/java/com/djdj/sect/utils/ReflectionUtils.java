package com.djdj.sect.utils;

import cn.hutool.core.text.CharSequenceUtil;
import com.alibaba.excel.annotation.ExcelProperty;
import com.djdj.sect.entity.ComplexHeaderVo;
import com.djdj.sect.feign.req.ExcelHeaderReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.*;

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
            if (field.isAnnotationPresent(Schema.class)) {
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

    public static List<ComplexHeaderVo> buildComplexHeaderRequest(Field[] array) {
        List<ComplexHeaderVo> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (Field field : array) {
            if (field.isAnnotationPresent(ExcelProperty.class)) {
                ExcelProperty property = field.getDeclaredAnnotation(ExcelProperty.class);
                String[] value = property.value();
                //1、处理单层表头
                if (value.length == 1 && CharSequenceUtil.isNotBlank(value[0])) {
                    ComplexHeaderVo vo = new ComplexHeaderVo();
                    vo.setFieldName(CharSequenceUtil.toUnderlineCase(field.getName()))
                            .setFieldText(value[0]);
                    list.add(vo);
                }
                //2、处理多层表头，数组长度大于1且第一个值未被处理过
                if (value.length > 1 && !set.contains(value[0])) {
                    String fieldText = value[0];
                    ComplexHeaderVo headerRequest = new ComplexHeaderVo();
                    headerRequest.setFieldText(fieldText);
                    list.add(headerRequest);
                    findChildrenRecursion(array, headerRequest, 2, getParentFieldText(value, 2));
                    set.add(value[0]);
                }
            }
        }
        return list;
    }

    private static void findChildrenRecursion(Field[] array, ComplexHeaderVo headerRequest, int level, String parentFieldText) {
        Set<String> set = new HashSet<>();
        for (Field declaredField : array) {
            if (declaredField.isAnnotationPresent(ExcelProperty.class)) {
                ExcelProperty excelProperty = declaredField.getDeclaredAnnotation(ExcelProperty.class);
                String[] value = excelProperty.value();
                if (value.length >= level && !set.contains(value[level - 1]) && getParentFieldText(value, level).equals(parentFieldText)) {
                    ComplexHeaderVo children = new ComplexHeaderVo()
                            .setFieldName(CharSequenceUtil.toUnderlineCase(declaredField.getName()))
                            .setFieldText(value[level - 1]);
                    if (headerRequest.getChildren() == null) {
                        headerRequest.setChildren(new ArrayList<>());
                    }
                    headerRequest.getChildren().add(children);
                    // 递归查询复杂表头的子集
                    findChildrenRecursion(array, children, level + 1, getParentFieldText(value, level + 1));
                    set.add(value[level - 1]);
                }
            }
        }
    }

    private static String getParentFieldText(String[] value, int level) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < level - 1; i++) {
            stringBuilder.append(value[i]);
            if (i < level - 2) {
                stringBuilder.append(".");
            }
        }
        return stringBuilder.toString();
    }
}
