# OSS AgentScope - 运营商智能运维系统

基于 **Spring Boot + AgentScope Java** 框架构建的运营商 OSS 智能运维系统，将运营商网络运维业务场景与 AI 多智能体技术结合。

## 项目概述

本项目面向通信运营商的 OSS（Operations Support System，运营支撑系统）场景，使用 AgentScope 的 ReAct 智能体和多智能体协同能力，实现：

- **故障智能诊断** - 自动分析告警、查询日志、定位根因
- **网络质量监控** - 性能指标查询、网络质量分析
- **资源智能管理** - 设备查询、端口管理、资源分配
- **工单自动流转** - 故障工单创建、派发、状态跟踪
- **多 Agent 协同** - 监控 → 诊断 → 工单，全流程自动化

## 项目结构

```
study_agentscope/
├── pom.xml                                         # Maven 配置
├── src/main/java/com/example/agentscope/
│   ├── AgentScopeApplication.java                  # Spring Boot 启动类
│   ├── controller/
│   │   └── OssAgentController.java                 # OSS 业务接口
│   ├── service/
│   │   ├── OssAgentService.java                    # OSS 服务接口
│   │   └── impl/
│   │       └── OssAgentServiceImpl.java            # OSS 服务实现
│   ├── dto/
│   │   ├── AgentRequest.java / AgentResponse.java  # 通用 DTO
│   │   ├── AlarmInfo.java                          # 告警信息
│   │   ├── FaultDiagnosisResult.java               # 故障诊断结果
│   │   ├── PerformanceMetric.java                  # 性能指标
│   │   ├── DeviceInfo.java                         # 设备信息
│   │   └── WorkOrder.java                          # 工单信息
│   ├── tool/
│   │   ├── FaultDiagnosisTools.java                # 故障诊断工具
│   │   ├── NetworkMonitorTools.java                # 网络监控工具
│   │   ├── ResourceManageTools.java                # 资源管理工具
│   │   └── WorkOrderTools.java                     # 工单管理工具
│   └── config/
│       ├── OllamaProperties.java                   # Ollama 配置
│       ├── OllamaConfig.java                       # 模型配置
│       └── AgentProperties.java                    # Agent 配置
└── src/main/resources/
    └── application.yml                             # 应用配置
```

## Spring 分层架构

```
┌─────────────────────────────────────────────┐
│  Controller 层（接口层）                      │
│  接收 HTTP 请求，调用 Service                  │
├─────────────────────────────────────────────┤
│  Service 层（业务逻辑层）                     │
│  接口 + 实现，编排 Agent 调用                  │
├─────────────────────────────────────────────┤
│  Tool 层（Agent 工具层）                      │
│  @Tool 注解定义，Agent 自动调用                │
├─────────────────────────────────────────────┤
│  DTO 层（数据传输对象）                       │
│  告警、性能、设备、工单等业务对象               │
└─────────────────────────────────────────────┘
```

## OSS 业务模块

| 模块 | 功能 | Agent | 工具类 |
|------|------|-------|--------|
| 故障管理 | 告警分析、故障定位、工单流转 | 故障诊断 Agent | `FaultDiagnosisTools` |
| 性能管理 | 指标查询、质量分析 | 网络监控 Agent | `NetworkMonitorTools` |
| 资源管理 | 设备查询、端口分配 | 资源管理 Agent | `ResourceManageTools` |
| 工单管理 | 工单创建、派发、跟踪 | 工单管理 Agent | `WorkOrderTools` |

## API 接口

### 1. 故障诊断
```bash
POST /api/oss/fault/diagnose
{"alarmContent": "OLT-SH-01 上行链路中断"}
```

### 2. 网络监控
```bash
POST /api/oss/monitor/query
{"query": "上海区域网络质量如何？"}
```

### 3. 资源管理
```bash
POST /api/oss/resource/query
{"query": "OLT-SH-01有哪些可用端口？"}
```

### 4. 工单管理
```bash
POST /api/oss/workorder/create
{"orderRequest": "创建故障工单，OLT上行链路中断，优先级P1"}
```

### 5. 故障协同处理（多 Agent 协同）
```bash
POST /api/oss/collaborate
{"issue": "OLT-SH-01 上行链路中断，影响1200户宽带用户"}
```

## 多 Agent 协同流程

```
用户输入故障描述
       ↓
┌──────────────────────────┐
│ 网络监控 Agent             │
│ 查询性能 → 分析质量        │
└──────────────────────────┘
       ↓
┌──────────────────────────┐
│ 故障诊断 Agent             │
│ 分析告警 → 定位根因        │
└──────────────────────────┘
       ↓
┌──────────────────────────┐
│ 工单管理 Agent             │
│ 创建工单 → 派发处理        │
└──────────────────────────┘
       ↓
输出故障协同处理报告
```

## 前置要求

- JDK 17+
- Maven 3.8+
- Ollama 本地模型服务

## 快速开始

```bash
# 编译
mvn clean compile

# 启动（确保 Ollama 已运行）
mvn spring-boot:run

# 或在 IDEA 中运行 AgentScopeApplication.java
```

## 测试

```bash
# 故障诊断
curl -X POST http://localhost:8080/api/oss/fault/diagnose \
  -H "Content-Type: application/json" \
  -d '{"alarmContent": "OLT-SH-01 上行链路中断"}'

# 故障协同处理
curl -X POST http://localhost:8080/api/oss/collaborate \
  -H "Content-Type: application/json" \
  -d '{"issue": "OLT-SH-01 上行链路中断，影响1200户宽带用户"}'
```

## 学习要点

1. **Spring 分层架构** - Controller → Service → Tool，每层职责清晰
2. **面向接口编程** - Service 接口 + Impl 实现，方便替换和测试
3. **构造方法注入** - Spring 推荐的依赖注入方式
4. **AgentScope 工具系统** - `@Tool` 定义工具，Agent 自动推理调用
5. **多 Agent 协同** - 多个专业 Agent 协作完成复杂运维任务

## 参考资源

- [AgentScope Java 官方文档](https://java.agentscope.io)
- [AgentScope Java GitHub](https://github.com/agentscope-ai/agentscope-java)
- [Spring Boot 官方文档](https://spring.io/projects/spring-boot)
- [ReAct 论文](https://react-lm.github.io/)

## 许可证

Apache 2.0
