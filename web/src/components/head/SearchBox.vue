<script setup>
import { ref } from 'vue'

const searchQuery = ref('')
const isSearchBoxAvailable = ref(false)
const isEditing = ref(false)

const handleSearch = () => {
  console.log('Search query:', searchQuery.value)
}

const startEditing = () => {
  isEditing.value = true
}

const stopEditing = () => {
  isEditing.value = false
  searchQuery.value = ''
}

const handleMouseDown = () => {
  if (searchQuery.value === '') {
    isSearchBoxAvailable.value = false
  }
}

const handleMouseUp = () => {
  if (searchQuery.value === '') {
    isSearchBoxAvailable.value = true
  } else {
    console.log('Search query:', searchQuery.value) // 搜索功能
  }
}
</script>

<template>
  <div class="search-box">
    <el-input
      v-if="isEditing"
      class="init"
      type="string"
      v-model="searchQuery"
      @keyup.enter="handleSearch"
      placeholder="搜索"
      :disabled="isSearchBoxAvailable"
    >
      <template #prefix>
        <i class="iconfont icon-sousuo"
           @mousedown="handleMouseDown"
           @mouseup="handleMouseUp"></i>
      </template>
      <template #suffix>
        <i class="iconfont icon-fork" @click="stopEditing"></i>
      </template>
    </el-input>
    <div v-else class="init-button" @click="startEditing">
      <i class="iconfont icon-sousuo"></i>
    </div>
  </div>
</template>

<style scoped>
.search-box {
  width: 375px;
}
.init {
  width: 100%;
  font-size: 14px;
}
.el-input__wrapper {
  background-color: #fff;
  border-radius: 5px;
}

.init-button {
  width: 100%;
  height: 33.33px;
  padding: 0 10px;
  font-size: 14px;
  border-radius: 5px;
  background-color: #fff;
  border: 1px solid #dcdfe6;
  display: flex;
  align-items: center;
  cursor: pointer;
}
.init-button:hover {
  background-color: #f5f7fa;
}
::v-deep .el-input__prefix,
::v-deep .el-input__suffix {
  width: 30px;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
}
</style>
