<template>
  <div class="void" id="void">
    <div class="crop">
      <ul class="card-list" style="--count: 6">
        <li>
          <div class="card task-card">
            <!-- <a href="#">
              <span>2020-2024</span>
              <span>Model for generating highly dimensional, mostly numeric, tabular data</span>
            </a> -->
            <TaskCard :index="2"/>
          </div>
        </li>
        <li>
          <div class="card">
            <a href="#">
              <img src="./03.gif" alt="" />
            </a>
          </div>
        </li>
        <li>
          <div class="card task-card">
            <!-- <a href="#">
              <span>2020-2024</span>
              <span>Model for generating highly dimensional, mostly numeric, tabular data</span>
            </a> -->
            <TaskCard :index="1"/>
          </div>
        </li>
        <li>
          <div class="card">
            <a href="#">
              <img src="./03.gif" alt="" />
            </a>
          </div>
        </li>
        <li>
          <div class="card task-card">
            <!-- <a href="#">
              <span>2020-2024</span>
              <span>Model for generating highly dimensional, mostly numeric, tabular data</span>
            </a> -->
            <TaskCard :index="0"/>
          </div>
        </li>
        <li>
          <div class="card">
            <a href="#">
              <img src="./03.gif" alt="" />
            </a>
          </div>
        </li>
      </ul>
      <div class="last-circle"></div>
      <div class="second-circle"></div>
    </div>
    <div class="mask"></div>
    <div class="center-circle"></div>
  </div>
</template>

<script setup>
import TaskCard from './TaskCard.vue'
</script>

<style scoped>
/* 设置容器样式 */
.void {
  width: 700px;
  margin: auto;
  position: absolute;
  left: 50%;
  top: 10%;
  aspect-ratio: 1 / 1;
  --rotate-speed: 40;
  --count: 8;
  --easing: cubic-bezier(0, 0.37, 1, 0.63);
}

/* 鼠标悬停时暂停动画 */
ul:hover * {
  animation-play-state: paused;
}

/* 设置列表样式 */
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  position: relative;
  width: 100%;
  aspect-ratio: 1 / 1;
  z-index: 1;
}

/* 设置列表项样式 */
li {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  width: 100%;
  animation: rotateCW calc(var(--rotate-speed) * 1s) var(--easing) infinite;
}

/* 设置卡片样式 */
.card {
  width: 32%;
  height: auto; /* 修改为自动高度，根据内容调整 */
  max-height: 16vh; /* 修改为视口高度的16%，使最大高度更具响应性 */
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 8px;
  gap: 8px;
  background: #ffffff;
  box-shadow: 0px 4px 12px rgba(70, 0, 255, 0.1), 0px 16px 32px rgba(165, 135, 255, 0.1);
  border-radius: 8px;
  font-family: Arial, sans-serif; /* 明确字体系列 */
  font-size: 14px; /* 字体大小 */
  font-weight: 400; /* 字体粗细 */
  color: #535062;
  animation: rotateCCW calc(var(--rotate-speed) * 1s) var(--easing) infinite;
}

.task-card::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0; /* 修改为bottom: 0; 使伪元素与卡片高度一致 */
  width: 5px; /* 边框宽度 */
  background: linear-gradient(to bottom, #9885c5, #7f639d); /* 渐变颜色 */
  border-top-left-radius: 12px; /* 添加圆角样式以匹配卡片的圆角 */
  border-bottom-left-radius: 12px; /* 添加圆角样式以匹配卡片的圆角 */
}

/* 设置图片样式 */
.card img {
  width: 100%;
}

/* 设置链接样式 */
a {
  text-decoration: none;
  color: unset;
  display: block;
  height: 80px;
  overflow: hidden;
}

/* 设置模型名称样式 */
.model-name {
  font-weight: 500;
  font-size: 18px;
  line-height: 150%;
  color: #3b2ed0;
  display: block;
}

