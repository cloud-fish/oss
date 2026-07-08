import { defineStore } from 'pinia'
import { ref } from 'vue'
import * as api from '../api/oss'

export const useOssStore = defineStore('oss', () => {
  const loading = ref(false)
  const result = ref('')
  const error = ref('')

  async function diagnoseFault(alarmContent) {
    loading.value = true
    error.value = ''
    result.value = ''
    try {
      const res = await api.diagnoseFault(alarmContent)
      if (res.data.success) {
        result.value = res.data.reply
      } else {
        error.value = res.data.error || '诊断失败'
      }
    } catch (err) {
      error.value = err.message || '请求失败'
    } finally {
      loading.value = false
    }
  }

  async function monitorNetwork(query) {
    loading.value = true
    error.value = ''
    result.value = ''
    try {
      const res = await api.monitorNetwork(query)
      if (res.data.success) {
        result.value = res.data.reply
      } else {
        error.value = res.data.error || '查询失败'
      }
    } catch (err) {
      error.value = err.message || '请求失败'
    } finally {
      loading.value = false
    }
  }

  async function manageResource(query) {
    loading.value = true
    error.value = ''
    result.value = ''
    try {
      const res = await api.manageResource(query)
      if (res.data.success) {
        result.value = res.data.reply
      } else {
        error.value = res.data.error || '查询失败'
      }
    } catch (err) {
      error.value = err.message || '请求失败'
    } finally {
      loading.value = false
    }
  }

  async function createWorkOrder(orderRequest) {
    loading.value = true
    error.value = ''
    result.value = ''
    try {
      const res = await api.createWorkOrder(orderRequest)
      if (res.data.success) {
        result.value = res.data.reply
      } else {
        error.value = res.data.error || '创建失败'
      }
    } catch (err) {
      error.value = err.message || '请求失败'
    } finally {
      loading.value = false
    }
  }

  async function collaborativeFaultHandling(issue) {
    loading.value = true
    error.value = ''
    result.value = ''
    try {
      const res = await api.collaborativeFaultHandling(issue)
      if (res.data.success) {
        result.value = res.data.reply
      } else {
        error.value = res.data.error || '处理失败'
      }
    } catch (err) {
      error.value = err.message || '请求失败'
    } finally {
      loading.value = false
    }
  }

  return {
    loading,
    result,
    error,
    diagnoseFault,
    monitorNetwork,
    manageResource,
    createWorkOrder,
    collaborativeFaultHandling,
  }
})
