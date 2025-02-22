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
      <div>
        <div class="personal-head-portrait">
          <img src="@/assets/img/head-portrait.jpg" alt="#">
        </div>
        <div class="personal-name">待办高手</div>
        <div class="personal-detail">专注提升效率的打工人~</div>
        <div class="usual-tag">
          <div class="tag-work">Work</div>
          <div class="tag-study">Study</div>
          <div class="tag-social">Social</div>
          <div class="tag-other">Other</div>
        </div>
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

.personal-head-portrait {
  width: 200px;
  height: 200px;
  margin: 0 auto;
  margin-top: 30px;
}

.personal-head-portrait img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.3);
}

.personal-name {
  text-align: center;
  font-size: 24px;
  color: #2c2c2c;
  margin-top: 25px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.personal-detail {
  text-align: center;
  font-size: 16px;
  color: #767676;
  margin-top: 15px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.usual-tag {
  width: 100%;
  display: flex;
  justify-content: center;
  margin-top: 25px;
}

.usual-tag > div {
  margin: 0 3px;
}

.tag-work, .tag-study, .tag-social, .tag-other {
  width: calc(25% - 12px);
  padding: 10px 0;
  text-align: center;
  border-radius: 25px;
  color: white;
  font-size: 16px;
  cursor: pointer;
}

.tag-work {
  background-color: #4CAF50;
}

.tag-study {
  background-color: #2196F3;
}

.tag-social {
  background-color: #f44336;
}

.tag-other {
  background-color: #FF9800;
}

.achievements img {
  width: 60px;
  height: 60px;
}
</style>
