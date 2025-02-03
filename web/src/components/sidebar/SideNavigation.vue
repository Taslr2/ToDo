<script setup></script>

<template>
  <div class="total">
    <ul class="nav">
      <li><router-link to="/">我的一天</router-link></li>
      <li><router-link to="/inplan">计划内</router-link></li>
      <li><router-link to="/alltasks">任务</router-link></li>
    </ul>
  </div>
</template>

<style scoped>
.total {
  width: 292px;
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
</style>
