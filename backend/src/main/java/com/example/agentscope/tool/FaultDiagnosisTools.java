package com.example.agentscope.tool;

import io.agentscope.core.tool.Tool;
import io.agentscope.core.tool.ToolParam;

/**
 * 故障诊断工具集 - OSS故障管理模块
 */
public class FaultDiagnosisTools {

    @Tool(name = "query_active_alarms", description = "查询当前活跃的告警信息，返回告警列表")
    public String queryActiveAlarms(
            @ToolParam(name = "severity", description = "告警级别过滤: CRITICAL, MAJOR, MINOR, WARNING, 不传则查所有", required = false)
            String severity,
            @ToolParam(name = "device_type", description = "设备类型过滤: SWITCH, ROUTER, OLT, BTS, 不传则查所有", required = false)
            String deviceType) {

        StringBuilder result = new StringBuilder();
        result.append("当前活跃告警列表:\n\n");

        // 模拟告警数据
        result.append("1. [CRITICAL] OLT-SH-01 | 上行链路中断 | 2024-01-15 14:30:00\n");
        result.append("   影响范围: 下挂 1200 户宽带用户\n\n");

        result.append("2. [MAJOR] BTS-BJ-05 | 基站退服 | 2024-01-15 14:25:00\n");
        result.append("   影响范围: 周边 500 米移动信号覆盖\n\n");

        result.append("3. [MINOR] SW-GZ-03 | 端口流量超限 | 2024-01-15 14:20:00\n");
        result.append("   影响范围: 端口利用率 95%\n\n");

        if (severity != null) {
            result.append(String.format("[已过滤告警级别: %s]\n", severity));
        }
        if (deviceType != null) {
            result.append(String.format("[已过滤设备类型: %s]\n", deviceType));
        }

        return result.toString();
    }

    @Tool(name = "analyze_device_log", description = "分析设备日志，提取关键错误信息")
    public String analyzeDeviceLog(
            @ToolParam(name = "device_name", description = "设备名称，如 OLT-SH-01")
            String deviceName,
            @ToolParam(name = "time_range", description = "时间范围，如 '最近1小时', '今天'")
            String timeRange) {

        return String.format("设备 %s 日志分析 (%s):\n\n" +
                "发现以下关键事件:\n" +
                "1. 14:30:00 [ERROR] 上行端口 GE0/0/1 链路状态变为 DOWN\n" +
                "2. 14:29:58 [WARNING] 光模块接收功率低于阈值 (-25dBm)\n" +
                "3. 14:29:55 [INFO] 检测到光信号衰减\n\n" +
                "初步判断: 上行光路故障，可能是光纤断裂或光模块故障\n" +
                "建议操作: 检查上行光纤连接，测试光模块",
                deviceName, timeRange);
    }

    @Tool(name = "diagnose_fault", description = "根据告警信息诊断故障根因")
    public String diagnoseFault(
            @ToolParam(name = "alarm_content", description = "告警内容描述")
            String alarmContent,
            @ToolParam(name = "device_type", description = "设备类型: SWITCH, ROUTER, OLT, BTS")
            String deviceType) {

        String faultType;
        String rootCause;
        String solution;

        if (alarmContent.contains("链路") || alarmContent.contains("中断")) {
            faultType = "链路故障";
            rootCause = "物理链路中断，可能原因：光纤断裂、光模块故障、对端设备掉电";
            solution = "1. 检查光纤连接是否正常\n2. 测试光模块收发功率\n3. 检查对端设备状态\n4. 必要时更换光模块或光纤";
        } else if (alarmContent.contains("CPU") || alarmContent.contains("cpu")) {
            faultType = "CPU过载";
            rootCause = "设备CPU利用率过高，可能原因：广播风暴、攻击流量、配置错误";
            solution = "1. 检查流量是否存在异常\n2. 排查是否存在环路\n3. 检查是否有攻击流量\n4. 优化设备配置";
        } else if (alarmContent.contains("端口")) {
            faultType = "端口异常";
            rootCause = "端口流量超限或错误包过多，可能原因：流量突增、端口故障";
            solution = "1. 检查端口流量构成\n2. 检查是否存在异常流量\n3. 考虑扩容或负载均衡";
        } else {
            faultType = "未知故障";
            rootCause = "需要进一步收集信息进行分析";
            solution = "1. 收集设备日志\n2. 检查设备状态\n3. 联系技术支持";
        }

        return String.format("故障诊断结果:\n\n" +
                "故障类型: %s\n" +
                "设备类型: %s\n" +
                "告警内容: %s\n\n" +
                "根因分析: %s\n\n" +
                "处理建议:\n%s",
                faultType, deviceType, alarmContent, rootCause, solution);
    }

    @Tool(name = "get_fault_history", description = "查询设备历史故障记录")
    public String getFaultHistory(
            @ToolParam(name = "device_name", description = "设备名称")
            String deviceName,
            @ToolParam(name = "days", description = "查询最近多少天的记录", required = false)
            int days) {

        if (days <= 0) {
            days = 7;
        }

        return String.format("设备 %s 最近 %d 天故障记录:\n\n" +
                "1. 2024-01-15 | 上行链路中断 | 已恢复 | 处理时长: 45分钟\n" +
                "2. 2024-01-10 | 光模块告警 | 已恢复 | 处理时长: 2小时\n" +
                "3. 2024-01-08 | 端口流量超限 | 已恢复 | 处理时长: 30分钟\n\n" +
                "故障统计: 共 %d 次故障，平均处理时长: 1小时",
                deviceName, days, 3);
    }
}
