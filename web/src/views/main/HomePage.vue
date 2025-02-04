<script setup>
import HeadNavigation from '@/components/HeadNavigation.vue';
import SideNavigation from '@/components/sidebar/SideNavigation.vue';
import { ref } from 'vue';

const isSidebarVisible = ref(true);

const handleUpdateVisibility = (visibility) => {
  isSidebarVisible.value = visibility;
};
</script>

<template>
  <HeadNavigation />
  <div class="main-container">
    <div :class="['sidebar', { 'hidden-sidebar': !isSidebarVisible }]">
      <SideNavigation @updateVisibility="handleUpdateVisibility" />
    </div>
    <div class="content">
      <RouterView />
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

.main-container {
  display: flex;
  height: calc(100vh - 48px);
}

.sidebar {
  width: 290px;
  background-color: #fff;
  transition: width 0.3s;
}

.hidden-sidebar {
  width: 0;
  overflow: hidden;
}

.content {
  width: 100%;
}
</style>
