<script setup>
import { defineProps, defineEmits } from 'vue'
import rightCancel from '@/assets/svg/rightcancel.svg'
const props = defineProps(['isNewVisible'])
const emit = defineEmits(['closeIsNew'])
const isNewVisible = props.isNewVisible

const closeIsNew = () => {
  emit('closeIsNew', false)
}
</script>
<template>
  <transition>
    <div class="total" v-show="isNewVisible">
      <div class="title">
        <h3>新增功能</h3>
        <button><img :src="rightCancel" @click="closeIsNew" /></button>
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
