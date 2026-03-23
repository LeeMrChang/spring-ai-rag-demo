package com.djdj.sect.rag.controller;

import com.djdj.sect.rag.dto.ChatRequest;
import com.djdj.sect.rag.dto.ChatResponse;
import com.djdj.sect.rag.service.RagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/rag")
@RequiredArgsConstructor
public class RagController {

    private final RagService ragService;

    /** 录入文本知识 */
    @PostMapping("/load")
    public Map<String, String> load(@RequestBody Map<String, String> body) {
        ragService.loadText(
                body.getOrDefault("content", ""),
                body.getOrDefault("sourceId", "manual")
        );
        return Map.of("status", "ok");
    }

    /** RAG 问答 */
    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody ChatRequest request) {
        return ragService.chat(request);
    }
}
