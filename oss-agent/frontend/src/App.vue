<template>
  <div class="min-h-screen bg-gray-50">
    <nav class="bg-blue-600 text-white shadow-lg">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="flex items-center justify-between h-16">
          <div class="flex items-center">
            <h1 class="text-xl font-bold">OSS 智能运维系统</h1>
          </div>
          <div class="flex space-x-4">
            <router-link to="/" class="px-3 py-2 rounded-md text-sm font-medium hover:bg-blue-700">首页</router-link>
            <router-link to="/fault" class="px-3 py-2 rounded-md text-sm font-medium hover:bg-blue-700">故障诊断</router-link>
            <router-link to="/monitor" class="px-3 py-2 rounded-md text-sm font-medium hover:bg-blue-700">网络监控</router-link>
            <router-link to="/resource" class="px-3 py-2 rounded-md text-sm font-medium hover:bg-blue-700">资源管理</router-link>
            <router-link to="/workorder" class="px-3 py-2 rounded-md text-sm font-medium hover:bg-blue-700">工单管理</router-link>
            <router-link to="/collaborate" class="px-3 py-2 rounded-md text-sm font-medium hover:bg-blue-700">协同处理</router-link>
          </div>
        </div>
      </div>
    </nav>
    <main class="max-w-7xl mx-auto py-6 sm:px-6 lg:px-8">
      <router-view />
    </main>
  </div>
</template>

<script setup>
</script>
