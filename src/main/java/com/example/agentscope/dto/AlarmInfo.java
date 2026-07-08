package com.example.agentscope.dto;

/**
 * 告警信息 - 运营商OSS故障管理核心对象
 */
public class AlarmInfo {

    /** 告警ID */
    private String alarmId;

    /** 告警级别: CRITICAL(严重), MAJOR(主要), MINOR(次要), WARNING(警告) */
    private String severity;

    /** 告警来源设备名称 */
    private String deviceName;

    /** 设备类型: SWITCH(交换机), ROUTER(路由器), OLT(光线路终端), BTS(基站) */
    private String deviceType;

    /** 告警内容描述 */
    private String alarmContent;

    /** 告警时间 */
    private String alarmTime;

    /** 告警状态: ACTIVE(活跃), CLEARED(已清除), ACKNOWLEDGED(已确认) */
    private String status;

    public AlarmInfo() {
    }

    public AlarmInfo(String alarmId, String severity, String deviceName, String deviceType,
                     String alarmContent, String alarmTime, String status) {
        this.alarmId = alarmId;
        this.severity = severity;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.alarmContent = alarmContent;
        this.alarmTime = alarmTime;
        this.status = status;
    }

    public String getAlarmId() { return alarmId; }
    public void setAlarmId(String alarmId) { this.alarmId = alarmId; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public String getDeviceName() { return deviceName; }
    public void setDeviceName(String deviceName) { this.deviceName = deviceName; }

    public String getDeviceType() { return deviceType; }
    public void setDeviceType(String deviceType) { this.deviceType = deviceType; }

    public String getAlarmContent() { return alarmContent; }
    public void setAlarmContent(String alarmContent) { this.alarmContent = alarmContent; }

    public String getAlarmTime() { return alarmTime; }
    public void setAlarmTime(String alarmTime) { this.alarmTime = alarmTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
