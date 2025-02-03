<script setup>
const toggleMenu = () => {
  console.log('缩略功能未完成')
}
</script>

<template>
  <div class="total">
    <div class="toggle" @click="toggleMenu">
      <img
        class="menu-icon"
        src="@/assets/icons/menu-icon.svg"
        alt="Menu Icon"
        width="20"
        height="20"
      />
    </div>
    <ul class="nav">
      <li><router-link to="/">我的一天</router-link></li>
      <li><router-link to="/inplan">计划内</router-link></li>
      <li><router-link to="/alltasks">任务</router-link></li>
    </ul>
  </div>
</template>

<style scoped>
.total {
  width: 290px;
  height: 100%;
  display: flex;
  flex-direction: column;
  background-color: #fff;
  position: relative;
}

.total::after {
  content: '';
  position: absolute;
  top: 0;
  right: 0;
  width: 3px;
  height: 100%;
  background: linear-gradient(to right, #eee, #faf9f8);
}

.toggle {
  display: flex;
  width: 100%;
  height: 48px;
  box-sizing: border-box;
  margin: 16px 0px 0px;
  padding: 0px 24px;
  align-items: center;
}
.menu-icon {
  cursor: pointer;
}
</style>
