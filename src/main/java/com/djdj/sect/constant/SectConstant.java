package com.djdj.sect.constant;

import cn.hutool.core.date.DatePattern;
import cn.hutool.json.JSONConfig;

/**
 * 门派相关constant
 */
public final class SectConstant {

    private SectConstant() {}

    public static final JSONConfig JSON_CONFIG = JSONConfig.create().setDateFormat(DatePattern.NORM_DATETIME_PATTERN);

    public static final String NO_CONFIGURATION_VALUE = "未配置【{}】【{}】";
    public static final String LIMIT_1 = "limit 1";
    public static final String TASK_START = "[{}] job start, {}";
    public static final String TASK_ERROR = "[{}] job error.";
    public static final String TASK_END = "[{}] job done, cost time: {}";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYY_MM = "yyyy-MM";
    public static final String YYYY_MM_2 = "yyyy年MM月";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_NOT_SS = "yyyy.MM.dd HH:mm";
    public static final String MM_DD = "MM月dd日";
    public static final String YYYY_MM_DD_HH_MM = "yyyyMMddHHmm";
    public static final String YYYY_MM_DD_HH = "yyyy-MM-dd-HH";
    public static final String THE_FORMAT_OF_THE_TASK_PARAMETER_IS_INCORRECT = "任务参数格式错误，仅支持【{}】";
    public static final String SECT_ID_NOT_EXIST = "门派ID【{}】不存在";
    public static final String AUDIT_NOT_IN_PENDING = "该条审批不在待审批状态";
    public static final String PERIOD_TEMPLATE = "{}~{}";

    // 当个师尊下的师父数量
    public static final int MASTER_LIMIT = 5;
    // 当个师父下的弟子数量
    public static final int FOLLOWER_LIMIT = 5;
    // 门派等级格式
    public static final String GRADE_NAME_TEMPLATE = "{}级{}阶";

    public static final String GRADE_CODE_TEMPLATE = "{}-{}";

    public static final String SYS_MARK = "系统编辑，默认同意";

    public static final String DISMISS = "遣散踢出";

    public static final String APPLY_NO_NEED_PERMIT = "师父和师尊都不存在，免审批通过";

    public static final String LOG_NO_NEED_PERMIT = "申请退出，师父和师尊都不存在，免审批通过";

    public static final String SYSTEM = "系统";

    public static final Long SYS_USER = 0L;

    public static final String NONE_SECT = "无";

    public static final String INNER_MSG_TITLE = "门派内部邮件";

    public static final String KICK_MSG_TITLE = "系统消息";

    public static final String KICK_MSG_TEMPLATE = "系统监测你门下弟子{}在上期PK中，贡献值不足{}，是否将其踢出门派?";

    public static final String TERMINATE_REMARK = "解约技师自动踢出门派";

    /**
     * 追溯标识
     */
    public static final String TRACE_ID = "traceId";
    /**
     * 当前登录人的技师ID
     */
    public static final String SERVER_ID = "serverId";
    /**
     * 当前登录人的“平台名|真实名”
     */
    public static final String SERVER_NAME = "serverName";
    /**
     * 运营中心ID
     */
    public static final String MERCHANT_ID = "merchantId";
    /**
     * 票据
     */
    public static final String TOKEN = "token";
    /**
     * 调试标识key
     */
    public static final String DEBUG_KEY = "debug";
    /**
     * 调试标识value
     */
    public static final String DEBUG_VALUE = "true";
    /**
     * 管理端来源标识key
     */
    public static final String DJDJ_SOURCE_KEY = "Djdj-Source";
    /**
     * 管理端来源标识value
     */
    public static final String DJDJ_SOURCE_VALUE = "djdj-super-admin v4.0";
    /**
     * 管理端用户ID
     */
    public static final String ADMIN_USER_ID = "adminUserId";
    /**
     * 管理端用户名称
     */
    public static final String ADMIN_USER_NAME = "adminUserName";
    /**
     * 管理端用户所属运营中心ID
     */
    public static final String ADMIN_MERCHANT_ID = "adminMerchantId";
    /**
     * 管理端请求标识
     */
    public static final String ADMIN_MARK = "adminMark";
    /**
     * 耗时
     */
    public static final String TIME_COST = "timeCost";
    /**
     * 告警
     */
    public static final String ALARM = "alarm";
    /**
     * 请求参数
     */
    public static final String REQ = "req";
    /**
     * 响应参数
     */
    public static final String RESP = "resp";
    /**
     * 请求头参数
     */
    public static final String HEAD = "head";
    /**
     * 请求接口路径
     */
    public static final String URI = "uri";
    /**
     * 请求客户端IP
     */
    public static final String IP = "ip";

    public static final String NORMAL = "正常";

    public static final String HIDE = "隐藏";

    public static final String INIT_GRADE = "0";

    public static final String GLORY_SCORE_LEVEL_UP_REMARK = "门派升级消耗积分{}";

    public static final String GLORY_SCORE_MEETING_AUDITING_REMARK = "门派讲座审核通过加积分 {}";
    public static final String GIFT_GLORY_SCORE_REMARK = "荣耀分赠送审核通过加积分 {}";
    public static final String DEDUCT_GLORY_SCORE_REMARK = "荣耀分扣减审核通过减积分 {}";

    public static final String GRADE_SEPARATOR = "-";

    public static final int MAX_GRADE = 10;

    public static final int MAX_SUB_GRADE = 3;

    public static final int GRADE_START = 1;

    // 无门派时，门派ID为0.（语音通知号码时）
    public static final Long NO_SECT = 0L;

    // 日期格式：年-月-日
    public static final String DATE_FORMAT_YMD = "yyyy-MM-dd";

    // 日期格式：年-月-日 时:分:秒
    public static final String DATE_FORMAT_YMD_HMS = "yyyy-MM-dd HH:mm:ss";

    public static final int MAX_CONCURRENCY_PER_THREAD = 30;

    public static final int MAX_ASSESS_MONTHS = 3;

    public static final String DAY_BOUNDARY = "{} 08:00:00";

    public static final Long HEAD_QUARTER = 0L;

    public static final Long NOT_MATCHED = -1L;

    public static final String NOT_EXIST = "无";

    public static final String PK_FAIL = "PK失败";

    public static final String ORDER_FAIL = "（订单退款）";
    public static final String PROFIT_FAIL = "存在打款失败的金额";
    // 请求服务来源--固定为BI
    public static final String BI = "bi";
}
