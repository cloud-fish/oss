package com.example.agentscope.config;

import io.agentscope.core.model.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Ollama 模型配置
 * 
 * 使用 AgentScope 原生 OllamaChatModel，直接调用 Ollama API
 * 默认地址: http://localhost:11434
 */
@Configuration
public class OllamaConfig {

    @Bean
    public OllamaChatModel ollamaChatModel(OllamaProperties properties) {
        return OllamaChatModel.builder()
                .modelName(properties.getModelName())
                .baseUrl(properties.getBaseUrl())
                .build();
    }
}
