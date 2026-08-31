<template>
  <div class="space-y-6">
    <div class="bg-white shadow rounded-lg p-6">
      <h2 class="text-2xl font-bold text-gray-800 mb-4">协同处理</h2>
      <p class="text-gray-600 mb-6">多 Agent 协同故障处理：监控发现问题 → 诊断定位根因 → 创建故障工单</p>
      
      <div class="space-y-4">
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-2">问题描述</label>
          <textarea
            v-model="issue"
            rows="4"
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-red-500"
            placeholder="例如：OLT-SH-01 上行链路中断，影响1200户宽带用户"
          ></textarea>
        </div>
        
        <button
          @click="handleCollaborate"
          :disabled="ossStore.loading || !issue.trim()"
          class="px-6 py-2 bg-red-600 text-white rounded-md hover:bg-red-700 disabled:bg-gray-400 disabled:cursor-not-allowed"
        >
          {{ ossStore.loading ? '处理中...' : '开始协同处理' }}
        </button>
        
        <div v-if="ossStore.error" class="p-4 bg-red-50 border border-red-200 rounded-md">
          <p class="text-red-800">{{ ossStore.error }}</p>
        </div>
        
        <div v-if="ossStore.result" class="p-4 bg-gray-50 border border-gray-200 rounded-md">
          <h3 class="font-semibold text-gray-800 mb-2">处理结果</h3>
          <pre class="whitespace-pre-wrap text-sm text-gray-700">{{ ossStore.result }}</pre>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useOssStore } from '../stores/ossStore'

const ossStore = useOssStore()
const issue = ref('')

function handleCollaborate() {
  if (issue.value.trim()) {
    ossStore.collaborativeFaultHandling(issue.value)
  }
}
</script>
