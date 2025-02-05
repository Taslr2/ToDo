<script setup>
import { ref } from 'vue'
import { defineEmits } from 'vue'

import calendarIcon from '@/assets/icons/calendar.svg'
import sunIcon from '@/assets/icons/sun.svg'
import houseIcon from '@/assets/icons/house.svg'
import bellIcon from '@/assets/icons/bell.svg'

const emit = defineEmits(['updateVisibility'])

const isContentVisible = ref(true)
const selectedIndex = ref(0)

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
</script>

<template>
  <div class="toggle">
    <img
      class="menu-icon"
      src="@/assets/icons/menu-icon.svg"
      alt="Menu Icon"
      width="20"
      height="20"
      @click="toggleContent"
    />
  </div>
  <div class="sidebar" v-show="isContentVisible">
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
    </div>
  </div>
</template>

<style scoped>
.body,
html {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
  background-color: white;
}

.sidebar {
  background-color: #fff;
  display: flex;
  flex-direction: column;
  transition: width 0.5s;
}

.total {
  flex: 1;
  width: 290px;
  display: flex;
  flex-direction: column;
  background-color: #fff;
  position: relative;
}

.total::after {
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
}

.nav li img {
  display: inline-block;
  vertical-align: middle;
  margin-right: 16px;
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
</style>
