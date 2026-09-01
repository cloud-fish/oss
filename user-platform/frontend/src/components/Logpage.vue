<script setup lang="ts">
import { ref } from 'vue'
import { login,register } from '../api/user'

const emit = defineEmits<{
  (e: 'login'): void
}>()

const username = ref('')
const password = ref('')
const errorMsg = ref('')
const rememberMe = ref(false)

async function handleLogin() {
  errorMsg.value = ''
  if (!username.value || !password.value) {
    errorMsg.value = 'Username and password are required'

    return
  }
  try {
    const res = await login(username.value, password.value)
    console.log(res)
    emit('login')
  } catch (error) {
    errorMsg.value = 'Login failed'
    console.error(error)
  }
}

async function handleRegister() {
  errorMsg.value = ''
  if (!username.value || !password.value) {
    errorMsg.value = 'Username and password are required'
    return
  }
  try {
    const res = await register(username.value, password.value)
    console.log(res)
  } catch (error) {
    errorMsg.value = 'Registration failed'
    console.error(error)
  }
}

async function handleForgotPassword() {
  errorMsg.value = ''
  if (!username.value) {
    errorMsg.value = 'Username is required'
    return
  }
  try {
    const res = await forgotPassword(username.value)
    console.log(res)
  } catch (error) {
    errorMsg.value = 'Forgot Password failed'
    console.error(error)
  }
}
</script>

<template>
  <div class="flex h-screen" style="background-image: url('/login.png'); background-size: cover; background-position: center;">
    

    <div class="flex px-6 md:ml-auto md:w-2/5 bg-gray-100 items-center">
      <div class="flex flex-col w-full m-4 bg-gray-200">

        <div class="mb-8 text-center">
          <h2 class="text-2xl font-bold text-center">欢迎回来</h2>
          <p class="text-center">登陆后继续你的学习</p>
        </div>

        <div class="mb-4">
          <label class="block text-sm font-bold mb-2">账号</label>
          <input type="text" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" v-model="username" />
        </div>

        <div class="mb-4">
          <label class="block text-sm font-bold mb-2">密码</label>
          <input type="password" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" v-model="password" />
        </div>

        <div class="flex flex-row items-center justify-between w-full">
          <label class="flex ">
            <input v-model="rememberMe" type="checkbox" class="mr-2 leading-tight" />记住我
          </label>

          <button type="button" class="font-medium text-indigo-600 hover:text-indigo-7 focus:outline-none focus:shadow-outline cursor-pointer" @click="handleForgotPassword">
            忘记密码
          </button>
        </div>

        <div class="flex flex-col w-full">
          <button type="button" class="w-full bg-indigo-500 text-white font-bold py-2 px-4 rounded focus:shadow-outline hover:bg-indigo-600 cursor-pointer" @click="handleLogin">
            登录
          </button>
          <button type="button" class="w-full bg-gray-500 text-white font-bold py-2 px-4 rounded focus:shadow-outline  hover:bg-gray-600 cursor-pointer" @click="handleRegister">
            注册
          </button>
        </div>


      </div>
    </div>
  </div>

    
</template>
