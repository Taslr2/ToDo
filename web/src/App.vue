<script setup>
import { ref, provide } from 'vue'
import Loading from '@/components/loading/loading.vue'

const loadingRef = ref(null)

// 提供全局 loading 方法
provide('showLoading', () => loadingRef.value?.showLoading())
provide('hideLoading', () => loadingRef.value?.hideLoading())
</script>

<template>
  <div>
    <Loading ref="loadingRef" />
    <RouterView />
  </div>
</template>

<style scoped></style>
