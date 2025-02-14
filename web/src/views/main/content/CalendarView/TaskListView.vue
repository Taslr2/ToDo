<template>
  <div class="TaskListView">
    <div class="calendar-content">
      <transition-group name="bounce" tag="div">
        <div v-for="(task, index) in filteredTasks" :key="index" class="task-card">
          <div class="task-title">{{ task.title }}</div>
          <div class="task-category">{{ task.category }}</div>
          <div class="task-date">{{ task.expectedCompletionDate }}</div>
        </div>
      </transition-group>
    </div>
    <div class="add-task-form">
      <form @submit.prevent="addCalendarEvent">
        <input v-model="calendarDescription" placeholder="Enter a task for this day" type="text" />
        <select v-model="calendarType">
          <option value="Work">Work</option>
          <option value="Study">Study</option>
          <option value="Social">Social</option>
          <option value="Other">Other</option>
        </select>
      </form>
    </div>
  </div>
</template>
  
<script setup>
import { ref, defineProps, watch } from 'vue'

// 定义接收的 props
const props = defineProps({
  year: Number,
  month: Number,
  day: Number, 
})
console.log("接收：", props.year, props.month, props.day)

// 初始化所有任务
import { tasks } from '@/api/tasks.js'
const allTasks = ref(tasks)

// 监听 props 变化并过滤任务
const filteredTasks = ref([])
watch([() => props.year, () => props.month, () => props.day], ([newYear, newMonth, newDay]) => {
  filteredTasks.value = allTasks.value.filter(task => {
    const taskDate = new Date(task.expectedCompletionDate)
    const taskYear = taskDate.getFullYear()
    const taskMonth = taskDate.getMonth() + 1
    const taskDay = taskDate.getDate()
    return taskYear === newYear && taskMonth === newMonth && taskDay === newDay
  })
  console.log("过滤：", filteredTasks.value)
})

// 添加 tasks 的相关数据
const calendarDescription = ref('')
const calendarType = ref('Work')

// 添加 tasks 的方法
const addCalendarEvent = () => {
  const newTask = {
    id: Date.now(), // 为每个任务生成唯一的 id
    title: calendarDescription.value,
    category: calendarType.value,
    expectedCompletionDate: new Date(props.year, props.month - 1, props.day).toISOString(), // 使用传递的 year, month, day
    isCompleted: false,
    completionDate: null,
    isDeleted: false,
    details: ''
  }
  allTasks.value.push(newTask)

  // 清空输入框
  calendarDescription.value = ''
  calendarType.value = 'Work'

  // 重新过滤任务
  filteredTasks.value = allTasks.value.filter(task => {
    const taskDate = new Date(task.expectedCompletionDate)
    const taskYear = taskDate.getFullYear()
    const taskMonth = taskDate.getMonth() + 1
    const taskDay = taskDate.getDate()
    return taskYear === props.year && taskMonth === props.month && taskDay === props.day
  })
}
</script>
  
<style scoped>
/* 你的样式代码保持不变 */
.TaskListView {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
}

.calendar-content {
  height: calc(100% - 50px);
  padding: 10px;
  flex-direction: column; /* 纵向排列 */
  gap: 10px; /* 卡片之间的间距 */
  overflow-y: scroll;
  scrollbar-width: none;
  -ms-overflow-style: none;
}

.calendar-content::-webkit-scrollbar {
  display: none;
}

.task-card {
  background-color: white;
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 10px;
  margin: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.task-title {
  font-weight: bold;
  margin-bottom: 5px;
}

.task-category,
.task-date {
  color: #555;
}

.add-task-form {
  width: 100%;
  height: 50px;
}

.add-task-form input {
  width: 80%;
  height: 40px;
  margin: 10px;
  margin-left: 0;
  border-bottom-left-radius: 12px;
  margin-right: 0px;
  background: #bbb5de;
  border: none;
  flex: 2;
  outline: none;
  color: #fff;
  text-indent: 2ch;
}

.add-task-form select {
  width: 20%;
  height: 40px;
  text-align: center;
  background: #c27dfa;
  border: none;
  /* padding: 1.2em; */
  outline: none;
  color: #fff;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
  border-bottom-left-radius: 0;
  border-bottom-right-radius: 12px;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
}

.bounce-enter-active {
  animation: bounce-in 0.5s;
}

.bounce-leave-active {
  animation: bounce-in 0.5s reverse;
}

@-webkit-keyframes bounce-in {
  0% {
    transform: translateY(-5px);
    opacity: 0;
  }
  50% {
    transform: translateY(0);
    opacity: 0.5;
  }
  100% {
    transform: translateY(0);
    opacity: 1;
  }
}

@keyframes bounce-in {
  0% {
    transform: translateY(-5px);
    opacity: 0;
  }
  50% {
    transform: translateY(0);
    opacity: 0.5;
  }
  100% {
    transform: translateY(0);
    opacity: 1;
  }
}
</style>
