<script setup>
import { ref, onMounted, inject, watch } from 'vue'
import axios from 'axios'
// import { tasks } from '@/api/tasks.js'

// 初始化所有任务
const tasks = ref([])

// 使用 axios 请求后端接口
axios
  .get('http://localhost:8080/todo/showTodos')
  .then((response) => {
    tasks.value = response.data
    console.log("tasks-1:", tasks.value)
  })
  .catch((error) => {
    console.error('获取任务列表失败:', error)
  })

// 监听 tasks 的变化
watch(tasks, (newTasks) => {
  console.log("tasks-2:", newTasks)
}, { immediate: true })

const props = defineProps({
  index: {
    type: Number,
    required: true
  }
})

const selectedTask = ref(null)

watch(() => {
  const filtered = tasks.value.filter(task => !task.isCompleted && !task.isDeleted)
  console.log("tasks-3:", tasks.value)
  console.log("filtered:", filtered)
  if (filtered.length > props.index) {
    selectedTask.value = filtered[props.index]
  }
})

// 格式化日期函数
const formatDateString = (dateString) => {
    const options = { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit', hour12: false }
    const date = new Date(dateString)
  return new Intl.DateTimeFormat('zh-CN', options).format(date)
}
</script>

<template>
  <div v-if="selectedTask">
    <div class="task-card">
      <h3>{{ selectedTask.title }}</h3>
      <p>类别: {{ selectedTask.category }}</p>
      <p>ddl: {{ formatDateString(selectedTask.expectedCompletionDate) }}</p>
    </div>
  </div>
  <div v-else>
    <p>没有更多的任务啦~可以去休息一下！</p>
  </div>
</template>

<style scoped>
.task-card {
  font-family: "微软雅黑", "楷体";
  line-height: 1.3;
  padding: 4px 9px;
}

.task-card h3 {
  color: #383aba;
  font-size: 18px;
  margin-bottom: 5px;
}

.task-card p {
  color: #4b4db1;
  font-size: 12px;
}
</style>