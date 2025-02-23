<script setup>
import { defineProps, defineEmits, computed } from 'vue'
import rightCancel from '@/assets/svg/rightcancel.svg'
const props = defineProps(['isHelpVisible'])
const emit = defineEmits(['closeIsHelp'])
const isHelpVisible = props.isHelpVisible

const closeIsHelp = () => {
  emit('closeIsHelp', false)
}

const iconColor = computed(() => {
  return document.documentElement.classList.contains('dark-mode') ||
    document.body.classList.contains('dark-mode')
    ? 'filter:brightness(0) saturate(100%) invert(82%) sepia(22%) saturate(0%) hue-rotate(138deg) brightness(95%) contrast(92%)'
    : ''
})

</script>

<template>
  <transition>
    <div class="total" v-show="isHelpVisible">
      <div class="title">
        <h3>帮助</h3>
        <el-button @click="closeIsHelp" class="cancel" color="#faf9f8">
          <img :src="rightCancel" :style="iconColor"/>
        </el-button>
      </div>
      <div class="settingSection">
        <p><a href="#">了解详细信息</a></p>
        <el-button type="primary" color="#626aef">同步</el-button>
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

.settingSection {
  width: 328px;
  height: 74.2px;
  margin: 0 auto;
  padding: 0 5px;
}

.settingSection p {
  font-size: 14px;
  color: #2564cf;
  margin-bottom: 10px;
}

.settingSection a {
  color: #2564d2;
  text-decoration: none;
}

.settingSection a:visited {
  color: #2564d2;
}

.settingSection a:hover {
  text-decoration: underline;
  color: #2564d2;
}

.settingSection .el-button {
  margin-top: 8px;
  font-size: 14px;
  font-family: microsoftoeui, Tahoma, Geneva, Verdana, sans-serif;
  font-weight: 700;
}
</style>
