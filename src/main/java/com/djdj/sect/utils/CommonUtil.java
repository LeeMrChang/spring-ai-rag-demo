package com.djdj.sect.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.text.CharSequenceUtil;
import com.djdj.sect.constant.SectConstant;
import com.djdj.sect.feign.req.ExcelHeaderReq;
import com.djdj.sect.feign.req.SectFundDataReq;
import com.xxl.job.core.context.XxlJobHelper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.djdj.sect.constant.SectConstant.*;

/**
 * @author lichanghao
 * @date 2024/7/18
 * @Desc
 */
@Slf4j
public class CommonUtil {

    private CommonUtil() {}

    /**
     *  针对集合某个元素对象属性进行过滤操作
     * @param keyExtractor keyExtractor
     * @return T+
     * @param <T> T
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * 过滤出没有交集的元素
     * @param a 数组1
     * @param b 数组2
     * @return 结果
     */
    public static Set<Long> filterNoIntersection(List<Long> a, List<Long> b) {
        Set<Long> setA = new HashSet<>(a);
        Set<Long> setB = new HashSet<>(b);
        setA.removeAll(setB);
        return setA;
    }

    /**
     * 注意：：：如果活动时间调整，需检查此处是否需要做相应的调整
     * <p>
     * 资金服务的时间转换(开始时间)
     * 资金服务的时间是以0点分隔，而门派服务的时间是以8点分隔
     *
     * @param start 活动开始时间
     * @return 转换后时间
     */
    public static Date processStartTimeForShopKeeper(Date start) {
        return DateUtil.beginOfDay(start);
    }

    /**
     * 注意：：：如果活动时间调整，需检查此处是否需要做相应的调整
     * <p>
     * 资金服务的时间转换(结束时间)
     * 资金服务的时间是以0点分隔，而门派服务的时间是以8点分隔
     *
     * @param end 活动结束时间
     * @return 转换后时间
     */
    public static Date processEndTimeForShopKeeper(Date end) {
        end = DateUtil.beginOfDay(end);
        return DateUtil.offsetSecond(end, -1);
    }

    /**
     * 门派等级编码转换为门派等级名称
     *
     * @param grade grade
     * @return String
     */
    public static String getGradeName(String grade) {
        grade = CharSequenceUtil.trim(grade);
        if (CharSequenceUtil.isBlank(grade)) {
            return CharSequenceUtil.EMPTY;
        }
        if (CharSequenceUtil.equals(grade, INIT_GRADE)) {
            return CharSequenceUtil.format(GRADE_NAME_TEMPLATE, INIT_GRADE, INIT_GRADE);
        }
        String[] split = grade.split("-");
        if (split.length < 2) {
            return CharSequenceUtil.EMPTY;
        }
        return CharSequenceUtil.format(GRADE_NAME_TEMPLATE, split[0], split[1]);
    }

    public static void doCronJob(String jobInfo, String params, Consumer<String> consumer) {
        if (CharSequenceUtil.isBlank(params)) {
            params = XxlJobHelper.getJobParam();
        }
        String traceId = UUID.fastUUID().toString(true);
        MDC.put(SectConstant.TRACE_ID, traceId);
        TimeInterval timer = DateUtil.timer();
        timer.start();
        XxlJobHelper.log(TASK_START, traceId, jobInfo);
        log.info(TASK_START, traceId, jobInfo);
        try {
            consumer.accept(params);
        } catch (Exception e) {
            XxlJobHelper.log(TASK_ERROR, traceId, e);
            log.error(TASK_ERROR, traceId, e);
            throw e;
        } finally {
            long interval = timer.interval();
            MDC.put(SectConstant.TIME_COST, String.valueOf(interval));
            XxlJobHelper.log(TASK_END, traceId, interval);
            log.info(TASK_END, traceId, interval);
            MDC.remove(SectConstant.TIME_COST);
        }
    }

    public static List<SectFundDataReq> getActivityDateByDay(String startDateStr, String endDateStr) {
        // 解析日期字符串为 LocalDate 对象
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);
        List<SectFundDataReq> dates = new ArrayList<>();
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            dates.add(new SectFundDataReq()
                    .setStartTime(date.format(DateTimeFormatter.ofPattern(SectConstant.YYYY_MM_DD)) + " 00:00:00")
                    .setEndTime(date.format(DateTimeFormatter.ofPattern(SectConstant.YYYY_MM_DD)) + " 23:59:59")
            );
        }
        return dates;
    }
}
