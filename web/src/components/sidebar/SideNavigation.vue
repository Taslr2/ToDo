<script setup></script>

<template>
  <div class="total">1</div>
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
  background: linear-gradient(to right, #eee, #faf9f8); /* 从左到右的渐变 */
}
</style>
<!-- #e9e8e7 ,-->
