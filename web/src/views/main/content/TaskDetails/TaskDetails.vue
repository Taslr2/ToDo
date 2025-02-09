<script setup>
import { ref, defineProps, defineEmits } from 'vue'
import house from '@/assets/svg/house.svg'
import navigation from '@/assets/svg/menu-icon.svg'
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
        任务详情
      </div>
      <div class="timeLine">
        <TimeLine />
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
  height: calc(100% - 60px);
}

.task-info {
  width: 50%;
  /* float: left; */
}

.timeLine {
  width: 50%;
  position: relative;
  left: 25%;
}
</style>
