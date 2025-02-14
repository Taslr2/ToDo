<template>
    <transition name="fade" mode="out-in">
      <div class="modify-task" v-if="currentSelectedTask" :key="currentSelectedTask.title">
        <h2>{{ currentSelectedTask.title }}</h2>
        <p>类别: {{ currentSelectedTask.category }}</p>
        <p>详情: {{ currentSelectedTask.details }}</p>
        <p>预计完成日期: {{ currentSelectedTask.expectedCompletionDate }}</p>
        <!-- 根据需要添加其他属性 -->
      </div>
    </transition>
  </template>
  
  <script setup>
  import { defineProps, watch, ref } from 'vue'
  
  const props = defineProps({
    selectedTask: Object,
    year: Number,
    month: Number,
    day: Number,
  })
  
  // 接收点击的任务
  const currentSelectedTask = ref(props.selectedTask)
  watch(
    () => props.selectedTask,
    (newTask) => {
      currentSelectedTask.value = newTask
      console.log('点击任务:', newTask)
    }
  )
  
  watch([() => props.year, () => props.month, () => props.day], () => {
    currentSelectedTask.value = null
  })
  </script>
  
  <style scoped>
  .modify-task {
    padding: 22px;
  }
  
  .fade-enter-active,
  .fade-leave-active {
    transition: opacity 0.5s;
  }
  .fade-enter-from,
  .fade-leave-to {
    opacity: 0;
  }
  </style>
  