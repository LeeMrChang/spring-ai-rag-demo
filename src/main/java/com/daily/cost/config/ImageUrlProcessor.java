//package com.djdj.cost.config;
//
//import cn.hutool.core.text.CharSequenceUtil;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
///**
// * 头像url修正
// */
//@Component
//public class ImageUrlProcessor {
//    @Value("${djdj.image.server.domain:}")
//    private String domain;
//
//    public String fixUrl(String url) {
//        if (CharSequenceUtil.isBlank(url) || CharSequenceUtil.startWith(url, "http://") || CharSequenceUtil.startWith(url, "https://")) {
//            return url;
//        }
//        url = CharSequenceUtil.startWith(url, "/") ? url : "/" + url;
//        return domain + url;
//    }
//}
