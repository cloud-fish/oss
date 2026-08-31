package com.example.agentscope.service;

/**
 * OSS智能运维服务接口 - 运营商OSS业务
 */
public interface OssAgentService {

    /**
     * 故障诊断 - 分析告警信息，定位故障根因
     *
     * @param alarmContent 告警内容描述
     * @return 诊断结果
     */
    String diagnoseFault(String alarmContent);

    /**
     * 网络监控 - 查询性能指标，分析网络质量
     *
     * @param query 查询内容（如：上海区域网络质量）
     * @return 监控结果
     */
    String monitorNetwork(String query);

    /**
     * 资源管理 - 查询设备信息，分配资源
     *
     * @param query 查询内容（如：OLT-SH-01的端口信息）
     * @return 资源信息
     */
    String manageResource(String query);

    /**
     * 工单管理 - 创建工单，派发工单
     *
     * @param orderRequest 工单请求描述
     * @return 工单处理结果
     */
    String manageWorkOrder(String orderRequest);

    /**
     * 故障协同处理 - 多Agent协同：监控发现问题 → 诊断定位 → 生成工单
     *
     * @param issue 故障问题描述
     * @return 协同处理结果
     */
    String collaborativeFaultHandling(String issue);
}
