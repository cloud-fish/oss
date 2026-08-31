package com.example.agentscope.dto;

/**
 * 网络性能指标 - OSS性能管理模块
 */
public class PerformanceMetric {

    /** 指标名称: bandwidth(带宽利用率), latency(时延), packet_loss(丢包率), availability(可用率) */
    private String metricName;

    /** 设备名称 */
    private String deviceName;

    /** 指标当前值 */
    private double currentValue;

    /** 指标阈值 */
    private double threshold;

    /** 单位: %, ms, Mbps, Gbps */
    private String unit;

    /** 采集时间 */
    private String collectTime;

    /** 是否超阈值 */
    private boolean overThreshold;

    public PerformanceMetric() {
    }

    public String getMetricName() { return metricName; }
    public void setMetricName(String metricName) { this.metricName = metricName; }

    public String getDeviceName() { return deviceName; }
    public void setDeviceName(String deviceName) { this.deviceName = deviceName; }

    public double getCurrentValue() { return currentValue; }
    public void setCurrentValue(double currentValue) { this.currentValue = currentValue; }

    public double getThreshold() { return threshold; }
    public void setThreshold(double threshold) { this.threshold = threshold; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public String getCollectTime() { return collectTime; }
    public void setCollectTime(String collectTime) { this.collectTime = collectTime; }

    public boolean isOverThreshold() { return overThreshold; }
    public void setOverThreshold(boolean overThreshold) { this.overThreshold = overThreshold; }
}