/* 设置SVG样式 */
svg {
  position: absolute;
  top: 0;
  left: 0;
  z-index: 0;
}

/* 设置不同列表项的动画延迟 */
li:nth-child(2),
li:nth-child(2) .card {
  animation-delay: calc((var(--rotate-speed) / var(--count)) * -1s);
}

li:nth-child(3),
li:nth-child(3) .card {
  animation-delay: calc((var(--rotate-speed) / var(--count)) * -2s);
}

li:nth-child(4),
li:nth-child(4) .card {
  animation-delay: calc((var(--rotate-speed) / var(--count)) * -3s);
}

li:nth-child(5),
li:nth-child(5) .card {
  animation-delay: calc((var(--rotate-speed) / var(--count)) * -4s);
}

li:nth-child(6),
li:nth-child(6) .card {
  animation-delay: calc((var(--rotate-speed) / var(--count)) * -5s);
}

/* 定义顺时针旋转动画 */
@keyframes rotateCW {
  from {
    transform: translate3d(0px, -50%, -1px) rotate(-45deg);
  }

  to {
    transform: translate3d(0px, -50%, 0px) rotate(-315deg);
  }
}

/* 定义逆时针旋转动画 */
@keyframes rotateCCW {
  from {
    transform: rotate(45deg);
  }

  to {
    transform: rotate(315deg);
  }
}

/* 设置中心圆样式 */
.center-circle {
  position: absolute;
  width: 147px;
  aspect-ratio: 1 / 1;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  background: #ffffff;
  box-shadow: 0px 18px 36px -18px rgba(12, 5, 46, 0.3), 0px 30px 60px -12px rgba(12, 5, 46, 0.25);
  border-radius: 50%;
  background-size: cover;
  background-position: -35px 0;
}

/* 设置第二个圆样式 */
.second-circle {
  position: absolute;
  width: 296px;
  aspect-ratio: 1 / 1;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  background: #f5f4fe;
  opacity: 0.5;
  box-shadow: 0px 18px 36px -18px rgba(12, 5, 46, 0.3), 0px 30px 60px -12px rgba(12, 5, 46, 0.25);
  border-radius: 50%;
  background-image: url(./02.gif);
}

/* 设置最后一个圆样式 */
.last-circle {
  position: absolute;
  width: 550px;
  aspect-ratio: 1 / 1;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  background: #f5f4fe;
  opacity: 0.25;
  box-shadow: 0px 18px 36px -18px rgba(12, 5, 46, 0.3), 0px 30px 60px -12px rgba(12, 5, 46, 0.25);
  border-radius: 50%;
  background-image: url(./04.gif);
}

/* 设置裁剪样式 */
.crop {
  mask-image: linear-gradient(
    90deg,
    rgba(0, 0, 0, 0),
    rgba(0, 0, 0, 0) 50%,
    rgba(0, 0, 0, 1) 50%,
    rgba(0, 0, 0, 1)
  );
}

/* 设置遮罩样式 */
.mask {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  width: 50%;
  background-position: 100% 50%;
  background-repeat: no-repeat;
  background-image: radial-gradient(
    100% 50% at 100% 50%,
    rgba(60, 26, 229, 0.25) 0%,
    rgba(59, 26, 229, 0.241896) 20%,
    rgba(53, 26, 229, 0.1872) 40%,
    rgba(41, 23, 240, 0.104) 60%,
    rgba(34, 26, 229, 0.0184296) 90%,
    rgba(32, 26, 229, 0) 100%
  );
}

/* 设置遮罩样式后的效果 */
.mask:after {
  content: '';
  position: absolute;
  width: 1px;
  height: 100%;
  right: 0;
  display: block;
  background-image: linear-gradient(
    180deg,
    rgba(60, 26, 229, 0) 0%,
    #3c1ae5 50%,
    rgba(60, 26, 229, 0) 100%
  );
}
</style>