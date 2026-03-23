package com.djdj.sect.rag.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChatResponse {

    private String answer;
    private List<String> sourceChunks;
}
