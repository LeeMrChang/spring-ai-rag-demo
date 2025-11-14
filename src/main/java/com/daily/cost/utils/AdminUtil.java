package com.daily.cost.utils;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.NumberUtil;
import com.daily.cost.constant.SectConstant;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.Objects;

import static com.daily.cost.constant.SectConstant.HEAD_QUARTER;
import static com.daily.cost.constant.SectConstant.NOT_MATCHED;

/**
 * 管理端util
 */
@Slf4j
public class AdminUtil {

    private AdminUtil() {
    }

    /**
     * 获取当前管理端用户ID
     *
     * @return
     */
    public static Long getUserId() {
        String userId = MDC.get(SectConstant.ADMIN_USER_ID);
        if (NumberUtil.isLong(userId)) {
            return Long.parseLong(userId);
        } else {
            throw new BizException("管理端用户ID为空");
        }
    }

    /**
     * 获取当前管理端用户名称
     *
     * @return
     */
    public static String getUserName() {
        String userName = MDC.get(SectConstant.ADMIN_USER_NAME);
        if (CharSequenceUtil.isNotBlank(userName)) {
            return userName;
        } else {
            throw new BizException("管理端用户名称为空");
        }
    }

    /**
     * 获取当前管理端用户所属运营中心ID
     *
     * @return merchant_id
     */
    public static Long getUserMerchantId() {
        String merchantId = MDC.get(SectConstant.ADMIN_MERCHANT_ID);
        if (NumberUtil.isLong(merchantId)) {
            return Long.parseLong(merchantId);
        } else {
           throw new BizException("管理端用户所属运营中心ID为空");
        }
    }

    /**
     * 运营中心ID查询参数与操作人所在运营中心比对
     *
     * @return merchant_id
     */
    public static Long checkMerchantId(Long queryId) {
        Long selfId = getUserMerchantId();
        if (Objects.equals(selfId, HEAD_QUARTER)) { // 总部账号，支持筛选。
            // 如果为空，返回空，那么查所有。
            // 不过不空，直接返回，查对应的ID。
            return queryId;
        } else { // 非总部账号，不支持筛选。
            // 如果不传，或这传自己，这个时候查自己运营中心
            // 如果传过来的参数不是自己所在运营中心，直接返回空数组。
            return queryId == null || Objects.equals(queryId, selfId) ? selfId : NOT_MATCHED;
        }
    }

    /**
     * 判断当前管理端用户是否属于运营中心
     * @return
     */
    public static boolean merchantAuth() {
        String adminMark = MDC.get(SectConstant.ADMIN_MARK);
        String merchantId = MDC.get(SectConstant.ADMIN_MERCHANT_ID);
        return Objects.equals(adminMark, Boolean.TRUE.toString()) && NumberUtil.isLong(merchantId) && Long.parseLong(merchantId) > 0;
    }

    /**
     * 判断当前管理端用户是否属于总部
     * @return
     */
    public static boolean headAuth() {
        String adminMark = MDC.get(SectConstant.ADMIN_MARK);
        String merchantId = MDC.get(SectConstant.ADMIN_MERCHANT_ID);
        return Objects.equals(adminMark, Boolean.TRUE.toString()) && NumberUtil.isLong(merchantId) && Long.parseLong(merchantId) == 0;
    }

}
