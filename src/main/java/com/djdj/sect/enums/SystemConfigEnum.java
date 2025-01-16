package com.djdj.sect.enums;

import com.djdj.sect.utils.BizException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 系统配置枚举
 */
@Getter
@AllArgsConstructor
public enum SystemConfigEnum {

    PK_INTRODUCTION("pk_introduction", "PK说明"),
    PK_TYPE("pk_type", "pk类型"),
    PK_MODE("pk_mode", "pk模式"),
    MIN_SALES("min_sales", "达标贡献值"),
    LEVEL_CONDITION("level_condition", "非弟子等级条件"),
    HOUR_SALES("hour_sales", "时长贡献值"),
    ACHIEVEMENT_SALES("achievement_sales", "业绩贡献值"),
    FIRST_ORDER_SALES("first_order_sales", "首单贡献值"),
    PARTY_METTING_SCORE("party_metting_score", "讲座考核积分"),
    PARTY_SCORE_YJ("party_score_yj", "门派业绩积分"),
    PARTY_LEVEL("party_level", "门派等级配置"),
    CHAMPION_REWARD("champion_reward", "冠军奖励"),
    PARTY_TARGET("party_target", "门派目标"),
    CAO_IS_DEBUG("cao_is_debug", "打车环境"),
    JS_CAO_MENPAI_COUPON_RATIO("js_cao_menpai_coupon_ratio", "门派打车券比例"),
    JH_TASK("jh_task", "门派任务说明"),
    PARTY_MANAGE_RATE2("party_manage_rate2", "门派管理费2"),
    PARTY_MANAGE_RATE1("party_manage_rate1", "门派管理费1"),
    DYNAMIC_RATE("dynamic_rate", "公益积分兑换"),
    EXCHANGE_SWITCH("exchange_switch", "门派提现开关"),
    WELFARE_SCORE_RATE("welfare_score_rate", "贡献值转公益积分"),
    SETTLEMENT("settlement", "入驻协议"),
    ACTIVITY_RULE("activity_rule", "活动规则"),
    PROFIT_RULE_MASTER("profit_rule_master", "收益说明-师父"),
    PROFIT_RULE("profit_rule", "收益说明-师尊"),
    SALES_RATE("sales_rate", "佣金转化比例"),
    SF_FROM_DZ("sf_from_dz", "三级贡献值比例"),
    SZ_FROM_DZ("sz_from_dz", "二级贡献值比例"),
    SZ_FROM_SF("sz_from_sf", "一级贡献值比例"),
    PLUNDER_RATE("plunder_rate", "掠夺转化比例"),
    JH_ACTIVITY_COUNTRY_NAME("jh_activity_country_name", "门派全国PK活动名称"),
    JH_ACTIVITY_MONTH_NAME("jh_activity_month_name", "门派月度PK活动名称"),
    PARTY_RULE("party_rule", "门派规则"),
    PARTY_SCORE("party_score", "门派可赠送分值"),
    LEVEL_INTRODUCTION("level_introduction", "等级说明"),
    MISSION_INTRODUCTION("mission_introduction", "任务说明"),
    ACTIVITY_INTRODUCTION("activity_introduction", "活动说明"),
    SECT_INTRODUCTION("sect_introduction", "门派说明"),
    MASTER_CHECK_WHITELIST("master_check_whitelist", "师傅师尊校验白名单"),
    SECT_DETAIL_ANNOUNCEMENT("sect_detail_announcement", "门派详情公告"),

    ;

    private final String code;
    private final String desc;

    public static SystemConfigEnum valOf(String code) {
        return Arrays.stream(values())
                .filter(item -> item.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new BizException("系统配置【{}】不存在", code));
    }

}
