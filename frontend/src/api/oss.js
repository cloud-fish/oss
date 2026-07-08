import axios from 'axios'

const api = axios.create({
  baseURL: '/api/oss',
  timeout: 30000,
})

export function diagnoseFault(alarmContent) {
  return api.post('/fault/diagnose', { alarmContent })
}

export function monitorNetwork(query) {
  return api.post('/monitor/query', { query })
}

export function manageResource(query) {
  return api.post('/resource/query', { query })
}

export function createWorkOrder(orderRequest) {
  return api.post('/workorder/create', { orderRequest })
}

export function collaborativeFaultHandling(issue) {
  return api.post('/collaborate', { issue })
}
