package com.example.agentscope.dto;

/**
 * 工单信息 - OSS故障管理模块工单流转
 */
public class WorkOrder {

    /** 工单ID */
    private String orderId;

    /** 工单类型: FAULT(故障工单), MAINTENANCE(维护工单), CONFIGURATION(配置工单) */
    private String orderType;

    /** 工单标题 */
    private String title;

    /** 工单描述 */
    private String description;

    /** 优先级: P1(紧急), P2(高), P3(中), P4(低) */
    private String priority;

    /** 工单状态: CREATED(已创建), ASSIGNED(已派发), PROCESSING(处理中), RESOLVED(已解决), CLOSED(已关闭) */
    private String status;

    /** 派发对象(处理人/处理组) */
    private String assignee;

    /** 关联设备 */
    private String relatedDevice;

    /** 创建时间 */
    private String createTime;

    public WorkOrder() {
    }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getOrderType() { return orderType; }
    public void setOrderType(String orderType) { this.orderType = orderType; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAssignee() { return assignee; }
    public void setAssignee(String assignee) { this.assignee = assignee; }

    public String getRelatedDevice() { return relatedDevice; }
    public void setRelatedDevice(String relatedDevice) { this.relatedDevice = relatedDevice; }

    public String getCreateTime() { return createTime; }
    public void setCreateTime(String createTime) { this.createTime = createTime; }
}
