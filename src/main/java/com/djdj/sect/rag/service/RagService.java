package com.djdj.sect.rag.service;

import com.djdj.sect.rag.dto.ChatRequest;
import com.djdj.sect.rag.dto.ChatResponse;

public interface RagService {

    /** 录入文本知识 */
    void loadText(String content, String sourceId);

    /** RAG 问答 */
    ChatResponse chat(ChatRequest request);
}
