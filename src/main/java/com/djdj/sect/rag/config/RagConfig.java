package com.djdj.sect.rag.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.redis.RedisVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPooled;

@Configuration
public class RagConfig {

    @Bean
    public ChatClient chatClient(OllamaChatModel chatModel) {
        return ChatClient.builder(chatModel).build();
    }

    @Bean
    public VectorStore vectorStore(EmbeddingModel embeddingModel) {
        // 直接用 Jedis 连本地 redis-stack
        JedisPooled jedis = new JedisPooled("localhost", 6379);
        return RedisVectorStore.builder(jedis, embeddingModel)
                .indexName("rag-knowledge-index")
                .prefix("doc:")
                .initializeSchema(true)
                .build();
    }
}
