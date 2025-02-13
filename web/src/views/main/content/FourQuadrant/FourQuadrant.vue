<script setup>
import { ref, defineProps, defineEmits } from 'vue'
import sun from '@/assets/svg/sun.svg'
import navigation from '@/assets/svg/menu-icon.svg'

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
  <div class="four-quadrant-view">
    <div class="header-title">
      <img
        :class="['sun', { navigation: !props.isSidebarVisible }]"
        :src="props.isSidebarVisible ? sun : navigation"
        alt="sun"
        @click="toggleSidebar"
      />
      <h3 class="title-text">四象限</h3>
    </div>
    <div class="quadrant-container">
      <div class="quedrantBox">重要但不紧急</div>
      <div class="quedrantBox">重要且紧急</div>
      <div class="quedrantBox">不重要且不紧急</div>
      <div class="quedrantBox">紧急但不重要</div>
    </div>
  </div>
</template>

<style scoped>
.four-quadrant-view {
  width: 100%;
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
.sun {
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

.quadrant-container {
  width: 100%;
  height: 100%;
}

.quedrantBox {
  width: calc(50% - 20px);
  height: calc(50% - 60px);
  background-color: pink;
  float: left;
  margin: 10px;
}
</style>
