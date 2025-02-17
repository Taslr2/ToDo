<script setup>
import { ref, defineEmits, defineProps, watch, inject } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useSideStore } from '@/stores/sidebar'

const sideStore = useSideStore()
const emit = defineEmits(['updateVisibility'])
const props = defineProps(['isSidebarVisible'])

const isContentVisible = ref(props.isSidebarVisible)
const route = useRoute()

watch(
  () => props.isSidebarVisible,
  (newVal) => {
    isContentVisible.value = newVal
  },
)

const toggleContent = () => {
  isContentVisible.value = !isContentVisible.value
  emit('updateVisibility', isContentVisible.value)
}

watch(
  () => route.path,
  (newPath) => {
    const index = sideStore.menuItems.findIndex((item) => item.route === newPath)
    if (index !== -1) {
      sideStore.selectedIndex = index // 移除 .value
    }
  },
)

// 传入当前路径初始化选中索引
sideStore.initSelectedIndex(route.path)

const showLoading = inject('showLoading')
const hideLoading = inject('hideLoading')

const router = useRouter()
const navigateWithDelay = async (path, index) => {
  // 改为接收 path 参数
  try {
    sideStore.selectedIndex = index
    showLoading()
    await new Promise((resolve) => setTimeout(resolve, 1000))
    await router.push({ path }) // 使用对象方式传递路由路径
    hideLoading()
  } catch (error) {
    console.error('Navigation error:', error)
    hideLoading()
  }
}
</script>

<template>
  <div class="all" v-if="isContentVisible">
    <div class="toggle">
      <img
        class="menu-icon"
        src="@/assets/svg/menu-icon.svg"
        alt="Menu Icon"
        width="20"
        height="20"
        @click="toggleContent"
      />
    </div>
    <div class="sidebar">
      <div class="total">
        <ul class="nav">
          <li
            v-for="(item, index) in sideStore.menuItems"
            v-show="item.isVisible"
            :key="index"
            :class="sideStore.selectedIndex === index ? 'selected' : 'unselected'"
            @click="navigateWithDelay(item.route, index)"
          >
            <img :src="item.iconSrc" width="20" height="20" />
            <span class="text">{{ item.text }}</span>
          </li>
        </ul>
        <div class="ul-last"></div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.sidebar {
  background-color: #fff;
  display: flex;
  flex-direction: column;
}

.all {
  width: 290px;
  display: flex;
  flex-direction: column;
  background-color: #fff;
  position: relative;
  height: calc(100vh - 48px);
}

.all::after {
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
  background-color: #fff;
}

.menu-icon {
  cursor: pointer;
}

.nav li {
  cursor: pointer;
  width: 100%;
  height: 44px;
  padding: 12px 24px;
  box-sizing: border-box;
  transition: transform 0.5s ease;
  position: relative;
}

.nav li img {
  display: inline-block;
  vertical-align: middle;
  margin-right: 16px;
}

.ul-last {
  width: 258px;
  height: 1px;
  margin: 9px 16px;
  background-color: #e1dfdd;
  transform: scaleY(1.1);
}

.text {
  display: inline-block;
  vertical-align: middle;
  color: #333;
}

.unselected {
  font-size: 14px;
  font-family: Microsoft YaHei;
}

.unselected:hover {
  background-color: #f5f5f5;
}

.selected {
  font-size: 14px;
  background-color: #eff6fc;
  color: #000;
  font-weight: 900;
}

.selected::before {
  content: '';
  position: absolute;
  left: 0;
  top: 0;
  width: 2px;
  height: 100%;
  background-color: #2564cf;
  animation: zoomIn 0.5s;
}
</style>
