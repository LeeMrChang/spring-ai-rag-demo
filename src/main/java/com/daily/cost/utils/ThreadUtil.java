package com.daily.cost.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.LongFunction;

/**
 * 线程工具类
 */
public class ThreadUtil {

    private ThreadUtil() {}

    /**
     * 批量分组条数
     */
    private static final Integer PARTITION_SIZE = 100;

    public static void execute(Runnable command) {
        SpringUtil.getBean(SimpleAsyncTaskExecutor.class).execute(command);
    }

    public static <T> Future<T> submit(Callable<T> task) {
        return SpringUtil.getBean(SimpleAsyncTaskExecutor.class).submit(task);
    }

    /**
     * 批量分组submit
     * @param serverIds
     * @param function
     * @return
     * @param <T>
     */
    public static <T> Map<Long, T> submitBatch(List<Long> serverIds, LongFunction<T> function) {
        Map<Long, T> map = new ConcurrentHashMap<>();
        if (CollUtil.isEmpty(serverIds)) {
            return map;
        }
        ListUtil.partition(serverIds, PARTITION_SIZE).forEach(subList -> subList.stream().map(serverId -> submit(() -> {
            T apply = function.apply(serverId);
            if (ObjectUtil.isNotEmpty(apply)) {
                map.put(serverId, apply);
            }
            return true;
        })).forEach(ThreadUtil::getFuture));
        return map;
    }

    /**
     * 批量分组submit
     * @param serverIds
     * @param function
     * @param consumer
     * @return
     * @param <T>
     * @param <R>
     */
    public static <T, R> Map<Long, T> submitBatch(List<Long> serverIds, Function<List<Long>, R> function, BiConsumer<Map<Long, T>, R> consumer) {
        Map<Long, T> map = new ConcurrentHashMap<>();
        if (CollUtil.isEmpty(serverIds)) {
            return map;
        }
        ListUtil.partition(serverIds, PARTITION_SIZE).stream().map(subList -> submit(() -> {
            consumer.accept(map, function.apply(subList));
            return true;
        })).forEach(ThreadUtil::getFuture);
        return map;
    }

    /**
     * 分组并行forEach
     * @param list
     * @param consumer
     * @param <T>
     */
    public static <T> void forEachParallel(List<T> list, Consumer<T> consumer) {
        if (CollUtil.isEmpty(list)) {
            return;
        }
        ListUtil.partition(list, PARTITION_SIZE).forEach(subList -> subList.stream().map(item -> submit(() -> {
            consumer.accept(item);
            return true;
        })).forEach(ThreadUtil::getFuture));
    }

    private static void getFuture(Future<Boolean> future) {
        try {
            future.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new BizException(e);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof BizException bizException) {
                throw bizException;
            } else {
                throw new BizException(e);
            }
        }
    }

}
