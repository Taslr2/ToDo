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
      <Calendar />
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
