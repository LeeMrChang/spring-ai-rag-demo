# 🤖 Spring AI + Ollama + Redis RAG Demo

<div align="center">

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.4-6DB33F?style=flat-square&logo=springboot)
![Spring AI](https://img.shields.io/badge/Spring%20AI-1.0.0-6DB33F?style=flat-square&logo=spring)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2023.0.3-6DB33F?style=flat-square&logo=spring)
![JDK](https://img.shields.io/badge/JDK-21+-orange?style=flat-square&logo=openjdk)
![Ollama](https://img.shields.io/badge/Ollama-latest-black?style=flat-square)
![Redis](https://img.shields.io/badge/Redis%20Stack-latest-DC382D?style=flat-square&logo=redis)
![License](https://img.shields.io/badge/License-MIT-blue?style=flat-square)

**基于 Spring AI + Ollama + Redis 向量数据库实现的知识库增强问答（RAG）Demo**

[快速开始](#-快速开始) · [链路说明](#-rag-链路流程) · [API 文档](#-api-接口) · [常见问题](#-常见问题-faq)

</div>

---

## 📖 项目简介

本项目是一个完整的 **RAG（Retrieval-Augmented Generation，检索增强生成）** 实践 Demo，展示如何将私有知识库与本地大语言模型结合，实现基于私有数据的智能问答。

### 什么是 RAG？

> 传统大模型回答问题全靠训练时学到的知识，容易"幻觉"（一本正经地编造答案）。  
> RAG 的思路是：**先检索，再生成** —— 像开卷考试一样，先从知识库找到相关资料，再让模型基于资料作答。

```
没有 RAG：用户提问 → 模型凭记忆回答（可能编造）

有了 RAG：用户提问 → 检索知识库 → 相关片段 + 问题 → 模型回答（有据可查）
```

### 核心功能

- 📥 **知识入库**：支持文本录入，自动切分、向量化并存入 Redis
- 🔍 **语义检索**：基于向量相似度，精准召回相关知识片段
- 💬 **智能问答**：结合检索结果生成有依据的回答，返回引用来源
- 🚫 **防幻觉**：知识库外的问题明确告知无法回答，不编造内容

---

## 🛠 技术栈

| 组件 | 版本 | 说明 |
|------|------|------|
| JDK | 21+ | 推荐 OpenJDK 21，支持虚拟线程 |
| Spring Boot | 3.3.4 | 最低 3.3.x，与 Spring AI 1.0.0 对齐 |
| Spring AI | 1.0.0 | 正式版，已发布至 Maven Central |
| Spring Cloud | 2023.0.3 | 兼容 Spring Boot 3.3.x |
| Ollama | latest | 本地 LLM 运行时 |
| Redis Stack | latest | 含 RediSearch 向量检索模块 |
| Docker Desktop | latest | 运行 Redis Stack 容器 |

### AI 模型

| 模型 | 大小 | 用途 |
|------|------|------|
| `nomic-embed-text` | ~274MB | 向量嵌入模型（**必须安装**） |
| `gemma3:1b` | ~778MB | 对话模型（推荐，体积小） |
| `llama3` | ~4.7GB | 对话模型（效果更好，但较大） |

> ⚠️ **注意**：`nomic-embed-text` 必须安装，它负责将文本转为向量，缺少它知识入库和检索都会报错。

---

## 🚀 快速开始

### 前置条件

- JDK 21+
- Maven 3.6+
- Docker Desktop（已启动）
- Ollama（已安装）

### 第一步：启动 Redis Stack

```bash
docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 redis/redis-stack:latest
```

> 启动后访问 [http://localhost:8001](http://localhost:8001) 可打开 RedisInsight 可视化管理界面。

### 第二步：安装 Ollama 并拉取模型

```bash
# 安装 Ollama：https://ollama.com/download

# 拉取向量嵌入模型（必须）
ollama pull nomic-embed-text

# 拉取对话模型（二选一）
ollama pull gemma3:1b   # 推荐，体积小
ollama pull llama3      # 效果更好，但需要 4.7GB
```

### 第三步：配置 application.yml

```yaml
spring:
  ai:
    ollama:
      base-url: http://localhost:11434
      chat:
        options:
          model: gemma3:1b        # 改为你已下载的模型
          temperature: 0.7
      embedding:
        options:
          model: nomic-embed-text
    vectorstore:
      redis:
        index: rag-knowledge-index
        prefix: "doc:"
        initialize-schema: true
        uri: redis://localhost:6379
```

### 第四步：启动项目

```bash
mvn spring-boot:run
```

启动成功后访问 `http://localhost:9001`

---

## 🔄 RAG 链路流程

### 知识入库流程

```
原始文档 / 文本
      │
      ▼
TokenTextSplitter（文本切分）
每块 512 token，重叠 64 token 保证上下文连贯
      │
      ▼
OllamaEmbeddingModel（向量化）
调用 nomic-embed-text 将文本转为高维向量
      │
      ▼
RedisVectorStore（向量存储）
以 doc: 为前缀写入 Redis Stack，建立向量索引
```

### 问答检索流程

```
用户提问：POST /rag/chat
      │
      ▼
问题向量化
nomic-embed-text 将问题转为向量
      │
      ▼
Redis 向量相似度检索
similaritySearch(topK=4, threshold=0.5)
返回最相关的 N 个文档片段
      │
      ▼
构建增强 Prompt
将检索片段注入 System Prompt 作为【参考资料】
      │
      ▼
OllamaChatModel 生成回答
基于参考资料生成最终回答
      │
      ▼
返回结果
{ answer: 回答内容, sourceChunks: 引用片段列表 }
```

### 代码核心逻辑

```java
// RagServiceImpl.java 核心流程
public ChatResponse chat(ChatRequest request) {

    // 1. 相似度检索
    List<Document> docs = vectorStore.similaritySearch(
        SearchRequest.builder()
            .query(request.getQuestion())
            .topK(request.getTopK())
            .similarityThreshold(request.getThreshold())
            .build()
    );

    // 2. 拼装上下文
    String context = docs.stream()
        .map(Document::getText)
        .collect(Collectors.joining("\n\n---\n\n"));

    // 3. 调用模型生成回答
    String answer = chatClient.prompt()
        .system("你是知识库问答助手，根据【参考资料】回答问题。\n\n【参考资料】\n" + context)
        .user(request.getQuestion())
        .call()
        .content();

    return ChatResponse.builder().answer(answer)
        .sourceChunks(/* 引用片段列表 */).build();
}
```

---

## 📡 API 接口

### 录入文本知识

```
POST /rag/load
Content-Type: application/json
```

**请求体**

```json
{
    "content": "深圳市共有9个区，分别是福田区、罗湖区、南山区...",
    "sourceId": "shenzhen-intro"
}
```

**响应**

```json
{
    "status": "ok"
}
```

---

### RAG 智能问答

```
POST /rag/chat
Content-Type: application/json
```

**请求体**

| 字段 | 类型 | 默认值 | 说明 |
|------|------|--------|------|
| `question` | String | - | 用户问题 |
| `topK` | int | 4 | 检索返回的相关片段数量 |
| `threshold` | double | 0.5 | 相似度阈值（0~1，越高越严格） |

```json
{
    "question": "深圳有哪些区？",
    "topK": 3,
    "threshold": 0.5
}
```

**响应**

```json
{
    "answer": "根据参考资料，深圳市共有9个区：福田区、罗湖区、南山区...",
    "sourceChunks": [
        "[shenzhen-intro] 深圳市共有9个区，分别是福田区..."
    ]
}
```

---

### 完整测试示例

```bash
# 步骤 1：录入知识
curl -X POST http://localhost:9001/rag/load \
  -H "Content-Type: application/json" \
  -d '{
    "content": "武当派由张三丰创立，以太极拳闻名，讲究以柔克刚，四两拨千斤。",
    "sourceId": "wudang"
  }'

# 步骤 2：问答
curl -X POST http://localhost:9001/rag/chat \
  -H "Content-Type: application/json" \
  -d '{
    "question": "武当派的功夫有什么特点？",
    "topK": 3
  }'

# 步骤 3：测试知识边界（应返回"无相关信息"）
curl -X POST http://localhost:9001/rag/chat \
  -H "Content-Type: application/json" \
  -d '{
    "question": "华山派的绝技是什么？",
    "topK": 3
  }'
```

---

## 📁 项目结构

```
src/main/java/com/djdj/sect/rag/
├── config/
│   └── RagConfig.java          # ChatClient + VectorStore Bean 配置
├── controller/
│   └── RagController.java      # REST 接口层
├── service/
│   ├── RagService.java         # 接口定义
│   └── impl/
│       └── RagServiceImpl.java # 核心 RAG 逻辑实现
└── dto/
    ├── ChatRequest.java        # 请求 DTO（question / topK / threshold）
    └── ChatResponse.java       # 响应 DTO（answer / sourceChunks）
```

---

## ❗ 常见问题 FAQ

### 依赖报错：`spring-ai-ollama-spring-boot-starter not found`

Spring AI 1.0.0 重命名了所有 Starter，旧名称已废弃：

```xml
<!-- ❌ 旧名称（废弃） -->
<artifactId>spring-ai-ollama-spring-boot-starter</artifactId>
<artifactId>spring-ai-redis-store-spring-boot-starter</artifactId>

<!-- ✅ 新名称（1.0.0 正式版） -->
<artifactId>spring-ai-starter-model-ollama</artifactId>
<artifactId>spring-ai-starter-vector-store-redis</artifactId>
```

### Spring Boot 与 Cloud 版本不兼容

```
Spring Boot [3.3.4] is not compatible with this Spring Cloud release train
```

`spring-cloud-dependencies` 版本必须与 Boot 版本配套：

| Spring Boot | Spring Cloud |
|-------------|--------------|
| 3.2.x | 2023.0.0 |
| 3.3.x | **2023.0.3** |

### VectorStore Bean 找不到

自动配置有时不会触发，需要在 `RagConfig.java` 中手动声明：

```java
@Bean
public VectorStore vectorStore(EmbeddingModel embeddingModel) {
    JedisPooled jedis = new JedisPooled("localhost", 6379);
    return RedisVectorStore.builder(jedis, embeddingModel)
            .indexName("rag-knowledge-index")
            .prefix("doc:")
            .initializeSchema(true)
            .build();
}
```

### 404 model not found

```bash
ollama list          # 查看已安装模型
ollama pull gemma3:1b  # 下载模型
```

并确保 `application.yml` 中的 `model` 与已下载模型名称完全一致。

### 检索到 0 个片段

- 降低相似度阈值：`threshold: 0.3`
- 确认 `nomic-embed-text` 已安装：`ollama list`
- 确认知识已成功录入：访问 [http://localhost:8001](http://localhost:8001) 在 Browser 中搜索 `doc:` 前缀

### Spring AI 1.0.0 API 变更速查

| 旧写法（M 版本） | 新写法（1.0.0） |
|-----------------|----------------|
| `new Document(content)` | `Document.builder().text(content).build()` |
| `document.getContent()` | `document.getText()` |
| `SearchRequest.query().withTopK()` | `SearchRequest.builder().query().topK().build()` |
| `vectorstore.redis.index-name` | `vectorstore.redis.index` |

---

## 📄 License

[MIT License](LICENSE)

---

<div align="center">
  <sub>Built with ❤️ using Spring AI · Ollama · Redis Stack</sub>
</div>