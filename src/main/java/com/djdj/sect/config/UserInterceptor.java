//package com.djdj.sect.config;
//
//import cn.hutool.core.text.CharSequenceUtil;
//import com.djdj.sect.constant.SectConstant;
//import com.djdj.sect.enums.ResultEnum;
//import com.djdj.sect.feign.resp.UserResp;
//import com.djdj.sect.utils.BizException;
//import jakarta.annotation.Resource;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.jetbrains.annotations.NotNull;
//import org.slf4j.MDC;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import java.util.Optional;
//
///**
// * 用户信息拦截器
// */
//@Component
//@Slf4j
//public class UserInterceptor implements HandlerInterceptor {
//
//    @Resource
//    @Lazy
//    private IAuthRpcService authsRpcService;
//
//    @Override
//    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
//        getToken(request).ifPresentOrElse(token -> {
//            // 根据token获取管理端用户
//            UserResp userResp = authsRpcService.getUserInfoByToken(token);
//            MDC.put(SectConstant.ADMIN_USER_ID, userResp.getId().toString());
//            MDC.put(SectConstant.ADMIN_USER_NAME, userResp.getNickname());
//            MDC.put(SectConstant.ADMIN_MERCHANT_ID, userResp.getMerchantId().toString());
//        }, () -> log.info("直接走系统测试使用,不需要token...."));
//        return true;
//    }
//
//    /**
//     * 获取参数值
//     * @param request
//     * @param key
//     * @return
//     */
//    private String getValue(HttpServletRequest request, String key) {
//        String value = request.getHeader(key);
//        if (CharSequenceUtil.isBlank(value)) {
//            value = request.getParameter(key);
//        }
//        return value;
//    }
//
//    /**
//     * 是否调试
//     * @param request
//     * @return
//     */
//    private boolean isDebug(HttpServletRequest request) {
//        String debug = getValue(request, SectConstant.DEBUG_KEY);
//        if (SectConstant.DEBUG_VALUE.equals(debug)) {
//            MDC.put(SectConstant.DEBUG_KEY, debug);
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    /**
//     * 是否管理端来源
//     * @param request
//     * @return
//     */
//    private boolean isAdminMark(HttpServletRequest request) {
//        String djdjSource = getValue(request, SectConstant.DJDJ_SOURCE_KEY);
//        if (SectConstant.DJDJ_SOURCE_VALUE.equals(djdjSource)) {
//            MDC.put(SectConstant.ADMIN_MARK, Boolean.TRUE.toString());
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    /**
//     * 获取token
//     * @param request
//     * @return
//     */
//    private Optional<String> getToken(HttpServletRequest request) {
//        String token = getValue(request, SectConstant.TOKEN);
//        if (CharSequenceUtil.isNotBlank(token)) {
//            MDC.put(SectConstant.TOKEN, token);
//            return Optional.of(token);
//        }
//        if (isDebug(request)) {
//            return Optional.empty();
//        } else {
//            throw new BizException(ResultEnum.LOGOUT, "token不能为空");
//        }
//    }
//
//}
