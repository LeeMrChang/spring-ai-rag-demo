package com.djdj.sect.utils;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ObjectUtil;

import java.util.Comparator;

/**
 * 比较器工具类
 */
public class ComparatorUtil {

    private ComparatorUtil() {}

    /**
     * 门派等级比较器
     * @return
     */
    public static Comparator<String> getSectGradeComparator() {
        return (gradeA, gradeB) -> {
            if (CharSequenceUtil.equals(gradeA, gradeB)) {
                return 0;
            }
            if ("0".equals(gradeA)) {
                return -1;
            }
            if ("0".equals(gradeB)) {
                return 1;
            }
            int[] gradeArrA = CharSequenceUtil.splitToInt(gradeA, "-");
            int[] gradeArrB = CharSequenceUtil.splitToInt(gradeB, "-");
            int prefixA = gradeArrA[0];
            int prefixB = gradeArrB[0];
            int suffixA = gradeArrA[1];
            int suffixB = gradeArrB[1];
            return ObjectUtil.notEqual(prefixA, prefixB)
                    ? Integer.compare(prefixA, prefixB)
                    : Integer.compare(suffixA, suffixB);
        };
    }

}
