//package com.djdj.cost.config.fixurl;
//
//import cn.hutool.extra.spring.SpringUtil;
//import com.djdj.cost.config.ImageUrlProcessor;
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.databind.BeanProperty;
//import com.fasterxml.jackson.databind.JsonSerializer;
//import com.fasterxml.jackson.databind.SerializerProvider;
//import com.fasterxml.jackson.databind.ser.ContextualSerializer;
//
//import java.io.IOException;
//
///**
// * 修正图片地址（旧版本数据的URL缺失域名）
// */
//public class FixUrlProcessor extends JsonSerializer<String> implements ContextualSerializer {
//
//    private boolean needFix = false;
//
//    @Override
//    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
//        if (needFix && value != null) {
//            // 自定义序列化逻辑
//            ImageUrlProcessor bean = SpringUtil.getBean(ImageUrlProcessor.class);
//            gen.writeString(bean.fixUrl(value));
//        } else {
//            gen.writeObject(value);
//        }
//    }
//
//    @Override
//    public JsonSerializer<?> createContextual(SerializerProvider serializer, BeanProperty prop) {
//        if (prop != null) {
//            FixUrl anno = prop.getAnnotation(FixUrl.class);
//            if (anno != null) {
//                needFix = true;
//            }
//        }
//        return this;
//    }
//}
