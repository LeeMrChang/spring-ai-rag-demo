package com.djdj.sect.utils;

import org.apache.commons.lang3.ArrayUtils;
/**
 * SectServer校验
 */
public class CheckerUtil {
    private CheckerUtil() {
    }

    /**
     * 校验Long是否不为null且不为0
     *
     * @param num num
     * @return boolean
     */
    public static boolean checkLongEmpty(Long num) {
        return num == null || num == 0;
    }

    /**
     * 校验Short是否不为null且不为0
     *
     * @param num num
     * @return boolean
     */
    public static boolean checkIntegerEmpty(Integer num) {
        return num == null || num == 0;
    }

    /**
     * 校验Long是否不为null且不为0
     *
     * @param num num
     * @return boolean
     */
    public static boolean checkShortEmpty(Short num) {
        return num == null || num == 0;
    }

    /**
     * 校验任意Long是否不为null且不为0
     *
     * @param nums nums
     * @return boolean
     */
    public static boolean isAnyLongEmpty(Long... nums) {
        if (ArrayUtils.isEmpty(nums)) {
            return false;
        }
        for (final Long cs : nums) {
            if (checkLongEmpty(cs)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 校验所有Long是否不为null且不为0
     *
     * @param nums nums
     * @return boolean
     */
    public static boolean isAllLongEmpty(Long... nums) {
        if (ArrayUtils.isEmpty(nums)) {
            return false;
        }
        for (final Long cs : nums) {
            if (!checkLongEmpty(cs)) {
                return false;
            }
        }
        return true;
    }
}
