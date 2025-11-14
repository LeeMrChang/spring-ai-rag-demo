package com.daily.cost.constant;

import cn.hutool.core.text.CharSequenceUtil;

public class RedisConstant {

    private RedisConstant() {}

    /**5秒*/
    public static final Integer SEC_5 = 5;
    /**10秒*/
    public static final Integer SEC_10 = 10;
    /**60秒*/
    public static final Integer SEC_60 = 60;

    /**锁key前缀*/
    private static final String LOCK_KEY_PREFIX = "lockKey:";
    /**校验key前缀*/
    private static final String VALID_KEY_PREFIX = "validKey:";

    /**完成订单接口锁key*/
    public static final String COMPLETE_ORDER_LOCK_KEY = LOCK_KEY_PREFIX + "completeOrder:{}";
    /**同步在线时长接口锁key*/
    public static final String SYN_ONLINE_HOUR_LOCK_KEY = LOCK_KEY_PREFIX + "synOnlineHour:{}";
    /**目标详情领区30贡献分锁key*/
    public static final String INSERT_TARGET_CONTRIBUTION_SCORE = LOCK_KEY_PREFIX + "saveJournalContributionScoreByTarget:{}";
    /**更新成员贡献值锁key*/
    public static final String UPDATE_SERVER_CONTRIBUTION_VALUE_LOCK_KEY = LOCK_KEY_PREFIX + "updateServerContributionValue:{}";
    /**更新门派贡献值和公益积分锁key*/
    public static final String UPDATE_SECT_CONTRIBUTION_VALUE_AND_WELFARE_SCORE_LOCK_KEY = LOCK_KEY_PREFIX + "updateSectContributionValueAndWelfareScore:{}";
    /**更新成员贡献分锁key*/
    public static final String UPDATE_CONTRIBUTION_SCORE_LOCK_KEY = LOCK_KEY_PREFIX + "updateContributionScore:{}";
    /**更新任务记录进度分锁key*/
    public static final String UPDATE_TASK_LOG_PROGRESS_LOCK_KEY = LOCK_KEY_PREFIX + "updateTaskLogProgress:{}:{}";
    /**更新目标业绩分锁key*/
    public static final String UPDATE_TARGET_LOG_PERFORMANCE_LOCK_KEY = LOCK_KEY_PREFIX + "updateTargetLogPerformance:{}";
    /**更新门派经费锁key*/
    public static final String UPDATE_FUND_LOCK_KEY = LOCK_KEY_PREFIX + "updateFund:{}";
    /**更新门派荣耀分锁key*/
    public static final String UPDATE_GLORY_SCORE_LOCK_KEY = LOCK_KEY_PREFIX + "updateGloryScore:{}";
    /**申请提现-门派经费锁key*/
    public static final String APPLY_WITHDRAW_OF_FUND = LOCK_KEY_PREFIX + "applyWithdrawOfFund:{}";
    /**申请提现-门派收益锁key*/
    public static final String APPLY_WITHDRAW_OF_EARNING = LOCK_KEY_PREFIX + "applyWithdrawOfEarning:{}";

    /**防止同个用户重复请求校验key*/
    public static final String REPETITION_VALID = VALID_KEY_PREFIX + "repetitionValid:{}:{}";

    /**
     * 格式化key
     * @param key
     * @param params
     * @return
     */
    public static String format(String key, Object... params) {
        return CharSequenceUtil.format(key, params);
    }

}
