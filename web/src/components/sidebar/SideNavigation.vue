<script setup>
import { ref } from 'vue'
import { defineEmits } from 'vue'

const emit = defineEmits(['updateVisibility'])

const isContentVisible = ref(true)

const toggleContent = () => {
  isContentVisible.value = !isContentVisible.value
  emit('updateVisibility', isContentVisible.value)
}
</script>

<template>
  <div>
    <div class="toggle" @click="toggleContent">
      <img
        class="menu-icon"
        src="@/assets/icons/menu-icon.svg"
        alt="Menu Icon"
        width="20"
        height="20"
      />
    </div>
    <div class="sidebar">
      <div class="total">
        <ul class="nav">
          <li>
            <router-link to="/">
              <img class="sun" src="@/assets/icons/calendar.svg" width="20" height="20" />日历
            </router-link>
          </li>
          <li>
            <router-link to="/fourquadrant">
              <img class="calendar" src="@/assets/icons/sun.svg" width="20" height="20" />四象限
            </router-link>
          </li>
          <li>
            <router-link to="/taskdetails">
              <img class="house" src="@/assets/icons/house.svg" width="20" height="20" />任务详情
            </router-link>
          </li>
          <li>
            <router-link to="/statisticalanalysis">
              <img class="house" src="@/assets/icons/bell.svg" width="20" height="20" />统计分析
            </router-link>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<style scoped>
body,
html {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
  background-color: white;
}

.sidebar {
  height: 100vh-48px;
  background-color: #fff;
  display: flex;
  flex-direction: column;
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
}
</style>
