<script setup>
import { ref, defineProps, defineEmits } from 'vue'
import sun from '@/assets/icons/sun.svg'
import navigation from '@/assets/icons/menu-icon.svg'

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
  <div class="header-title">
    <img
      :class="['sun', { navigation: !props.isSidebarVisible }]"
      :src="props.isSidebarVisible ? sun : navigation"
      alt="sun"
      @click="toggleSidebar"
    />
    <h3 class="title-text">四象限</h3>
  </div>
</template>

<style scoped>
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
</style>
