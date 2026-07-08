<template>
  <div class="space-y-6">
    <div class="bg-white shadow rounded-lg p-6">
      <h2 class="text-2xl font-bold text-gray-800 mb-4">工单管理</h2>
      <p class="text-gray-600 mb-6">创建故障工单，派发处理任务</p>
      
      <div class="space-y-4">
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-2">工单描述</label>
          <textarea
            v-model="orderRequest"
            rows="4"
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-orange-500"
            placeholder="例如：创建故障工单，OLT上行链路中断，优先级P1"
          ></textarea>
        </div>
        
        <button
          @click="handleCreate"
          :disabled="ossStore.loading || !orderRequest.trim()"
          class="px-6 py-2 bg-orange-600 text-white rounded-md hover:bg-orange-700 disabled:bg-gray-400 disabled:cursor-not-allowed"
        >
          {{ ossStore.loading ? '创建中...' : '创建工单' }}
        </button>
        
        <div v-if="ossStore.error" class="p-4 bg-red-50 border border-red-200 rounded-md">
          <p class="text-red-800">{{ ossStore.error }}</p>
        </div>
        
        <div v-if="ossStore.result" class="p-4 bg-gray-50 border border-gray-200 rounded-md">
          <h3 class="font-semibold text-gray-800 mb-2">创建结果</h3>
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
const orderRequest = ref('')

function handleCreate() {
  if (orderRequest.value.trim()) {
    ossStore.createWorkOrder(orderRequest.value)
  }
}
</script>
