<script setup>
import UpperRightComponent from '@/components/head/UpperRightComponent.vue'
const refreshPage = () => {
  window.location.reload()
}
</script>

<template>
  <div class="total">
    <div class="icon"><img src="@/assets/icons/sign.svg" alt="" /></div>
    <div class="title" @click="refreshPage">To Do</div>
    <UpperRightComponent class="upper-right" />
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
.upper-right {
  position: absolute;
  right: 0;
  top: 0;
}
</style>
