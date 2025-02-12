<script setup>
import { defineProps, defineEmits } from 'vue'
import rightCancel from '@/assets/svg/rightcancel.svg'
const props = defineProps(['isHelpVisible'])
const emit = defineEmits(['closeIsHelp'])
const isHelpVisible = props.isHelpVisible

const closeIsHelp = () => {
  emit('closeIsHelp', false)
}
</script>
<template>
  <transition>
    <div class="total" v-show="isHelpVisible">
      <div class="title">
        <h3>帮助</h3>
        <button @click="closeIsHelp"><img :src="rightCancel"  /></button>
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
