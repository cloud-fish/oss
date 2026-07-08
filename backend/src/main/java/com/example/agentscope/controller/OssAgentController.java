package com.example.agentscope.controller;

import com.example.agentscope.dto.AgentResponse;
import com.example.agentscope.service.OssAgentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * OSS智能运维REST接口
 * 提供运营商OSS业务的AI Agent能力
 */
@RestController
@RequestMapping("/api/oss")
public class OssAgentController {

    private static final Logger log = LoggerFactory.getLogger(OssAgentController.class);

    private final OssAgentService ossAgentService;

    public OssAgentController(OssAgentService ossAgentService) {
        this.ossAgentService = ossAgentService;
    }

    /**
     * 故障诊断接口
     * POST /api/oss/fault/diagnose
     * 
     * 请求示例: {"alarmContent": "OLT-SH-01 上行链路中断"}
     */
    @PostMapping("/fault/diagnose")
    public AgentResponse diagnoseFault(@RequestBody FaultDiagnoseRequest request) {
        log.info("收到故障诊断请求: {}", request.getAlarmContent());
        try {
            String result = ossAgentService.diagnoseFault(request.getAlarmContent());
            return AgentResponse.ok(result);
        } catch (Exception e) {
            log.error("故障诊断异常", e);
            return AgentResponse.fail(e.getMessage());
        }
    }

    /**
     * 网络监控接口
     * POST /api/oss/monitor/query
     * 
     * 请求示例: {"query": "上海区域网络质量如何？"}
     */
    @PostMapping("/monitor/query")
    public AgentResponse monitorNetwork(@RequestBody MonitorQueryRequest request) {
        log.info("收到网络监控请求: {}", request.getQuery());
        try {
            String result = ossAgentService.monitorNetwork(request.getQuery());
            return AgentResponse.ok(result);
        } catch (Exception e) {
            log.error("网络监控异常", e);
            return AgentResponse.fail(e.getMessage());
        }
    }

    /**
     * 资源管理接口
     * POST /api/oss/resource/query
     * 
     * 请求示例: {"query": "OLT-SH-01有哪些可用端口？"}
     */
    @PostMapping("/resource/query")
    public AgentResponse manageResource(@RequestBody ResourceQueryRequest request) {
        log.info("收到资源管理请求: {}", request.getQuery());
        try {
            String result = ossAgentService.manageResource(request.getQuery());
            return AgentResponse.ok(result);
        } catch (Exception e) {
            log.error("资源管理异常", e);
            return AgentResponse.fail(e.getMessage());
        }
    }

    /**
     * 工单管理接口
     * POST /api/oss/workorder/create
     * 
     * 请求示例: {"orderRequest": "创建故障工单，OLT上行链路中断，优先级P1"}
     */
    @PostMapping("/workorder/create")
    public AgentResponse manageWorkOrder(@RequestBody WorkOrderRequest request) {
        log.info("收到工单管理请求: {}", request.getOrderRequest());
        try {
            String result = ossAgentService.manageWorkOrder(request.getOrderRequest());
            return AgentResponse.ok(result);
        } catch (Exception e) {
            log.error("工单管理异常", e);
            return AgentResponse.fail(e.getMessage());
        }
    }

    /**
     * 故障协同处理接口
     * POST /api/oss/collaborate
     * 
     * 请求示例: {"issue": "OLT-SH-01 上行链路中断，影响1200户宽带用户"}
     * 
     * 协同流程: 监控发现问题 → 诊断定位根因 → 创建故障工单
     */
    @PostMapping("/collaborate")
    public AgentResponse collaborativeFaultHandling(@RequestBody CollaborateRequest request) {
        log.info("收到故障协同处理请求: {}", request.getIssue());
        try {
            String result = ossAgentService.collaborativeFaultHandling(request.getIssue());
            return AgentResponse.ok(result);
        } catch (Exception e) {
            log.error("故障协同处理异常", e);
            return AgentResponse.fail(e.getMessage());
        }
    }

    // ========== 请求DTO内部类 ==========

    public static class FaultDiagnoseRequest {
        private String alarmContent;
        public String getAlarmContent() { return alarmContent; }
        public void setAlarmContent(String alarmContent) { this.alarmContent = alarmContent; }
    }

    public static class MonitorQueryRequest {
        private String query;
        public String getQuery() { return query; }
        public void setQuery(String query) { this.query = query; }
    }

    public static class ResourceQueryRequest {
        private String query;
        public String getQuery() { return query; }
        public void setQuery(String query) { this.query = query; }
    }

    public static class WorkOrderRequest {
        private String orderRequest;
        public String getOrderRequest() { return orderRequest; }
        public void setOrderRequest(String orderRequest) { this.orderRequest = orderRequest; }
    }

    public static class CollaborateRequest {
        private String issue;
        public String getIssue() { return issue; }
        public void setIssue(String issue) { this.issue = issue; }
    }
}
