<script setup>
import UpperRightComponent from '@/components/head/UpperRightComponent.vue'
import SearchBox from '@/components/head/SearchBox.vue'
import { ref } from 'vue'

const activeBox = ref('')
const refreshPage = () => {
  window.location.reload()
}
const handleMouseDown = (box) => {
  activeBox.value = box
}
</script>

<template>
  <div class="total">
    <div :class="['icon', { active: activeBox === 'icon' }]" @mousedown="handleMouseDown('icon')">
      <img src="@/assets/svg/sign.svg" alt="" @click="refreshPage" />
    </div>
    <div class="title" @click="refreshPage">To Do</div>
    <div class="search"><SearchBox /></div>
    <div class="right"><UpperRightComponent /></div>
  </div>
</template>

<style scoped>
.total {
  display: flex;
  align-items: center;
  height: 48px;
  width: 100%;
  background-color: rgb(37, 100, 207);
  position: relative;
}
.icon {
  width: 48px;
  height: 48px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
}
.icon:hover {
  background-color: #005a9e;
  cursor: pointer;
}
.title {
  font-size: 16px;
  color: white;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 48px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  margin: 0 10px;
  font-weight: 500;
}
.title:hover {
  cursor: pointer;
  text-decoration: underline;
}
.search {
  flex: 1;
  display: flex;
  justify-content: center;
}
.right {
  width: 192px;
  height: 48px;
}
.icon.active {
  background-color: #173f85;
  border: 0.5px solid white;
  box-sizing: border-box;
}
</style>
