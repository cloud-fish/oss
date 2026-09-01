<template>
  <Homepage v-if="isLogin" @logout="handleLogout"/>
  <Logpage v-else @login="handleLogin" />
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'

import Homepage from './components/Homepage.vue'
import Logpage from './components/Logpage.vue'

const isLogin = ref(false)

onMounted(() => {
  isLogin.value = localStorage.getItem('isLogin') === 'true'
})

function handleLogin() {
  isLogin.value = true
  localStorage.setItem('isLogin', 'true')
}

function handleLogout() {
  isLogin.value = false
  localStorage.setItem('isLogin', 'false')
}

</script>