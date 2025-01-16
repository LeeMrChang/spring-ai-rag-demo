package com.djdj.sect.aspect;

import com.djdj.sect.aspect.anno.RedisLock;
import com.djdj.sect.constant.RedisConstant;
import com.djdj.sect.utils.AspectUtil;
import com.djdj.sect.utils.BizException;
import jakarta.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis分布式锁切面
 */
@Component
@Aspect
public class RedisLockAspect {

    @Resource
    private RedissonClient redissonClient;

    @Around("@annotation(redisLock)")
    public Object around(ProceedingJoinPoint point, RedisLock redisLock) throws Throwable {
        String redisKey = redisLock.redisKey();
        String[] keyParams = redisLock.keyParams();
        Map<String, Object> methodArgMap = AspectUtil.getMethodArgMap(point);
        List<Object> params = new ArrayList<>();
        for (String keyParam : keyParams) {
            params.add(AspectUtil.getValue(keyParam, methodArgMap));
        }
        String lockKey = RedisConstant.format(redisKey, params);
        RLock lock = redissonClient.getLock(lockKey);
        if (!lock.tryLock(RedisConstant.SEC_10, RedisConstant.SEC_60, TimeUnit.SECONDS)) {
            throw new BizException("获取锁【{}】失败，请稍后重试", lockKey);
        }
        try {
            return point.proceed();
        } finally {
            lock.unlock();
        }
    }

}
