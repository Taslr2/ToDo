<script setup>
import { ref } from 'vue'

const searchQuery = ref('')
const SearchMark = {
  template: '<img src="@/assets/svg/search-mark.svg" alt="Search Icon" class="icon" />'
}

const CancelSearch = {
  template: '<img src="@/assets/svg/cancel-search.svg" alt="Cancel Icon" class="icon" />'
}

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
        <SearchMark />
      </template>
      <template #suffix>
        <CancelSearch />
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
  padding: 8px 12px;
  border-radius: 4px;
  font-size: 14px;
}
</style>
