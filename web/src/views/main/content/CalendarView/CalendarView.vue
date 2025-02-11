<script setup>
import { ref, defineProps, defineEmits } from 'vue'
import calendar from '@/assets/svg/calendar.svg'
import navigation from '@/assets/svg/menu-icon.svg'
import Calendar from './Calendar.vue'

const props = defineProps(['isSidebarVisible'])
const emit = defineEmits(['makeSidebarVisible'])
const isLeftSidebarVisible = ref(true)

const toggleSidebar = () => {
  if (!props.isSidebarVisible) {
    isLeftSidebarVisible.value = !props.isSidebarVisible
    emit('makeSidebarVisible', isLeftSidebarVisible.value)
  }
}

import { tasks as taskList } from '@/api/tasks.js' 
const filteredTasks = ref([])  // 将 filteredTasks 定义为响应式引用

const handleDateSelected = (date) => {
  const year = date.year
  const month = date.month
  const day = date.day

  filteredTasks.value = taskList.filter(task => {
    const taskDate = new Date(task.expectedCompletionDate)
    const taskYear = taskDate.getFullYear()
    const taskMonth = taskDate.getMonth() + 1
    const taskDay = taskDate.getDate()
    return taskYear === year && taskMonth === month && taskDay === day
  })

  console.log(filteredTasks.value)
}
</script>

<template>
  <div class="calendar-view">
    <div class="header-title">
      <img
        :class="['calendar', { navigation: !props.isSidebarVisible }]"
        :src="props.isSidebarVisible ? calendar : navigation"
        alt="calendar"
        @click="toggleSidebar"
      />
      <h3 class="title-text">日历</h3>
    </div>
    <div class="calendar-content">
      <Calendar @dateSelected="handleDateSelected" />
      <div v-for="task in filteredTasks" :key="task.id">
        {{ task.title }} - {{ task.category }} - {{ task.expectedCompletionDate }}
      </div>
    </div>
  </div>
</template>


<style scoped>
.header-title {
  width: 100%;
  height: 60px;
  padding: 20px 20px 10px;
  margin: 10px;
  display: flex;
  align-items: center;
}
.calendar {
  width: 20px;
  height: 20px;
  margin-right: 10px;
  margin-bottom: 3px;
}
.navigation {
  cursor: pointer;
}
.title-text {
  font-size: 24px;
  font-family: '华文隶书';
}

.calendar-content {
  width: 100%;
  height: calc(100% - 60px);
  padding: 20px;
}
</style>
