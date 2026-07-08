<template>
  <div class="space-y-6">
    <div class="bg-white shadow rounded-lg p-6">
      <h2 class="text-2xl font-bold text-gray-800 mb-4">网络监控</h2>
      <p class="text-gray-600 mb-6">查询网络性能指标和质量报告</p>
      
      <div class="space-y-4">
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-2">查询内容</label>
          <textarea
            v-model="query"
            rows="4"
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-green-500"
            placeholder="例如：上海区域网络质量如何？"
          ></textarea>
        </div>
        
        <button
          @click="handleQuery"
          :disabled="ossStore.loading || !query.trim()"
          class="px-6 py-2 bg-green-600 text-white rounded-md hover:bg-green-700 disabled:bg-gray-400 disabled:cursor-not-allowed"
        >
          {{ ossStore.loading ? '查询中...' : '开始查询' }}
        </button>
        
        <div v-if="ossStore.error" class="p-4 bg-red-50 border border-red-200 rounded-md">
          <p class="text-red-800">{{ ossStore.error }}</p>
        </div>
        
        <div v-if="ossStore.result" class="p-4 bg-gray-50 border border-gray-200 rounded-md">
          <h3 class="font-semibold text-gray-800 mb-2">查询结果</h3>
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
const query = ref('')

function handleQuery() {
  if (query.value.trim()) {
    ossStore.monitorNetwork(query.value)
  }
}
</script>
