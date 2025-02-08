<template>
  <div class="StatisticalAnalysis-header">
    <div class="header-title">
      <img
        :src="props.isSidebarVisible ? greendot : navigation"
        alt="dot"
        :class="props.isSidebarVisible ? 'dot' : 'navigation'"
        @click="toggleSidebar"
      />
      <h3 class="title-text">任务数据统计</h3>
    </div>
    <div class="header-blocks">
      <div class="block-item">
        <p class="block-item-title">{{ allCount }}</p>
        <p class="block-item-text">任务总数</p>
        <p class="block-item-find-more">Look for more</p>
        <img src="../icons/more.png" alt="more" class="more-icon" />
      </div>
      <div class="block-item">
        <p class="block-item-title">{{ completedCount }}</p>
        <p class="block-item-text">已完成</p>
        <p class="block-item-find-more">Look for more</p>
        <img src="../icons/more.png" alt="more" class="more-icon" />
      </div>
      <div class="block-item">
        <p class="block-item-title">{{ unfinishedCount }}</p>
        <p class="block-item-text">待完成</p>
        <p class="block-item-find-more">Look for more</p>
        <img src="../icons/more.png" alt="more" class="more-icon" />
      </div>
      <div class="block-item">
        <p class="block-item-title">{{ deleteCount }}</p>
        <p class="block-item-text">已放弃</p>
        <p class="block-item-find-more">Look for more</p>
        <img src="../icons/more.png" alt="more" class="more-icon" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, inject, defineProps, defineEmits } from 'vue'
import greendot from '../icons/green-dot.png'
import navigation from '@/assets/svg/menu-icon.svg'

const allTasks = inject('allTasks')
const completedTasks = inject('completedTasks')
const unfinishedTasks = inject('unfinishedTasks')
const deleteTasks = inject('deleteTasks')

const allCount = ref(allTasks.value.length)
const completedCount = ref(completedTasks.value.length)
const unfinishedCount = ref(unfinishedTasks.value.length)
const deleteCount = ref(deleteTasks.value.length)

const props = defineProps(['isSidebarVisible'])
const emit = defineEmits(['makeSidebarVisible'])

const toggleSidebar = () => {
  if (!props.isSidebarVisible) {
    emit('makeSidebarVisible', props.isSidebarVisible)
  }
}
</script>

<style scoped>
.header-title {
  padding: 20px 10px;
  padding-bottom: 10px;
  display: flex;
  align-items: center;
}

.dot {
  width: 30px;
  height: 30px;
}

.navigation {
  width: 20px;
  height: 20px;
  margin-right: 10px;
  margin-bottom: 3px;
  cursor: pointer;
}

.title-text {
  font-size: 24px;
  font-family: '华文隶书';
}

.header-blocks {
  width: 100%;
  height: 185px;
  display: flex;
}

.block-item {
  width: calc(25% - 20px);
  margin: 10px;
  border-radius: 15px;
  padding: 30px;
  transition: all 0.2s ease-in-out;
}

.block-item:hover {
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.5);
}

.header-blocks > :nth-child(1) {
  background: linear-gradient(135deg, #7881f7, #75caf9);
}

.header-blocks > :nth-child(2) {
  background: linear-gradient(135deg, #ec958f, #e17785);
}

.header-blocks > :nth-child(3) {
  background: linear-gradient(135deg, #64b3dd, #82eda3);
}

.header-blocks > :nth-child(4) {
  background: linear-gradient(135deg, #867ef5, #e388e8);
}

.block-item-title {
  font-size: 36px;
  font-family: 'Arial Black';
  color: #f1f1f1;
}

.block-item-text {
  font-size: 18px;
  font-family: '华文隶书';
  color: #f1f1f1;
  margin-top: 10px;
}

.block-item-find-more {
  text-align: right;
  font-size: 12px;
  margin-top: 10px;
  color: #f1f1f1;
}

.more-icon {
  width: 45px;
  height: 45px;
  margin-left: calc(100% - 50px);
}
</style>
