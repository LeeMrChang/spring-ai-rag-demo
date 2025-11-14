package com.daily.cost.feign.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BaseResp<T> {

    private Integer code;

    private String msg;

    private T data;

    private MetaInfo meta;

    @Data
    public static class MetaInfo {

        @JsonProperty("request_id")
        private String requestId;

        @JsonProperty("trace_id")
        private String traceId;

        @JsonProperty("span_level")
        private String spanLevel;

    }
}
