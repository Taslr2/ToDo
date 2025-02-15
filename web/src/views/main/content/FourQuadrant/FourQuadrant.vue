<script setup>
import { ref, onMounted, onUnmounted } from 'vue'
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

// 放大quedrantBox
const isClicked = ref([false, false, false, false])
const toggleFullScreen = (index) => {
  console.log("点击了四象限的", index)
  isClicked.value[index] = !isClicked.value[index]
}

const handleKeyDown = (event) => {  // 按下Esc键退出全屏
  if (event.key === 'Escape') {
    isClicked.value = [false, false, false, false];
  }
}

onMounted(() => {
  window.addEventListener('keydown', handleKeyDown);
});

onUnmounted(() => {
  window.removeEventListener('keydown', handleKeyDown);
});
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
      <div
        class="quedrantBox"
        :class="{ 'clicked-box': isClicked[0] }"
        @click="toggleFullScreen(0)"
      >
        重要但不紧急
      </div>
      <div
        class="quedrantBox"
        :class="{ 'clicked-box': isClicked[1] }"
        @click="toggleFullScreen(1)"
      >
        重要且紧急
      </div>
      <div
        class="quedrantBox"
        :class="{ 'clicked-box': isClicked[2] }"
        @click="toggleFullScreen(2)"
      >
        不重要且不紧急
      </div>
      <div
        class="quedrantBox"
        :class="{ 'clicked-box': isClicked[3] }"
        @click="toggleFullScreen(3)"
      >
        紧急但不重要
      </div>
    </div>
    <div class="notes">
      <p>点击象限可以放大，按下Esc键退出全屏</p>
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
  width: 90%;
  height: 85%;
  position: relative;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -55%);
}

.quedrantBox {
  width: calc(50% - 20px);
  height: calc(50% - 60px);
  position: absolute;
  float: left;
  margin: 10px;
  transition: all 0.3s ease;
}

.quadrant-container> .quedrantBox:nth-child(1) {
  background-color: #d09bfb;
  left: 0px;
  top: 0px;
  z-index: 1;
}

.quadrant-container> .quedrantBox:nth-child(2) {
  background-color: #a43ef8;
  left: 50%;
  top: 0px;
  z-index: 1;
}

.quadrant-container> .quedrantBox:nth-child(3) {
  background-color: #e5d1f5;
  left: 0px;
  top: calc(50% - 40px);
  z-index: 1;
}

.quadrant-container> .quedrantBox:nth-child(4) {
  background-color: #b87aeb;
  left: 50%;
  top: calc(50% - 40px);
  z-index: 1;
}

.quadrant-container > .quedrantBox.clicked-box {
  left: 0px;
  top: 0px;
  width: calc(100% - 20px);
  height: calc(100% - 100px);
  z-index: 10;
}

.notes {
  /* background-color: pink; */
  text-align: center;
  color: #a4a4a4;
  position: relative;
  top: -6%;
}
</style>
