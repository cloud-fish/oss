package com.example.agentscope.tool;

import io.agentscope.core.tool.Tool;
import io.agentscope.core.tool.ToolParam;

/**
 * 资源管理工具集 - OSS资源管理模块
 */
public class ResourceManageTools {

    @Tool(name = "query_device_info", description = "查询网络设备详细信息")
    public String queryDeviceInfo(
            @ToolParam(name = "device_name", description = "设备名称，如 OLT-SH-01")
            String deviceName) {

        return String.format("设备详细信息:\n\n" +
                "【基本信息】\n" +
                "  设备名称: %s\n" +
                "  设备类型: OLT (光线路终端)\n" +
                "  设备型号: MA5800-X17\n" +
                "  设备厂商: 华为\n" +
                "  软件版本: V800R020C00\n\n" +
                "【位置信息】\n" +
                "  机房: 上海浦东核心机房\n" +
                "  机架: A03-12\n" +
                "  机位: U20-U35\n\n" +
                "【网络信息】\n" +
                "  管理IP: 10.10.10.1\n" +
                "  所属区域: 上海\n" +
                "  所属网元: NE-SH-001\n\n" +
                "【端口资源】\n" +
                "  总端口数: 1280\n" +
                "  已用端口: 1200\n" +
                "  可用端口: 80\n" +
                "  利用率: 93.75%%",
                deviceName);
    }

    @Tool(name = "query_port_info", description = "查询设备端口信息")
    public String queryPortInfo(
            @ToolParam(name = "device_name", description = "设备名称")
            String deviceName,
            @ToolParam(name = "port_name", description = "端口名称，如 GE0/0/1，不传则查询所有端口", required = false)
            String portName) {

        if (portName != null) {
            return String.format("端口 %s:%s 详细信息:\n\n" +
                    "【端口信息】\n" +
                    "  端口类型: GE (千兆以太网)\n" +
                    "  端口状态: UP\n" +
                    "  速率: 1000Mbps\n" +
                    "  双工模式: 全双工\n\n" +
                    "【流量信息】\n" +
                    "  入向流量: 250Mbps\n" +
                    "  出向流量: 180Mbps\n" +
                    "  利用率: 25%%\n\n" +
                    "【连接信息】\n" +
                    "  对端设备: SW-SH-02\n" +
                    "  对端端口: GE1/0/5\n" +
                    "  链路类型: 上行链路",
                    deviceName, portName);
        }

        return String.format("设备 %s 端口列表:\n\n" +
                "【上行端口】(4个)\n" +
                "  GE0/0/1  UP   10Gbps   连接: SW-SH-01\n" +
                "  GE0/0/2  UP   10Gbps   连接: SW-SH-02\n" +
                "  GE0/0/3  UP   10Gbps   连接: SW-SH-03\n" +
                "  GE0/0/4  DOWN 10Gbps   未连接\n\n" +
                "【PON口】(16个)\n" +
                "  PON1/1/1  UP   128ONU   在线: 120\n" +
                "  PON1/1/2  UP   128ONU   在线: 115\n" +
                "  ... (共16个PON口)\n\n" +
                "端口统计: 总计 20 个端口，UP 18 个，DOWN 2 个",
                deviceName);
    }

    @Tool(name = "allocate_resource", description = "分配网络资源，如端口、带宽")
    public String allocateResource(
            @ToolParam(name = "resource_type", description = "资源类型: port(端口), bandwidth(带宽), vlan(VLAN)")
            String resourceType,
            @ToolParam(name = "device_name", description = "目标设备名称")
            String deviceName,
            @ToolParam(name = "quantity", description = "申请数量", required = false)
            int quantity) {

        if (quantity <= 0) {
            quantity = 1;
        }

        if (resourceType.equals("port")) {
            return String.format("端口资源分配结果:\n\n" +
                    "设备: %s\n" +
                    "申请数量: %d 个端口\n\n" +
                    "分配结果:\n" +
                    "  已分配端口: GE0/0/5\n" +
                    "  端口类型: GE (千兆以太网)\n" +
                    "  端口状态: 已预留，待配置\n\n" +
                    "操作建议:\n" +
                    "1. 执行端口配置命令\n" +
                    "2. 配置VLAN和IP\n" +
                    "3. 验证连通性",
                    deviceName, quantity);
        } else if (resourceType.equals("bandwidth")) {
            return String.format("带宽资源分配结果:\n\n" +
                    "设备: %s\n" +
                    "申请带宽: %d Mbps\n\n" +
                    "分配结果:\n" +
                    "  可用带宽: 1000 Mbps\n" +
                    "  已分配: %d Mbps\n" +
                    "  剩余: %d Mbps\n\n" +
                    "状态: 分配成功",
                    deviceName, quantity, quantity, 1000 - quantity);
        } else {
            return String.format("VLAN资源分配结果:\n\n" +
                    "设备: %s\n" +
                    "分配VLAN ID: 100\n" +
                    "VLAN名称: VLAN_BUSINESS\n" +
                    "状态: 创建成功",
                    deviceName);
        }
    }

    @Tool(name = "query_resource_usage", description = "查询资源使用情况")
    public String queryResourceUsage(
            @ToolParam(name = "region", description = "区域名称，如 '上海', '北京'")
            String region) {

        return String.format("%s 区域资源使用统计:\n\n" +
                "【设备统计】\n" +
                "  总设备数: 156 台\n" +
                "  在线设备: 152 台\n" +
                "  离线设备: 4 台\n" +
                "  设备可用率: 97.4%%\n\n" +
                "【端口统计】\n" +
                "  总端口数: 12,800 个\n" +
                "  已用端口: 10,240 个\n" +
                "  可用端口: 2,560 个\n" +
                "  端口利用率: 80%%\n\n" +
                "【带宽统计】\n" +
                "  总带宽: 100 Gbps\n" +
                "  已用带宽: 65 Gbps\n" +
                "  可用带宽: 35 Gbps\n" +
                "  带宽利用率: 65%%",
                region);
    }
}
