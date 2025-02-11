<script setup>
import { ref, defineEmits, defineProps, watch } from 'vue'
import { useRoute } from 'vue-router'

import calendarIcon from '@/assets/svg/calendar.svg'
import sunIcon from '@/assets/svg/sun.svg'
import houseIcon from '@/assets/svg/house.svg'
import bellIcon from '@/assets/svg/bell.svg'

const emit = defineEmits(['updateVisibility'])
const props = defineProps(['isSidebarVisible'])

const isContentVisible = ref(props.isSidebarVisible)
const selectedIndex = ref(0)
const route = useRoute()

watch(
  () => props.isSidebarVisible,
  (newVal) => {
    isContentVisible.value = newVal
  },
)

const toggleContent = () => {
  isContentVisible.value = !isContentVisible.value
  emit('updateVisibility', isContentVisible.value)
}

const whetherselected = (index) => {
  selectedIndex.value = index
}

const menuItems = [
  { text: '日历', iconClass: 'sun', iconSrc: calendarIcon, route: '/' },
  { text: '四象限', iconClass: 'calendar', iconSrc: sunIcon, route: '/fourquadrant' },
  { text: '任务详情', iconClass: 'house', iconSrc: houseIcon, route: '/taskdetails' },
  { text: '统计分析', iconClass: 'house', iconSrc: bellIcon, route: '/statisticalanalysis' },
]

watch(
  () => route.path,
  (newPath) => {
    const index = menuItems.findIndex((item) => item.route === newPath)
    if (index !== -1) {
      selectedIndex.value = index
    }
  },
)

const initSelectedIndex = () => {
  const index = menuItems.findIndex((item) => item.route === route.path)
  if (index !== -1) {
    selectedIndex.value = index
  }
}

initSelectedIndex()
</script>

<template>
  <transition
    name="leftside"
    leave-active-class="animate__animated animate__slideOutLeft custom-duration"
    enter-active-class="animate__animated animate__slideInLeft custom-duration"
  >
    <div class="all" v-show="isContentVisible">
      <div class="toggle">
        <img
          class="menu-icon"
          src="@/assets/svg/menu-icon.svg"
          alt="Menu Icon"
          width="20"
          height="20"
          @click="toggleContent"
        />
      </div>
      <div class="sidebar">
        <div class="total">
          <ul class="nav">
            <router-link v-for="(item, index) in menuItems" :key="index" :to="item.route">
              <li
                :class="selectedIndex === index ? 'selected' : 'unselected'"
                @click="whetherselected(index)"
              >
                <img :src="item.iconSrc" width="20" height="20" />
                <span class="text">{{ item.text }}</span>
              </li>
            </router-link>
          </ul>
          <div class="ul-last"></div>
        </div>
      </div>
    </div>
  </transition>
</template>

<style scoped>
.custom-duration {
  animation-duration: 1s !important;
}

.sidebar {
  background-color: #fff;
  display: flex;
  flex-direction: column;
}

.all {
  flex: 1;
  width: 290px;
  display: flex;
  flex-direction: column;
  background-color: #fff;
  position: relative;
  height: calc(100vh - 48px);
  animation-duration: 1s;
}

.all::after {
  content: '';
  position: absolute;
  top: 0;
  right: 0;
  width: 3px;
  height: 100%;
  background: linear-gradient(to right, #eee, #faf9f8);
}

.toggle {
  display: flex;
  width: 100%;
  height: 48px;
  box-sizing: border-box;
  margin: 16px 0px 0px;
  padding: 0px 24px;
  align-items: center;
  background-color: #fff;
}

.menu-icon {
  cursor: pointer;
}

.nav li {
  cursor: pointer;
  width: 100%;
  height: 44px;
  padding: 12px 24px;
  box-sizing: border-box;
  transition: transform 0.5s ease;
  position: relative;
}

.nav li img {
  display: inline-block;
  vertical-align: middle;
  margin-right: 16px;
}

.ul-last {
  width: 258px;
  height: 1px;
  margin: 9px 16px;
  background-color: #e1dfdd;
  transform: scaleY(1.1);
}

.text {
  display: inline-block;
  vertical-align: middle;
  color: #333;
}

.unselected {
  font-size: 14px;
  font-family: Microsoft YaHei;
}

.unselected:hover {
  background-color: #f5f5f5;
}

.selected {
  font-size: 14px;
  background-color: #eff6fc;
  color: #000;
  font-weight: 900;
}

.selected::before {
  content: '';
  position: absolute;
  left: 0;
  top: 0;
  width: 2px;
  height: 100%;
  background-color: #2564cf;
  animation: zoomIn 0.5s;
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
/* .leftside-enter,
.leftside-leave-to {
  width: 0;
  overflow: hidden;
}

.leftside-enter-active,
.leftside-leave-active {
  transition: width 0.5s ease;
}

.leftside-enter-to,
.leftside-leave {
  width: 290px;
} */
</style>
