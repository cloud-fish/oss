package com.example.agentscope.service.impl;

import com.example.agentscope.config.AgentProperties;
import com.example.agentscope.service.OssAgentService;
import com.example.agentscope.tool.FaultDiagnosisTools;
import com.example.agentscope.tool.NetworkMonitorTools;
import com.example.agentscope.tool.ResourceManageTools;
import com.example.agentscope.tool.WorkOrderTools;
import io.agentscope.core.ReActAgent;
import io.agentscope.core.message.Msg;
import io.agentscope.core.model.OllamaChatModel;
import io.agentscope.core.tool.Toolkit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * OSS智能运维服务实现
 * 使用AgentScope的ReAct Agent实现运营商OSS业务场景
 */
@Service
public class OssAgentServiceImpl implements OssAgentService {

    private static final Logger log = LoggerFactory.getLogger(OssAgentServiceImpl.class);

    private final OllamaChatModel chatModel;
    private final AgentProperties agentProperties;

    public OssAgentServiceImpl(OllamaChatModel chatModel, AgentProperties agentProperties) {
        this.chatModel = chatModel;
        this.agentProperties = agentProperties;
    }

    @Override
    public String diagnoseFault(String alarmContent) {
        log.info("故障诊断Agent处理告警: {}", alarmContent);

        Toolkit toolkit = new Toolkit();
        toolkit.registerTool(new FaultDiagnosisTools());

        ReActAgent agent = buildAgent("故障诊断专家", toolkit,
                "你是运营商OSS系统的故障诊断专家。\n" +
                "你的职责是：\n" +
                "1. 分析网络告警信息\n" +
                "2. 查询设备日志，提取关键错误\n" +
                "3. 诊断故障根因\n" +
                "4. 提供处理建议\n\n" +
                "请根据告警内容，使用工具进行诊断分析。回答要专业、准确。",
                agentProperties.getReact().getMaxIterations());

        return callAgent(agent, "请分析以下告警: " + alarmContent);
    }

    @Override
    public String monitorNetwork(String query) {
        log.info("网络监控Agent处理查询: {}", query);

        Toolkit toolkit = new Toolkit();
        toolkit.registerTool(new NetworkMonitorTools());

        ReActAgent agent = buildAgent("网络监控专家", toolkit,
                "你是运营商OSS系统的网络监控专家。\n" +
                "你的职责是：\n" +
                "1. 查询设备性能指标（带宽、时延、丢包率等）\n" +
                "2. 分析网络质量\n" +
                "3. 监控设备运行状态\n" +
                "4. 检查链路状态和流量\n\n" +
                "请使用工具查询相关性能数据并生成报告。",
                agentProperties.getMulti().getMaxIterations());

        return callAgent(agent, query);
    }

    @Override
    public String manageResource(String query) {
        log.info("资源管理Agent处理查询: {}", query);

        Toolkit toolkit = new Toolkit();
        toolkit.registerTool(new ResourceManageTools());

        ReActAgent agent = buildAgent("资源管理专家", toolkit,
                "你是运营商OSS系统的资源管理专家。\n" +
                "你的职责是：\n" +
                "1. 查询网络设备信息\n" +
                "2. 查询端口信息和状态\n" +
                "3. 分配网络资源（端口、带宽、VLAN）\n" +
                "4. 统计资源使用情况\n\n" +
                "请使用工具查询资源信息或执行资源分配。",
                agentProperties.getMulti().getMaxIterations());

        return callAgent(agent, query);
    }

    @Override
    public String manageWorkOrder(String orderRequest) {
        log.info("工单管理Agent处理请求: {}", orderRequest);

        Toolkit toolkit = new Toolkit();
        toolkit.registerTool(new WorkOrderTools());

        ReActAgent agent = buildAgent("工单管理专家", toolkit,
                "你是运营商OSS系统的工单管理专家。\n" +
                "你的职责是：\n" +
                "1. 创建故障工单\n" +
                "2. 派发工单给处理人员\n" +
                "3. 查询工单状态\n" +
                "4. 更新工单状态\n\n" +
                "请使用工具处理工单相关请求。",
                agentProperties.getMulti().getMaxIterations());

        return callAgent(agent, orderRequest);
    }

    @Override
    public String collaborativeFaultHandling(String issue) {
        log.info("故障协同处理: {}", issue);

        // 步骤1: 网络监控Agent发现问题
        log.info("步骤1: 网络监控Agent分析网络状态");
        String monitorResult = monitorNetwork("请检查网络状态，分析是否存在异常: " + issue);
        log.info("监控结果: {}", monitorResult);

        // 步骤2: 故障诊断Agent定位问题
        log.info("步骤2: 故障诊断Agent定位故障根因");
        String diagnosisResult = diagnoseFault(issue);
        log.info("诊断结果: {}", diagnosisResult);

        // 步骤3: 工单管理Agent创建工单
        log.info("步骤3: 工单管理Agent创建故障工单");
        String orderResult = manageWorkOrder("请创建故障工单，标题: " + issue + "，优先级: P1");
        log.info("工单结果: {}", orderResult);

        // 汇总结果
        return String.format("=== 故障协同处理报告 ===\n\n" +
                "【问题描述】\n%s\n\n" +
                "【监控分析】\n%s\n\n" +
                "【故障诊断】\n%s\n\n" +
                "【工单处理】\n%s\n\n" +
                "=== 处理完成 ===",
                issue, monitorResult, diagnosisResult, orderResult);
    }

    private ReActAgent buildAgent(String name, Toolkit toolkit, String sysPrompt, int maxIters) {
        return ReActAgent.builder()
                .name(name)
                .sysPrompt(sysPrompt)
                .model(chatModel)
                .toolkit(toolkit)
                .maxIters(maxIters)
                .build();
    }

    private String callAgent(ReActAgent agent, String message) {
        log.info("Agent [{}] 开始处理: {}", agent.getName(), message);

        Msg msg = Msg.builder()
                .textContent(message)
                .build();

        try {
            Msg response = agent.call(msg).block();
            if (response == null) {
                log.error("Agent [{}] 返回 null，请检查 Ollama 模型是否正常运行", agent.getName());
                return "Agent 未返回结果，请检查 Ollama 模型服务是否正常（ollama serve）";
            }
            String result = response.getTextContent();
            log.info("Agent [{}] 处理完成", agent.getName());
            return result;
        } catch (Exception e) {
            log.error("Agent [{}] 调用异常: {}", agent.getName(), e.getMessage(), e);
            return "Agent 调用异常: " + e.getMessage();
        }
    }
}
