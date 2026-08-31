<script setup lang="ts">
import { h, ref } from 'vue'

const menus = ref([
'菜单1', '菜单2', '菜单3', '菜单4', '菜单5', '菜单6', '菜单7', '菜单8'
])


const activeMenu = ref('菜单1')

</script>

<template>
  <div class="flex h-screen">
    
    <aside class="w-48 bg-gray-100">
      <h2 class="h-26 flex items-center justify-center">左侧导航栏</h2>


      <button 
        v-for="menu in menus" 
        :key="menu" 
        class="flex items-center justify-center w-full h-16" 
        :class="{ 'bg-gray-200 font-bold text-blue-600': activeMenu === menu }"
        @click="activeMenu = menu">
        
        {{ menu }}
      </button>

    </aside>

    <main class="flex-1 flex flex-col">

      <header class="h-16 bg-gray-200 flex items-center justify-center">
      工作区头部
      </header>
      
      <content class="flex-1 flex flex-col">
        <h2 class="flex items-center justify-center">工作区内容</h2>

        <div class="flex-1 flex flex-col gap-8">
          <div class="p-8 h-1/3 bg-gray-200">
          菜单一
          </div>

          <div class="flex-1 flex gap-8 p-6 bg-gray-300 ">
            <div class="flex-1 flex flex-col gap-8">
              <div class="p-8 h-1/2 bg-gray-500 ">
                第1页面
              </div>

              <div class="p-8 h-1/2 bg-gray-500">
                第2页面
              </div>
            </div>  

            <div class="w-1/3 p-8 bg-gray-500">
              第3页面
            </div>
          </div>

          <div class="p-8 h-1/3 bg-gray-400">
            菜单三
          </div>
        </div>
        

      </content>
      
    </main>

  </div>
    
</template>
