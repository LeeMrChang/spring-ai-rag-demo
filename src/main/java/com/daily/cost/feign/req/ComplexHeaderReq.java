package com.daily.cost.feign.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lichanghao
 * @date 2024/12/9
 * @Desc
 */
@Data
public class ComplexHeaderReq {

    @Schema(description = "字段的名称，例如：name")
    private String fieldName;

    @Schema(description = "字段在excel中的排序，越小，越在前面")
    private Integer fieldSort;

    @Schema(description = "字段在excel中的注释,例如：姓名")
    private String fieldText;

    @Schema(description = "复杂表头的子集")
    private List<ComplexHeaderReq> children;

    private List<ExcelDto> resList = new ArrayList<>();

    // 递归函数，用于查找并添加子节点
    public void addFieldText(String parentText, String childText, String fieldName) {
        if (ObjectUtils.isEmpty(this.children)) {
            this.children = new ArrayList<>();
        }
        if (this.fieldText.equals(parentText)) {
            List<String> collect = this.children.stream().map(ComplexHeaderReq::getFieldText).toList();
            if (collect.contains(childText)) {
                return;
            }
            ComplexHeaderReq request = new ComplexHeaderReq();
            request.setFieldName(fieldName);
            request.setFieldText(childText);
            request.setFieldSort(collect.size() + 1);
            this.children.add(request);
            return;
        }
        for (ComplexHeaderReq child : this.children) {
            child.addFieldText(parentText, childText, fieldName);
        }
    }


    // 递归函数，用于收集叶子节点及其所有上级节点
    public void collectLeafNodesWithAncestors(ComplexHeaderReq node, ExcelDto excelDto) {
        if (ObjectUtils.isEmpty(excelDto)) {
            excelDto = new ExcelDto();
        }
        if (node.getChildren() == null || node.getChildren().isEmpty()) {
            //叶子节点,开始新增
            ExcelDto temp = new ExcelDto();
            List<String> strings = new ArrayList<>();
            if (!CollectionUtils.isEmpty(excelDto.getFieldText())) {
                strings.addAll(excelDto.getFieldText());
            }
            strings.add(node.getFieldText());
            temp.setFieldText(strings);
            temp.setFieldName(node.getFieldName());
            resList.add(temp);
        } else {
            List<String> stringList = excelDto.getFieldText();
            if (ObjectUtils.isEmpty(stringList)) {
                stringList = new ArrayList<>();
            }
            ExcelDto temp = new ExcelDto();
            List<String> strings = new ArrayList<>(stringList);
            strings.add(node.getFieldText());
            temp.setFieldText(strings);
            temp.setFieldName(node.getFieldName());
            // 如果不是叶子节点，递归遍历子节点
            // 使用lambda表达式简化排序
            List<ComplexHeaderReq> children1 = node.getChildren().stream().sorted(Comparator.comparingLong(ComplexHeaderReq::getFieldSort)).collect(Collectors.toList());
            for (ComplexHeaderReq child : children1) {
                collectLeafNodesWithAncestors(child, temp);
            }
        }
    }


    /**
     * 构建复杂表头的树
     *
     * @return
     */
    public static List<ComplexHeaderReq> buidTreeList(List<ExcelDto> excelDtos) {
        if (CollectionUtils.isEmpty(excelDtos)) {
            return new ArrayList<>();
        }
        List<ComplexHeaderReq> resList = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        // 遍历headList，找到最长的子列表
        int maxLength = 0;
        for (ExcelDto dto : excelDtos) {
            List<String> fieldText = dto.getFieldText();
            if (fieldText != null && fieldText.size() > maxLength) {
                maxLength = fieldText.size();
            }

            if (!set.contains(fieldText.get(0))) {
                //创建root根节点
                ComplexHeaderReq request = new ComplexHeaderReq();
                request.setFieldText(fieldText.get(0));
                if (fieldText.size() == 1) {
                    request.setFieldName(dto.getFieldName());
                }
                resList.add(request);
                set.add(fieldText.get(0));
            }
        }


        //遍历根节点
        for (ComplexHeaderReq request : resList) {
            String text = request.getFieldText();
            //再次遍历源数据
            for (ExcelDto dto : excelDtos) {
                List<String> fieldText = dto.getFieldText();
                if (!text.equals(fieldText.get(0))) {
                    continue;
                }
                //源数据的第一条已经被构建根节点，我们直接从第二个位置开始获取值，循环结束的地方是树的最大高度
                for (int i = 1; i < maxLength; i++) {
                    //如果循环的次数大于数组的长度，不再进行遍历
                    boolean b = fieldText.size() > (i);
                    if (!b) {
                        continue;
                    }

                    //获得本次遍历的父节点
                    String parentText = fieldText.get(i - 1);
                    //获得本次遍历儿子
                    String childText = fieldText.get(i);
                    //树的枝末节点，才需要复制字段属性
                    String name = null;
                    if (i == fieldText.size() - 1) {
                        name = dto.getFieldName();
                    }
                    //根据父节点，给树添加一个子节点
                    request.addFieldText(parentText, childText, name);
                }
//                set.add(dto.getFieldText().get(0));
            }
        }
        return resList;
    }

}
