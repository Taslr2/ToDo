<template>
  <div v-if="isLoading" class="loading-overlay">
    <p>加载中...</p>
  </div>
</template>
  
<script setup>
import { ref, onMounted, onUnmounted } from 'vue'

const isLoading = ref(false)

const showLoading = () => {
  isLoading.value = true
}
const hideLoading = () => {
  isLoading.value = false
}

// 提供给外部使用
defineExpose({ showLoading, hideLoading })
</script>
  
<style>
.loading-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.7);
  color: white;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
  z-index: 1000;
}
</style>
  