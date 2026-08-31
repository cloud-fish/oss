package com.example.agentscope.tool;

import io.agentscope.core.tool.Tool;
import io.agentscope.core.tool.ToolParam;

/**
 * 工单管理工具集 - OSS故障管理模块工单流转
 */
public class WorkOrderTools {

    @Tool(name = "create_work_order", description = "创建故障工单")
    public String createWorkOrder(
            @ToolParam(name = "title", description = "工单标题")
            String title,
            @ToolParam(name = "description", description = "工单描述")
            String description,
            @ToolParam(name = "priority", description = "优先级: P1(紧急), P2(高), P3(中), P4(低)")
            String priority,
            @ToolParam(name = "device_name", description = "关联设备名称", required = false)
            String deviceName) {

        String orderId = "WO-20240115-" + (int)(Math.random() * 1000);

        return String.format("工单创建成功!\n\n" +
                "【工单信息】\n" +
                "  工单编号: %s\n" +
                "  工单标题: %s\n" +
                "  优先级: %s\n" +
                "  关联设备: %s\n" +
                "  创建时间: 2024-01-15 14:35:00\n" +
                "  工单状态: 已创建\n\n" +
                "【下一步操作】\n" +
                "1. 派发工单给处理人员\n" +
                "2. 处理人员接单处理\n" +
                "3. 处理完成后反馈结果\n" +
                "4. 验证关闭工单",
                orderId, title, priority, deviceName != null ? deviceName : "无");
    }

    @Tool(name = "dispatch_work_order", description = "派发工单给处理人员")
    public String dispatchWorkOrder(
            @ToolParam(name = "order_id", description = "工单编号")
            String orderId,
            @ToolParam(name = "assignee", description = "处理人/处理组，如 '传输组', '张三'")
            String assignee) {

        return String.format("工单派发成功!\n\n" +
                "【派发信息】\n" +
                "  工单编号: %s\n" +
                "  派发对象: %s\n" +
                "  派发时间: 2024-01-15 14:40:00\n" +
                "  工单状态: 已派发\n\n" +
                "【通知】\n" +
                "已发送短信和邮件通知给 %s",
                orderId, assignee, assignee);
    }

    @Tool(name = "query_work_orders", description = "查询工单列表")
    public String queryWorkOrders(
            @ToolParam(name = "status", description = "工单状态过滤: CREATED, ASSIGNED, PROCESSING, RESOLVED, CLOSED，不传则查所有", required = false)
            String status,
            @ToolParam(name = "priority", description = "优先级过滤: P1, P2, P3, P4，不传则查所有", required = false)
            String priority) {

        StringBuilder result = new StringBuilder();
        result.append("工单列表:\n\n");

        result.append("1. WO-20240115-001 | P1 | OLT上行链路中断 | 处理中 | 传输组\n");
        result.append("   创建时间: 2024-01-15 14:30 | 处理人: 李工\n\n");

        result.append("2. WO-20240115-002 | P2 | 基站退服 | 已派发 | 无线组\n");
        result.append("   创建时间: 2024-01-15 14:25 | 处理人: 王工\n\n");

        result.append("3. WO-20240115-003 | P3 | 端口流量超限 | 已解决 | 数据组\n");
        result.append("   创建时间: 2024-01-15 14:20 | 处理人: 张工\n\n");

        if (status != null) {
            result.append(String.format("[已过滤状态: %s]\n", status));
        }
        if (priority != null) {
            result.append(String.format("[已过滤优先级: %s]\n", priority));
        }

        return result.toString();
    }

    @Tool(name = "update_work_order_status", description = "更新工单状态")
    public String updateWorkOrderStatus(
            @ToolParam(name = "order_id", description = "工单编号")
            String orderId,
            @ToolParam(name = "new_status", description = "新状态: ASSIGNED(已派发), PROCESSING(处理中), RESOLVED(已解决), CLOSED(已关闭)")
            String newStatus,
            @ToolParam(name = "remark", description = "备注说明", required = false)
            String remark) {

        return String.format("工单状态更新成功!\n\n" +
                "【更新信息】\n" +
                "  工单编号: %s\n" +
                "  新状态: %s\n" +
                "  更新时间: 2024-01-15 15:00:00\n" +
                "  备注: %s",
                orderId, newStatus, remark != null ? remark : "无");
    }
}
