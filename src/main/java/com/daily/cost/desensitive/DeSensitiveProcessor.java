package com.daily.cost.desensitive;

import cn.hutool.core.text.CharSequenceUtil;
import com.daily.cost.utils.BizException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;

/**
 * 脱敏序列化器
 */
public class DeSensitiveProcessor extends JsonSerializer<String> implements ContextualSerializer {

    private boolean useMask = false;
    private int prefix;
    private int suffix;
    private char mask;

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (useMask && value != null) {
            // 自定义序列化逻辑
            gen.writeString(hide(value, prefix, suffix, mask));
        } else {
            // 默认序列化逻辑
            gen.writeObject(value);
        }
    }

    // 掩码
    private String hide(String value, int prefix, int suffix, char mask) {
        if (prefix < 0 || suffix < 0) {
            throw new BizException("保留位数不能小于0");
        }
        if (CharSequenceUtil.isBlank(value)) {
            return CharSequenceUtil.EMPTY;
        }
        // 需要截取的长度不能超出原字符串长度
        int len = value.length();
        if ((prefix + suffix) >= len) {
            return CharSequenceUtil.replaceByCodePoint(value, 0, len, mask);
        }
        char[] origin = value.toCharArray();
        // 替换
        for (int i = prefix; i < len - suffix; i++) {
            origin[i] = mask;
        }
        return new String(origin);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializer, BeanProperty prop) {
        if (prop != null) {
            DeSensitive anno = prop.getAnnotation(DeSensitive.class);
            if (anno != null) {
                this.prefix = anno.prefix();
                this.suffix = anno.suffix();
                this.mask = anno.mask();
                useMask = true;
            }
        }
        return this;
    }
}
