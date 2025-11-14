package com.daily.cost.aspect;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import com.daily.cost.utils.AspectUtil;
import com.daily.cost.utils.BizException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Feign rpc接口异常统一处理切面
 */
@Component
@Aspect
public class FeignLogAspect {

    @Around("execution(* *..*Rpc.*(..))")
    public Object around(ProceedingJoinPoint point) {
        TimeInterval timer = DateUtil.timer();
        timer.start();
        Object proceed = null;
        Throwable ex = null;
        try {
            proceed = point.proceed();
            return proceed;
        } catch (BizException e) {
            ex = e;
            throw e;
        } catch (Throwable e) {
            ex = e;
            String service = AspectUtil.getFeignService(point);
            String url = AspectUtil.getFeignUrI(point);
            throw new BizException(e, "调用rpc接口异常，服务：{}，接口：{}，异常信息：{}", service, url, e.getMessage());
        } finally {
            AspectUtil.printLog(AspectUtil.Scope.FEIGN, point, timer.interval(), proceed, ex);
        }
    }

}
