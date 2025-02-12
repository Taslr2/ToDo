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
      <div class="calendar-container">
        <Calendar @dateSelected="handleDateSelected" />
      </div>
      <div class="task-list-container">
        <TaskListView :year="year" :month="month" :day="day" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, defineProps, defineEmits } from 'vue'
import calendar from '@/assets/svg/calendar.svg'
import navigation from '@/assets/svg/menu-icon.svg'
import Calendar from './Calendar.vue'
import TaskListView from './TaskListView.vue'

const props = defineProps(['isSidebarVisible'])
const emit = defineEmits(['makeSidebarVisible'])
const isLeftSidebarVisible = ref(true)

const toggleSidebar = () => {
  if (!props.isSidebarVisible) {
    isLeftSidebarVisible.value = !props.isSidebarVisible
    emit('makeSidebarVisible', isLeftSidebarVisible.value)
  }
}

const year = ref(0)
const month = ref(0)
const day = ref(0)

const handleDateSelected = (date) => {
  year.value = date.year
  month.value = date.month
  day.value = date.day
  // console.log("发送: ", year.value, month.value, day.value)
}

</script>

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
  height: calc(100% - 120px);
  padding: 20px;
  position: absolute;
  /* display: block;
  align-content: center; */
}

.calendar-container {
  width: 25%;
  /* height: 100%; */
  float: left;
  margin: 10px;
  position: absolute;
  top: 50%;
  transform: translateY(-55%);
  border: #fff solid 2px;
  z-index: 10;
}

.task-list-container {
  width: 30%;
  height: 402px;
  margin: 10px;
  border-radius: 12px;
  background: linear-gradient(0deg, #9786b8, #b4c7de);
  position: relative;
  left: calc(25% + 10px);
  position: absolute;
  top: 50%;
  transform: translateY(-55%);
  border: #fff solid 2px;
}
</style>
