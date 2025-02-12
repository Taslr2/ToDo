<script setup>
import { defineProps, defineEmits } from 'vue'
import rightCancel from '@/assets/svg/rightcancel.svg'
const props = defineProps(['isSettingVisible'])
const emit = defineEmits(['closeIsSetting'])
const isSettingVisible = props.isSettingVisible

const closeIsSetting = () => {
  emit('closeIsSetting', false)
  console.log('closeIsSetting')
}
</script>
<template>
  <transition>
    <div class="total" v-show="isSettingVisible">
      <div class="title">
        <h3>设置</h3>
        <button @click="closeIsSetting"><img :src="rightCancel" /></button>
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
