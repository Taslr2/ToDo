<script setup>
import { defineProps, defineEmits } from 'vue'
import rightCancel from '@/assets/svg/rightcancel.svg'
const props = defineProps(['isPersonalVisible'])
const emit = defineEmits(['closeIsPersonal'])
const isPersonalVisible = props.isPersonalVisible

const closeIsPersonal = () => {
  emit('closeIsPersonal', false)
}
</script>
<template>
  <transition>
    <div class="total" v-show="isPersonalVisible">
      <div class="title">
        <h3>个人主页</h3>
        <el-button @click="closeIsPersonal" class="cancel" color="#faf9f8"
          ><img :src="rightCancel"
        /></el-button>
      </div>
    </div>
  </transition>
</template>
<style scoped>
.total {
  width: 350px;
  height: 100%;
  background-color: #faf9f8;
}
.title {
  width: 100%;
  height: 65px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 10px;
}
.title h3 {
  font-size: 20px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  color: #252423;
  margin: 0 0 0 6px;
  font-weight: 700;
}
.cancel:hover {
  background-color: #cccbca;
}
.cancel:active {
  border: 2px solid #101010;
}
</style>
