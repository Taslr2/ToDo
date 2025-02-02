<script setup>
import HeadNavigation from '@/components/HeadNavigation.vue'
import SideNavigation from '@/components/sidebar/SideNavigation.vue'
</script>

<template>
  <HeadNavigation />
  <div class="main-container">
    <div class="sidebar">
      <SideNavigation />
    </div>
    <div class="content">
      <div>111</div>
    </div>
  </div>
</template>

<style scoped>
.main-container {
  display: flex;
  height: calc(100vh - 48px);
}
.content {
  width: 100%;
}
</style>
