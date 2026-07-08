package com.example.agentscope.tool;

import io.agentscope.core.tool.Tool;
import io.agentscope.core.tool.ToolParam;

/**
 * 网络监控工具集 - OSS性能管理模块
 */
public class NetworkMonitorTools {

    @Tool(name = "query_performance_metrics", description = "查询设备性能指标，如带宽利用率、时延、丢包率")
    public String queryPerformanceMetrics(
            @ToolParam(name = "device_name", description = "设备名称，如 SW-SH-01")
            String deviceName,
            @ToolParam(name = "metric_type", description = "指标类型: bandwidth(带宽), latency(时延), packet_loss(丢包), cpu(利用率), memory(内存)", required = false)
            String metricType) {

        StringBuilder result = new StringBuilder();
        result.append(String.format("设备 %s 性能指标:\n\n", deviceName));

        if (metricType == null || metricType.equals("bandwidth")) {
            result.append("【带宽利用率】\n");
            result.append("  上行: 65.2% (阈值 80%)\n");
            result.append("  下行: 42.8% (阈值 80%)\n\n");
        }

        if (metricType == null || metricType.equals("latency")) {
            result.append("【时延】\n");
            result.append("  平均: 12.5ms (阈值 50ms)\n");
            result.append("  最大: 45ms\n\n");
        }

        if (metricType == null || metricType.equals("packet_loss")) {
            result.append("【丢包率】\n");
            result.append("  当前: 0.02% (阈值 0.1%)\n\n");
        }

        if (metricType == null || metricType.equals("cpu")) {
            result.append("【CPU利用率】\n");
            result.append("  当前: 35% (阈值 80%)\n\n");
        }

        if (metricType == null || metricType.equals("memory")) {
            result.append("【内存利用率】\n");
            result.append("  当前: 58% (阈值 85%)\n");
        }

        return result.toString();
    }

    @Tool(name = "analyze_network_quality", description = "分析网络质量，生成质量报告")
    public String analyzeNetworkQuality(
            @ToolParam(name = "region", description = "区域名称，如 '上海', '北京', '广州'")
            String region,
            @ToolParam(name = "time_range", description = "时间范围，如 '最近1小时', '今天', '最近7天'")
            String timeRange) {

        return String.format("%s 区域网络质量报告 (%s):\n\n" +
                "【整体评分】: 92/100 (优秀)\n\n" +
                "【关键指标】:\n" +
                "  - 网络可用率: 99.98%\n" +
                "  - 平均时延: 15ms\n" +
                "  - 平均丢包率: 0.01%\n" +
                "  - 带宽利用率: 58%\n\n" +
                "【告警统计】:\n" +
                "  - 严重告警: 0 个\n" +
                "  - 主要告警: 2 个\n" +
                "  - 次要告警: 5 个\n\n" +
                "【结论】: 网络运行稳定，各项指标正常",
                region, timeRange);
    }

    @Tool(name = "get_device_status", description = "获取设备运行状态")
    public String getDeviceStatus(
            @ToolParam(name = "device_name", description = "设备名称")
            String deviceName) {

        return String.format("设备 %s 运行状态:\n\n" +
                "【基本信息】\n" +
                "  设备类型: OLT\n" +
                "  设备型号: MA5800-X17\n" +
                "  软件版本: V800R020\n" +
                "  运行时长: 125天\n\n" +
                "【运行状态】\n" +
                "  设备状态: 正常\n" +
                "  CPU利用率: 35%\n" +
                "  内存利用率: 58%\n" +
                "  温度: 42°C\n\n" +
                "【端口状态】\n" +
                "  上行端口: 4/4 正常\n" +
                "  PON口: 16/16 正常\n" +
                "  在线ONU: 1200/1280",
                deviceName);
    }

    @Tool(name = "query_link_status", description = "查询链路状态和流量信息")
    public String queryLinkStatus(
            @ToolParam(name = "link_name", description = "链路名称，如 'OLT-SH-01-UP-GE0/0/1'")
            String linkName) {

        return String.format("链路 %s 状态:\n\n" +
                "【链路信息】\n" +
                "  链路类型: 上行链路\n" +
                "  带宽: 10Gbps\n" +
                "  对端设备: SW-SH-01\n\n" +
                "【流量信息】\n" +
                "  入向流量: 2.5Gbps (25%%)\n" +
                "  出向流量: 1.8Gbps (18%%)\n\n" +
                "【链路质量】\n" +
                "  光功率: -12dBm (正常范围: -8~-25dBm)\n" +
                "  误码率: 0 (正常)\n" +
                "  链路状态: UP",
                linkName);
    }
}
