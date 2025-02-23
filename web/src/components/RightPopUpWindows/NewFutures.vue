z
<script setup>
import { defineProps, defineEmits, computed, onMounted } from 'vue'
import rightCancel from '@/assets/svg/rightcancel.svg'
const props = defineProps(['isNewVisible'])
const emit = defineEmits(['closeIsNew'])
const isNewVisible = props.isPersonalVisible
const closeIsNew = () => {
  emit('closeIsNew', false)
}

const listButton = () => {
  console.log('hello')
}

const updateButtonStyle = () => {
    const customButtons = document.querySelectorAll('.custom-button');
    customButtons.forEach(button => {
        const isDarkMode = document.documentElement.classList.contains('dark-mode');
        button.style.backgroundColor = isDarkMode ? '#383838' : '#fff';
        button.style.borderColor = isDarkMode? '#ccc' : '';
        const span = button.querySelector('span');
        if(span){
            span.style.color = isDarkMode ? '#ccc' : '#000';
        }
    });
}

onMounted(() => {
  updateButtonStyle();

  const observer = new MutationObserver(updateButtonStyle);
    observer.observe(document.documentElement, { attributes: true, attributeFilter: ['class'] });
})

const menuItems = [
  {
    imgSrc: '',
    mainText: '现在，你可以在设置中更改为深色模式主题!',
    buttonText: '试用',
  },
  {
    imgSrc: '',
    mainText: '现在，可以通过拖动窗格边缘来调整详细信息窗格的大小!',
    buttonText: '明白了',
  },
  {
    imgSrc: '',
    mainText: '现在可以按类别对任务进行分组。在"排序"按钮旁边可以找到新的"分组依据"选项!',
    buttonText: '明白了',
  },
]

const iconColor = computed(() => {
  return document.documentElement.classList.contains('dark-mode') ||
    document.body.classList.contains('dark-mode')
    ? 'filter:brightness(0) saturate(100%) invert(82%) sepia(22%) saturate(0%) hue-rotate(138deg) brightness(95%) contrast(92%)'
    : ''
})
</script>

<template>
  <transition>
    <div class="total" v-show="isNewVisible">
      <div class="title">
        <h3>新增功能</h3>
        <el-button @click="closeIsNew" class="cancel" color="#faf9f8">
          <img :src="rightCancel" :style="iconColor" />
        </el-button>
      </div>
      <div class="content">
        <ul class="wholeList" v-for="(item, index) in menuItems" :key="index">
          <li class="innerList">
            <div class="Img">
              <img :src="item.imgSrc" alt="暂无图片" class="innerImg" />
            </div>
            <div class="innerContent" :class="{ 'taller-box': index === 2 }">
              {{ item.mainText }}
              <el-button type="" @click="listButton" class="custom-button">
                {{ item.buttonText }}
              </el-button>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </transition>
</template>

<style scoped>
.total {
  width: 350px;
  height: 100%;
  background-color: #faf9f8;
  overflow-y: auto;
  overflow-x: hidden;

  scrollbar-width: thin;
  scrollbar-color: #8f8f8f transparent;
}

.total::-webkit-scrollbar {
  width: 6px;
}

.total::-webkit-scrollbar-track {
  background: transparent;
}

.total::-webkit-scrollbar-thumb {
  background-color: #c1c1c1;
  border-radius: 3px;
}

.total::-webkit-scrollbar-thumb:hover {
  background-color: #a8a8a8;
}

.title {
  width: 100%;
  height: 65px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 10px;
  margin-bottom: 20px;
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

.content {
  width: 100%;
  height: 100%;
}

.wholeList {
  width: 318px;
  margin: 0 5px 0 auto;
}

.innerList {
  margin-bottom: 30px;
}

.innerImg {
  object-fit: contain;
}

.Img {
  width: 320px;
  height: 180px;
  border-radius: 2px 2px 0 0;
  background-color: #daecff;
}

.innerContent {
  width: 318px;
  height: 142.67px;
  padding: 20px 28px;
  background-color: #fff;
  border-radius: 0 0 2px 2px;
  box-shadow:
    2px 0 2px rgba(101, 98, 98, 0.08),
    0 2px 3px rgba(104, 101, 101, 0.15);
  border-top: 0;
  font-size: 16.8px;
  font-family: Microsoft YaHei;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  position: relative;
  color: #292827;
  line-height: 1.6;
}

.taller-box {
  height: 165.33px !important;
}
</style>

<style>
:deep(.custom-button) {
  width: 53px !important;
  height: 33px !important;
  background-color: #fff !important;
  position: absolute;
  left: 28px;
  bottom: 20px;
  padding: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: background-color 0.3s ease;
}

:deep(.custom-button:hover) {
  background-color: #f5f5f5 !important;
}

:deep(.custom-button span) {
  display: flex;
  align-items: center;
  justify-content: center;
  color: #000000 !important;
}

@media (prefers-color-scheme: dark) {
  :deep(.custom-button) {
    background-color: #383838 !important;
    border-color: #ccc !important;
  }
  :deep(.custom-button span) {
    color: #ccc !important;
  }
}
</style>
