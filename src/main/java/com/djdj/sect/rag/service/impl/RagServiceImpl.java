package com.djdj.sect.rag.service.impl;

import com.djdj.sect.rag.dto.ChatRequest;
import com.djdj.sect.rag.dto.ChatResponse;
import com.djdj.sect.rag.service.RagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RagServiceImpl implements RagService {

    private final VectorStore vectorStore;

    private final ChatClient chatClient;

    @Override
    public void loadText(String content, String sourceId) {
        Document doc = Document.builder()
                .text(content)
                .metadata("source", sourceId)
                .build();

        TokenTextSplitter splitter = new TokenTextSplitter(512, 64, 5, 10000, true);
        List<Document> chunks = splitter.apply(List.of(doc));
        vectorStore.add(chunks);
        log.info("知识录入成功：{} 个片段，来源：{}", chunks.size(), sourceId);
    }

    @Override
    public ChatResponse chat(ChatRequest request) {
        // 1. 相似度检索
        List<Document> docs = vectorStore.similaritySearch(
                // 新写法（1.0.0 正式版）
                SearchRequest.builder()
                        .query(request.getQuestion())
                        .topK(request.getTopK())
                        .similarityThreshold(0.5)
                        .build()
        );
        if (docs != null) {
            log.info("检索到 {} 个相关片段", docs.size());
        }

        // 2. 拼装上下文
        String context = null;
        if (docs != null) {
            context = docs.stream()
                    .map(Document::getText)
                    .collect(Collectors.joining("\n\n---\n\n"));
        }

        // 3. 调用模型
        String systemPrompt = """
                你是一个知识库问答助手。
                请根据下面的【参考资料】回答用户问题。
                如果参考资料中没有相关内容，请回答"知识库中暂无相关信息"。
                
                【参考资料】
                %s
                """.formatted(context);

        String answer = chatClient.prompt()
                .system(systemPrompt)
                .user(request.getQuestion())
                .call()
                .content();

        // 4. 返回结果
        List<String> sourceChunks = null;
        if (docs != null) {
            sourceChunks = docs.stream()
                    .map(d -> "[" + d.getMetadata().getOrDefault("source", "未知").toString() + "] "
                            + Objects.requireNonNull(d.getText()).substring(0, Math.min(150, d.getText().length())) + "...")
                    .toList();
        }
        ChatResponse chatResponse = new ChatResponse();
        chatResponse.setAnswer(answer);
        chatResponse.setSourceChunks(sourceChunks);
        return chatResponse;
    }
}
