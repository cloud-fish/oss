import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import FaultDiagnosis from '../views/FaultDiagnosis.vue'
import NetworkMonitor from '../views/NetworkMonitor.vue'
import ResourceManage from '../views/ResourceManage.vue'
import WorkOrder from '../views/WorkOrder.vue'
import Collaborate from '../views/Collaborate.vue'

const routes = [
  { path: '/', name: 'Home', component: Home },
  { path: '/fault', name: 'FaultDiagnosis', component: FaultDiagnosis },
  { path: '/monitor', name: 'NetworkMonitor', component: NetworkMonitor },
  { path: '/resource', name: 'ResourceManage', component: ResourceManage },
  { path: '/workorder', name: 'WorkOrder', component: WorkOrder },
  { path: '/collaborate', name: 'Collaborate', component: Collaborate },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router
