package com.example.agentscope.dto;

/**
 * Agent 请求 DTO
 */
public class AgentRequest {

    /** 用户输入的问题/指令 */
    private String message;

    /** 指定 Agent 类型 (可选): react / researcher / writer */
    private String agentType;

    public AgentRequest() {
    }

    public AgentRequest(String message) {
        this.message = message;
    }

    public AgentRequest(String message, String agentType) {
        this.message = message;
        this.agentType = agentType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAgentType() {
        return agentType;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }
}
