<script setup>
import HeadNavigation from '@/components/head/HeadNavigation.vue'
import SideNavigation from '@/components/sidebar/SideNavigation.vue'
import { ref, provide, onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { tasks as taskList } from '@/api/tasks.js'  // 引入任务数组

const isSidebarVisible = ref(true)
const router = useRouter()
const route = useRoute()

const handleUpdateVisibility = (visibility) => {
  isSidebarVisible.value = visibility
}

const convertSidebarVisible = (visibility) => {
  isSidebarVisible.value = visibility
}

const tasks = ref(taskList)

provide('tasks', tasks)

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
