<script setup>
import { ref } from 'vue'

const searchQuery = ref('')
const isEditing = ref(false)
const isSearchVisable = ref(false)
const isCancelVisable = ref(false)
const isSearchAllowed = ref(true)
const isCancelAllowed = ref(true)
const inputRef = ref(null)

const startEditing = () => {
  isEditing.value = true
  isSearchVisable.value = false
  setTimeout(() => {
    inputRef.value.focus()
  }, 0)
}

const stopEditing = () => {
  isEditing.value = false
  searchQuery.value = ''
  isCancelVisable.value = false
}

const searchContent = () => {
  if (searchQuery.value === '') {
    isEditing.value = true
  } else {
    console.log('Search query:', searchQuery.value) // 搜索功能
  }
}

const makeSearchVisable = () => {
  if (isSearchAllowed.value == true) {
    setTimeout(() => {
      isSearchVisable.value = true
    }, 200)
    isSearchAllowed.value = false
  }
}
const makeSearchInvisable = () => {
  isSearchVisable.value = false
  isSearchAllowed.value = true
}
const makeCancelVisable = () => {
  if (isCancelAllowed.value == true) {
    setTimeout(() => {
      isCancelVisable.value = true
    }, 200)
    isCancelAllowed.value = false
  }
}
const makeCancelInvisable = () => {
  isCancelVisable.value = false
  isCancelAllowed.value = true
}
</script>

<template>
  <div class="search-box">
    <el-input
      v-if="isEditing"
      class="init"
      type="string"
      v-model="searchQuery"
      @keyup.enter="searchContent"
      placeholder="搜索"
      ref="inputRef"
    >
      <template #prefix>
        <span
          class="input-prefix"
          @click="searchContent"
          @mouseover="makeSearchVisable"
          @mouseleave="makeSearchInvisable"
        >
          <i class="iconfont icon-sousuo"></i>
        </span>
      </template>
      <template #suffix>
        <span
          class="input-suffix"
          @click="stopEditing"
          @mouseover="makeCancelVisable"
          @mouseleave="makeCancelInvisable"
        >
          <i class="iconfont icon-fork"></i>
        </span>
      </template>
    </el-input>
    <div
      v-else
      class="init-button"
      @click="startEditing"
      @mouseover="makeSearchVisable"
      @mouseleave="makeSearchInvisable"
    >
      <i class="iconfont icon-sousuo" style="margin-left: 10px"></i>
    </div>
    <div id="tooltip15" v-show="isSearchVisable">搜索</div>
    <div id="tooltip16" v-show="isCancelVisable">退出搜索</div>
  </div>
</template>

<style scoped>
.search-box {
  width: 375px;
  position: relative;
}
.init {
  width: 100%;
  font-size: 14px;
}
::v-deep .el-input__wrapper {
  background-color: #fff;
  border-radius: 5px;
  height: 33.33px;
  padding: 0;
}
::v-deep .el-input__inner {
  height: 100%;
}
.init-button {
  width: 100%;
  height: 33.33px;
  font-size: 14px;
  border-radius: 5px;
  background-color: #fff;
  border: 1px solid #dcdfe6;
  display: flex;
  align-items: center;
  cursor: pointer;
}
.init-button:hover {
  background-color: #f5f5f5;
}
.input-prefix,
.input-suffix {
  width: 40px;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
}
.input-prefix i {
  transform: translate(1px, 1px);
}
#tooltip15 {
  position: absolute;
  left: -50px;
  top: 2.5px;
  width: 40px;
  height: 30px;
  margin: -1px;
  padding: 0px;
  border: 0px;
  background-color: #fff;
  font-size: 12px;
  font-family: Microsoft YaHei;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 2px;
  animation: fadeIn 0.5s;
  color: #333;
}
#tooltip15::after {
  content: '';
  position: absolute;
  right: -25px;
  top: 50%;
  transform: translateY(-50%);
  border-width: 15px;
  border-style: solid;
  border-color: transparent transparent transparent #fff;
}
#tooltip16 {
  position: absolute;
  right: -13px;
  bottom: -40px;
  width: 70px;
  height: 30px;
  margin: -1px;
  padding: 0px;
  border: 0px;
  background-color: #fff;
  font-size: 12px;
  font-family: Microsoft YaHei;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 3px;
  animation: fadeIn 0.5s;
  color: #333;
  box-shadow: 2px 2px 4px 2px rgba(0, 0, 0, 0.15);
}
#tooltip16::before {
  content: '';
  position: absolute;
  top: -10px;
  left: 50%;
  transform: translateX(-50%);
  border-width: 0 10px 10px 10px;
  border-style: solid;
  border-color: transparent transparent #fff transparent;
}
</style>
