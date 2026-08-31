<template>
  <div class="space-y-6">
    <div class="bg-white shadow rounded-lg p-6">
      <h2 class="text-2xl font-bold text-gray-800 mb-4">欢迎使用 OSS 智能运维系统</h2>
      <p class="text-gray-600 mb-6">
        本系统基于 AgentScope 和 Spring Boot 构建，提供运营商 OSS 智能运维能力。
      </p>
      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        <router-link to="/fault" class="block p-4 bg-blue-50 rounded-lg hover:bg-blue-100 transition">
          <h3 class="font-semibold text-blue-900">故障诊断</h3>
          <p class="text-sm text-blue-700 mt-1">分析告警信息，定位故障根因</p>
        </router-link>
        <router-link to="/monitor" class="block p-4 bg-green-50 rounded-lg hover:bg-green-100 transition">
          <h3 class="font-semibold text-green-900">网络监控</h3>
          <p class="text-sm text-green-700 mt-1">查询性能指标，分析网络质量</p>
        </router-link>
        <router-link to="/resource" class="block p-4 bg-purple-50 rounded-lg hover:bg-purple-100 transition">
          <h3 class="font-semibold text-purple-900">资源管理</h3>
          <p class="text-sm text-purple-700 mt-1">查询设备信息，分配网络资源</p>
        </router-link>
        <router-link to="/workorder" class="block p-4 bg-orange-50 rounded-lg hover:bg-orange-100 transition">
          <h3 class="font-semibold text-orange-900">工单管理</h3>
          <p class="text-sm text-orange-700 mt-1">创建和派发故障工单</p>
        </router-link>
        <router-link to="/collaborate" class="block p-4 bg-red-50 rounded-lg hover:bg-red-100 transition">
          <h3 class="font-semibold text-red-900">协同处理</h3>
          <p class="text-sm text-red-700 mt-1">多 Agent 协同故障处理</p>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script setup>
</script>
