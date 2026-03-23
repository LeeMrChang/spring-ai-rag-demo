package com.djdj.sect.rag.dto;

import lombok.Data;

@Data
public class ChatRequest {

    private String question;
    private int topK = 4;
}
