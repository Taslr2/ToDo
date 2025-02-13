<script setup>
const closeIsNew = () => {
  console.log('hello')
}
const menuItems = [
  { imgSrc: '', mainText: '现在，你可以在设置中更改为深色模式主题!', buttonText: '试用' },
  {
    imgSrc: '',
    mainText: '现在，可以通过拖动窗格边缘来调整详细信息窗格的大小!',
    buttonText: '明白了',
  },
  {
    imgSrc: '',
    mainText: '现在可以按类别对任务进行分组。在“排序”按钮旁边可以找到新的“分组依据”选项!',
    buttonText: '明白了',
  },
]
</script>
<template>
  <transition>
    <div class="total" v-show="isNewVisible">
      <div class="title">
        <h3>新增功能</h3>
        <el-button @click="closeIsNew" class="cancel" color="#faf9f8">
          <img :src="rightCancel" />
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
  overflow: scroll;
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
.cance .cancel:hover {
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

:deep(.custom-button) {
  width: 67.33px !important;
  height: 33.33px !important;
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
  font-size: 14px;
  color: #292827;
}
</style>
