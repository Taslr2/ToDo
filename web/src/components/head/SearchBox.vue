<script setup>
import { ref } from 'vue'
import axios from 'axios'

const searchQuery = ref('')
const isEditing = ref(false)
const isSearchVisable = ref(false)
const isCancelVisable = ref(false)
const inputRef = ref(null)
const taskList = ref([])

const startEditing = () => {
  isEditing.value = true
  isSearchVisable.value = false
  setTimeout(() => {
    inputRef.value.focus()
  }, 0)
  console.log(isEditing.value)
}

const stopEditing = () => {
  isEditing.value = false
  searchQuery.value = ''
  isCancelVisable.value = false
}

const searchContent = () => {
  if (searchQuery.value === '') {
    isEditing.value = true
    taskList.value = []
  } else {
    axios
      .get(`http://localhost:8080/todo/search?keyword=${searchQuery.value}`)
      .then((response) => {
        taskList.value = response.data
      })
      .catch((error) => console.log(error))
  }
}

const makeSearchVisable = () => {
  isSearchVisable.value = true
}

const buttonMakeSearchVisable = () => {
  isSearchVisable.value = true
}

const makeSearchInvisable = () => {
  isSearchVisable.value = false
}
const makeCancelVisable = () => {
  isCancelVisable.value = true
}
const makeCancelInvisable = () => {
  isCancelVisable.value = false
}
const closeEditing = () => {
  isEditing.value = false
  searchQuery.value = ''
}

const emit = defineEmits(['showCalendar'])

const handleResultClick = (task) => {
  emit('showCalendar',task.expectedCompletionDate,task.title)
  console.log('SearchBox成功发送'+ task.expectedCompletionDate+ task.title)
  isEditing.value = false
}

defineExpose({
  closeEditing,
})
</script>

<template>
  <div class="search-box">
    <el-input
      v-if="isEditing"
      class="init"
      type="string"
      v-model="searchQuery"
      @keyup="searchContent"
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
      @mouseover="buttonMakeSearchVisable"
      @mouseleave="makeSearchInvisable"
    >
      <i class="iconfont icon-sousuo" style="margin-left: 10px"></i>
    </div>
    <div id="tooltip15" v-show="isSearchVisable">搜索</div>
    <div id="tooltip16" v-show="isCancelVisable">退出搜索</div>


    <div class="search-results" v-if="isEditing && taskList.length > 0">
      <div  v-for="task in taskList" :key="task.id" class="search-result-item" @click="handleResultClick(task)">
        {{ task.title }}
      </div>
    </div>
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
  z-index: 2;
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
  animation: fadeIn 0.5s ease 0.3s;
  color: #333;
  opacity: 0;
  animation-fill-mode: forwards;
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
  z-index: 1;
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
  animation: fadeIn 0.5s ease 0.3s;
  color: #333;
  opacity: 0;
  animation-fill-mode: forwards;
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
.search-results {

  position: absolute;
  top: 40px;
  right: 0;
  width: calc(100% - 45px);
  max-height: 200px;
  overflow-y: none;
  background-color: #fff;
  border: 1px solid #dcdfe6;
  border-radius: 5px;
  margin-top: 5px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  z-index: 999;
}
.search-result-item {
  display: flex;
  flex-direction:column ;
  width: 100%;
  height: 40px;
  padding: 0 0 0 15px;
  border-radius: 5px;
  justify-content: center;
  cursor: pointer;
  font-size: 14px;
  font-family: Microsoft YaHei;
}
.search-result-item:hover {
background-color: rgb(223, 223, 223);
}
.search-result-item:last-child {
  border-bottom: none;
}
</style>
