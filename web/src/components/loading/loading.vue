<template>
  <div v-if="isLoading" class="loading-overlay">
    <!-- <p>加载中...</p> -->
    <div class="loader">
      <span class="binary"></span>
      <span class="binary"></span>
      <span class="getting-there">LOADING MY STUFF...</span>
    </div>
  </div>
</template>
  
<script setup>
import { ref, onMounted, onUnmounted } from 'vue'

const isLoading = ref(false)

const showLoading = () => {
  isLoading.value = true
}
const hideLoading = () => {
  isLoading.value = false
}

// 提供给外部使用
defineExpose({ showLoading, hideLoading })
</script>
  
<style>
@import url('https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css');
@import url('https://fonts.googleapis.com/css?family=Lato&subset=latin');

.loading-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.7);
  color: white;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
  z-index: 1000;
}

.loader {
  width: 200px;
  height: 170px;
  position: relative;
}

.loader::before,
.loader::after {
  content: '';
  width: 0;
  height: 0;
  position: absolute;
  bottom: 100px;
  left: 5px;
  z-index: 1;
  border-left: 100px solid transparent;
  border-right: 100px solid transparent;
  border-bottom: 30px solid #1b2a33;
  transform: scale(0);
  transition: all 0.2s ease;
}

.loader::after {
  border-right: 15px solid transparent;
  border-bottom: 30px solid #162229;
}

.loader .getting-there {
  width: 120%;
  text-align: center;
  position: absolute;
  bottom: 50px;
  left: -5%;
  font-family: 'Lato';
  font-size: 20px;
  letter-spacing: 2px;
  color: white;
}

.loader .binary {
  width: 100%;
  height: 140px;
  display: block;
  color: white;
  position: absolute;
  top: -70px;
  left: 15px;
  z-index: 2;
  overflow: hidden;
}

.loader .binary::before,
.loader .binary::after {
  font-family: 'Lato';
  font-size: 24px;
  position: absolute;
  top: 0;
  left: 40px;
  opacity: 0;
}

.loader .binary:nth-child(1)::before {
  content: '0';
  -webkit-animation: a 1.1s linear infinite;
  animation: a 1.1s linear infinite;
}

.loader .binary:nth-child(1)::after {
  content: '1';
  -webkit-animation: b 1.3s linear infinite;
  animation: b 1.3s linear infinite;
}

.loader .binary:nth-child(2)::before {
  content: '3';
  -webkit-animation: c 0.9s linear infinite;
  animation: c 0.9s linear infinite;
}

.loader .binary:nth-child(2)::after {
  content: '4';
  -webkit-animation: d 0.7s linear infinite;
  animation: d 0.7s linear infinite;
}

.loader::before,
.loader::after {
  transform: scale(1);
}

@-webkit-keyframes a {
  0% {
    transform: translate(30px, 0) rotate(30deg);
    opacity: 0;
  }
  100% {
    transform: translate(30px, 150px) rotate(-50deg);
    opacity: 1;
  }
}

@keyframes a {
  0% {
    transform: translate(30px, 0) rotate(30deg);
    opacity: 0;
  }
  100% {
    transform: translate(30px, 150px) rotate(-50deg);
    opacity: 1;
  }
}

@-webkit-keyframes b {
  0% {
    transform: translate(50px, 0) rotate(-40deg);
    opacity: 0;
  }
  100% {
    transform: translate(40px, 150px) rotate(80deg);
    opacity: 1;
  }
}

@keyframes b {
  0% {
    transform: translate(50px, 0) rotate(-40deg);
    opacity: 0;
  }
  100% {
    transform: translate(40px, 150px) rotate(80deg);
    opacity: 1;
  }
}

@-webkit-keyframes c {
  0% {
    transform: translate(70px, 0) rotate(10deg);
    opacity: 0;
  }
  100% {
    transform: translate(60px, 150px) rotate(70deg);
    opacity: 1;
  }
}

@keyframes c {
  0% {
    transform: translate(70px, 0) rotate(10deg);
    opacity: 0;
  }
  100% {
    transform: translate(60px, 150px) rotate(70deg);
    opacity: 1;
  }
}

@-webkit-keyframes d {
  0% {
    transform: translate(30px, 0) rotate(-50deg);
    opacity: 0;
  }
  100% {
    transform: translate(45px, 150px) rotate(30deg);
    opacity: 1;
  }
}

@keyframes d {
  0% {
    transform: translate(30px, 0) rotate(-50deg);
    opacity: 0;
  }
  100% {
    transform: translate(45px, 150px) rotate(30deg);
    opacity: 1;
  }
}
</style>
  