package com.example.agentscope.dto;

/**
 * Agent 响应 DTO
 */
public class AgentResponse {

    /** 是否成功 */
    private boolean success;

    /** Agent 回复内容 */
    private String reply;

    /** 错误信息 (失败时) */
    private String error;

    public AgentResponse() {
    }

    public static AgentResponse ok(String reply) {
        AgentResponse response = new AgentResponse();
        response.setSuccess(true);
        response.setReply(reply);
        return response;
    }

    public static AgentResponse fail(String error) {
        AgentResponse response = new AgentResponse();
        response.setSuccess(false);
        response.setError(error);
        return response;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
