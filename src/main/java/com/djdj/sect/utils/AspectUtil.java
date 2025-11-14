package com.djdj.sect.utils;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.json.JSONUtil;
import com.djdj.sect.constant.SectConstant;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 切面工具类
 */
@Slf4j
public class AspectUtil {

    private AspectUtil() {}

    @Getter
    @AllArgsConstructor
    public enum Scope {

        CONTROLLER("controller"),
        FEIGN("feign"),
        METHOD("method"),

        ;

        private final String code;

    }

    private static final String LOG_FORMAT = "{} ---> {}#{} | cost#{}";

    public static Map<String, Object> getMethodArgMap(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Object[] args = point.getArgs();
        Parameter[] parameters = signature.getMethod().getParameters();
        Map<String, Object> methodArgMap = new HashMap<>();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            Object methodArg = args[i];
            methodArgMap.put(parameter.getName(), methodArg);
        }
        return methodArgMap;
    }

    public static Object getValue(String keyParam, Map<String, Object> methodArgMap) {
        if (keyParam.startsWith("#")) {
            keyParam = CharSequenceUtil.removePrefix(keyParam, "#");
            if (CharSequenceUtil.contains(keyParam, '.')) {
                String parameterName = CharSequenceUtil.subBefore(keyParam, ".", false);
                String expression = CharSequenceUtil.subAfter(keyParam, '.', false);
                Object methodArg = methodArgMap.get(parameterName);
                SpelParserConfiguration config = new SpelParserConfiguration(true, true);
                ExpressionParser parser = new SpelExpressionParser(config);
                return parser.parseExpression(expression).getValue(methodArg);
            } else {
                return methodArgMap.get(keyParam);
            }
        } else {
            return keyParam;
        }
    }

    public static void printLog(Scope scope, ProceedingJoinPoint point, long cost, Object proceed, Throwable ex) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        String clazz = signature.getDeclaringTypeName();
        String method = signature.getName();
        MDC.put(SectConstant.TIME_COST, String.valueOf(cost));
        if (cost > 2000) {
            MDC.put(SectConstant.ALARM, Boolean.TRUE.toString());
        }
        MDC.put(SectConstant.REQ, JSONUtil.toJsonStr(point.getArgs(), SectConstant.JSON_CONFIG));
        MDC.put(SectConstant.RESP, JSONUtil.toJsonStr(proceed, SectConstant.JSON_CONFIG));
        if (Scope.CONTROLLER.equals(scope)) {
            MDC.put(SectConstant.HEAD, getRequestHeads());
            MDC.put(SectConstant.URI, getRequestUri());
        } else if (Scope.FEIGN.equals(scope)) {
            MDC.put(SectConstant.URI, getFeignUrI(point));
        }
        switch (ex) {
            case null -> log.info(LOG_FORMAT, scope.getCode(), clazz, method, cost);
            case MethodValidationException e -> {
                MDC.put(SectConstant.RESP, e.getAllErrors().stream().map(MessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("；")));
                log.warn(LOG_FORMAT, scope.getCode(), clazz, method, cost);
            }
            case BizException e -> log.warn(LOG_FORMAT, scope.getCode(), clazz, method, cost, e);
            default -> log.error(LOG_FORMAT, scope.getCode(), clazz, method, cost, ex);
        }
        MDC.remove(SectConstant.TIME_COST);
        MDC.remove(SectConstant.ALARM);
        MDC.remove(SectConstant.REQ);
        MDC.remove(SectConstant.RESP);
        MDC.remove(SectConstant.HEAD);
        MDC.remove(SectConstant.URI);
    }

    public static String getFeignService(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<?> declaringType = signature.getDeclaringType();
        FeignClient annotation = declaringType.getAnnotation(FeignClient.class);
        return annotation.value();
    }

    public static String getFeignUrI(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method declaredMethod = signature.getMethod();
        String url;
        if (declaredMethod.isAnnotationPresent(PostMapping.class)) {
            PostMapping postMapping = declaredMethod.getAnnotation(PostMapping.class);
            url = postMapping.value()[0];
        } else if (declaredMethod.isAnnotationPresent(GetMapping.class)) {
            GetMapping getMapping = declaredMethod.getAnnotation(GetMapping.class);
            url = getMapping.value()[0];
        } else {
            url = "";
        }
        return url;
    }

    public static String getRequestIp() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return null == attributes ? null : attributes.getRequest().getRemoteAddr();
    }

    public static String getRequestUri() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return null == attributes ? null : attributes.getRequest().getRequestURI();
    }

    public static String getRequestHeads() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null == attributes) {
            return null;
        }
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> map = new HashMap<>();
        while (headerNames.hasMoreElements()) {
            String string = headerNames.nextElement();
            map.put(string, request.getHeader(string));
        }
        return JSONUtil.toJsonStr(map, SectConstant.JSON_CONFIG);
    }

    private static String toJsonStr(Object objects) {
        String jsonStr = JSONUtil.toJsonStr(objects, SectConstant.JSON_CONFIG);
        return CharSequenceUtil.isNotBlank(jsonStr) && jsonStr.length() > 15000 ? "字符长度超过10000不打印" : jsonStr;
    }

}
