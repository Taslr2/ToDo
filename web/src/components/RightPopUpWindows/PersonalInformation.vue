<script setup>
import { defineProps, defineEmits, ref } from 'vue'
import rightCancel from '@/assets/svg/rightcancel.svg'
const props = defineProps(['isPersonalVisible'])
const emit = defineEmits(['closeIsPersonal'])
const isPersonalVisible = props.isPersonalVisible

const closeIsPersonal = () => {
  emit('closeIsPersonal', false)
}

const first_make_achievements = ref(true)
const first_finish_achievements = ref(true)
const consecutive_achievements_achievements = ref(false)
const over_10_one_day_achievements = ref(false)
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
          <img src="@/assets/img/head-portrait.jpg" alt="#" />
        </div>
        <div class="personal-name">待办高手</div>
        <div class="personal-detail">专注提升效率的打工人~</div>
        <div class="usual-tag">
          <div class="tag-work">Work</div>
          <div class="tag-study">Study</div>
          <div class="tag-social">Social</div>
          <div class="tag-other">Other</div>
        </div>
        <div class="achievements">
          <div
            class="achievement-card first-make"
            :class="{ grayscale: !first_make_achievements }"
          >
            <img src="@/assets/img/first_make.png" alt="" />
            <div class="achievement-info">成功创建第一个Todo!</div>
          </div>
          <div
            class="achievement-card first-finish"
            :class="{ grayscale: !first_finish_achievements }"
          >
            <img src="@/assets/img/first_finish.png" alt="" />
            <div class="achievement-info">成功完成第一个Todo!</div>
          </div>
          <div
            class="achievement-card consecutive_achievements"
            :class="{ grayscale: !consecutive_achievements_achievements }"
          >
            <img src="@/assets/img/consecutive_achievements.png" alt="" />
            <div class="achievement-info">连续7天完成Todo!</div>
          </div>
          <div
           class="achievement-card over_10_one_day"
           :class="{ grayscale: !over_10_one_day_achievements }">
            <img src="@/assets/img/over_10_one_day.png" alt="" />
            <div class="achievement-info">一天完成10个以上Todo!</div>
          </div>
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
  overflow: scroll;
}

.total::-webkit-scrollbar {
  display: none;
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

.tag-work,
.tag-study,
.tag-social,
.tag-other {
  width: calc(25% - 12px);
  padding: 10px 0;
  text-align: center;
  border-radius: 25px;
  color: white;
  font-size: 16px;
  cursor: pointer;
  box-shadow: #8b8b8b 0px 2px 4px;
}

.tag-work {
  background-color: #4caf50;
}

.tag-study {
  background-color: #2196f3;
}

.tag-social {
  background-color: #f44336;
}

.tag-other {
  background-color: #ff9800;
}

.achievements {
  width: 240px;
  margin: 25px auto;
}

.achievement-card {
  height: 80px;
  margin: 10px 0;
  padding: 10px;
  border-radius: 12px;
  box-shadow: #b3b3b3 0px 2px 4px;
  transition: all 0.3s ease-in-out;
}

.achievement-card:hover {
  box-shadow: #b3b3b3 0px 2px 10px;
}

.achievements img {
  width: 60px;
  height: 60px;
  float: left;
}

.achievements .achievement-info {
  width: calc(100% - 60px);
  height: 60px;
  padding: 10px;
  float: left;
  font-size: 14px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}

.grayscale {
  filter: grayscale(100%); /* 灰度效果 */
}
</style>
