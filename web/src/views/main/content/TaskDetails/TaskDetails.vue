<script setup>
import { ref, defineProps, defineEmits, inject } from 'vue'
import house from '@/assets/svg/house.svg'
import navigation from '@/assets/svg/menu-icon.svg'
import TaskItem from '@/views/main/content/TaskDetails/TaskItem.vue'
import TimeLine from '@/views/main/content/TaskDetails/TimeLine/TimeLine.vue'

const props = defineProps(['isSidebarVisible'])
const emit = defineEmits(['makeSidebarVisible'])
const isLeftSidebarVisible = ref(true)

const toggleSidebar = () => {
  if (!props.isSidebarVisible) {
    isLeftSidebarVisible.value = !props.isSidebarVisible
    emit('makeSidebarVisible', isLeftSidebarVisible.value)
  }
}
</script>

<template>
  <div class="task-details">
    <div class="header-title">
      <img
        :class="['house', { navigation: !props.isSidebarVisible }]"
        :src="props.isSidebarVisible ? house : navigation"
        alt="house"
        @click="toggleSidebar"
      />
      <h3 class="title-text">任务详情</h3>
    </div>
    <div class="content">
      <div class="task-info">
        <TaskItem />
      </div>
      <div class="timeLine">
        <TimeLine />
      </div>
    </div>
  </div>
</template>

<style scoped>
.task-details {
  height: 100%;
}
.header-title {
  width: 100%;
  height: 60px;
  padding: 20px 20px 10px;
  margin: 10px;
  display: flex;
  align-items: center;
}
.house {
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

.content {
  float: left;
  width: 100%;
  /* height: calc(100% - 60px); */
  height: calc(100% - 120px);
}

.task-info {
  width: 50%;
  height: calc(100% - 50px);
  margin: 0 30px;
  /* background-color: pink; */
  overflow-y: auto; /* 添加滚动 */
}

/* 隐藏滚动条样式 */
.task-info::-webkit-scrollbar {
  width: 0;
  background: transparent;
}

.timeLine {
  width: 50%;
  /* position: relative;
  left: 25%; */
}
</style>
