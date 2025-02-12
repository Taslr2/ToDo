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
        <button @click="closeIsPersonal"><img :src="rightCancel" /></button>
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
  height: 58px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid #e5e5e5;
}
</style>
