<script setup>
import { ref, defineProps, defineEmits } from 'vue'
import calendar from '@/assets/icons/calendar.svg'
import navigation from '@/assets/icons/menu-icon.svg'

import rotate_timeline from './rotate-timeline/rotate_timeline.vue'

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
      <div class="calendar-left">aaa</div>
      <div class="calendar-right">
        <rotate_timeline />
      </div>
    </div>
  </div>
</template>

<style scoped>
.calendar-view {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}

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
  height: calc(100% - 80px);
  display: flex;
  /* background-color: pink; */
}

.calendar-left {
  width: 50%;
  height: calc(100% - 20px);
  margin: 10px;
  /* background-color: purple; */
  border-right: 1px solid #ccc;
}

.calendar-right {
  width: 50%;
  height: calc(100% - 20px);
  /* margin: 10px; */
  /* background-image: linear-gradient(to bottom, #faf9f8 0%, #fbc2eb77 100%); */
}
</style>
