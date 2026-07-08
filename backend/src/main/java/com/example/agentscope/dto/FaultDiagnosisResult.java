package com.example.agentscope.dto;

/**
 * 故障诊断结果 - OSS故障管理模块输出
 */
public class FaultDiagnosisResult {

    /** 故障ID */
    private String faultId;

    /** 故障类型: LINK_DOWN(链路中断), HIGH_CPU(CPU过高), PORT_ERROR(端口异常), SERVICE_DOWN(业务中断) */
    private String faultType;

    /** 故障设备 */
    private String faultDevice;

    /** 根因分析 */
    private String rootCause;

    /** 建议处理方案 */
    private String solution;

    /** 影响范围 */
    private String impactScope;

    public FaultDiagnosisResult() {
    }

    public String getFaultId() { return faultId; }
    public void setFaultId(String faultId) { this.faultId = faultId; }

    public String getFaultType() { return faultType; }
    public void setFaultType(String faultType) { this.faultType = faultType; }

    public String getFaultDevice() { return faultDevice; }
    public void setFaultDevice(String faultDevice) { this.faultDevice = faultDevice; }

    public String getRootCause() { return rootCause; }
    public void setRootCause(String rootCause) { this.rootCause = rootCause; }

    public String getSolution() { return solution; }
    public void setSolution(String solution) { this.solution = solution; }

    public String getImpactScope() { return impactScope; }
    public void setImpactScope(String impactScope) { this.impactScope = impactScope; }
}
