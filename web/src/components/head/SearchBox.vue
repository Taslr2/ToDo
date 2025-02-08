<script setup>
import { ref } from 'vue'

const searchQuery = ref('')
const handleSearch = () => {
  console.log('Search query:', searchQuery.value)
}
</script>

<template>
  <div class="search-box">
    <el-input
      class="init"
      type="string"
      v-model="searchQuery"
      @keyup.enter="handleSearch"
      placeholder="搜索"
    >
      <template #prefix>
        <i class="iconfont icon-sousuo"></i>
      </template>
      <template #suffix>
        <i class="iconfont icon-fork" @click="searchQuery = ''"></i>
      </template>
    </el-input>
  </div>
</template>

<style scoped>
.search-box {
  width: 100%;
  max-width: 400px;
}
.init {
  width: 100%;
  padding: 0 12px;
  border-radius: 4px;
  font-size: 14px;
}

</style>


