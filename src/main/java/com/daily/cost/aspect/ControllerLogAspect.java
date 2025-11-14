package com.daily.cost.aspect;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import com.daily.cost.utils.AspectUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 控制器日志切面
 */
@Component
@Aspect
public class ControllerLogAspect {

    /**
     * 环绕通知，执行类，执行方法，调用参数
     *
     * @param point point
     */
    @Around("execution(* *..*Controller.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        TimeInterval timer = DateUtil.timer();
        timer.start();
        Object proceed = null;
        Throwable ex = null;
        try {
            proceed = point.proceed();
            return proceed;
        } catch (Throwable e) {
            ex = e;
            throw e;
        } finally {
            AspectUtil.printLog(AspectUtil.Scope.CONTROLLER, point, timer.interval(), proceed, ex);
        }
    }

}
