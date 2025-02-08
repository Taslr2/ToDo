<script setup>
import HeadNavigation from '@/components/HeadNavigation.vue'
import SideNavigation from '@/components/sidebar/SideNavigation.vue'
import { ref, provide, onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { tasks } from '@/api/tasks.js' // 导入 tasks 数据，后续需要改为后端接口

const isSidebarVisible = ref(true)
const router = useRouter()
const route = useRoute()

const handleUpdateVisibility = (visibility) => {
  isSidebarVisible.value = visibility
}

const convertSidebarVisible = (visibility) => {
  isSidebarVisible.value = visibility
}

const tasksRef = ref(tasks) // 使用 ref 包装 tasks 数据

provide('tasks', tasksRef)

onMounted(() => {
  if (route.path !== '/') {
    router.push('/')
  }
})
</script>

<template>
  <div>
    <div class="head">
      <HeadNavigation />
    </div>
    <div class="main-container">
      <SideNavigation
        @updateVisibility="handleUpdateVisibility"
        :isSidebarVisible="isSidebarVisible"
      />
      <div class="content">
        <RouterView
          :is-sidebar-visible="isSidebarVisible"
          @makeSidebarVisible="convertSidebarVisible"
        />
      </div>
    </div>
  </div>
</template>

<style scoped>
.main-container {
  display: flex;
  height: calc(100vh - 48px);
  overflow: hidden;
}

.sidebar {
  width: 290px;
  background-color: #fff;
}

.content {
  width: 100%;
  transition: width 0.5s ease;
}
</style>
