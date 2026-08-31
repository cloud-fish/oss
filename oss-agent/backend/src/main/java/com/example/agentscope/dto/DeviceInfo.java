package com.example.agentscope.dto;

/**
 * 网络设备信息 - OSS资源管理模块
 */
public class DeviceInfo {

    /** 设备ID */
    private String deviceId;

    /** 设备名称 */
    private String deviceName;

    /** 设备类型: SWITCH(交换机), ROUTER(路由器), OLT(光线路终端), BTS(基站), SERVER(服务器) */
    private String deviceType;

    /** 设备IP地址 */
    private String ipAddress;

    /** 设备位置(机房) */
    private String location;

    /** 设备状态: ONLINE(在线), OFFLINE(离线), MAINTENANCE(维护中) */
    private String status;

    /** 端口总数 */
    private int totalPorts;

    /** 已用端口数 */
    private int usedPorts;

    public DeviceInfo() {
    }

    public String getDeviceId() { return deviceId; }
    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }

    public String getDeviceName() { return deviceName; }
    public void setDeviceName(String deviceName) { this.deviceName = deviceName; }

    public String getDeviceType() { return deviceType; }
    public void setDeviceType(String deviceType) { this.deviceType = deviceType; }

    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getTotalPorts() { return totalPorts; }
    public void setTotalPorts(int totalPorts) { this.totalPorts = totalPorts; }

    public int getUsedPorts() { return usedPorts; }
    public void setUsedPorts(int usedPorts) { this.usedPorts = usedPorts; }
}
