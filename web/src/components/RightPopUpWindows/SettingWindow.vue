<script setup>
import { ref, defineProps, defineEmits } from 'vue'
import rightCancel from '@/assets/svg/rightcancel.svg'
import { useSideStore } from '@/stores/sidebar'
import { watch } from 'vue'

const sideStore = useSideStore()

const [value1, value2, value3, value4, value5, value6, value11, value12] = [
  ref(true),
  ref(true),
  ref(false),
  ref(true),
  ref(true),
  ref(true),
  ref(false),
  ref(false),
]

// 添加 isDarkMode 响应式变量
const isDarkMode = ref(false)

// 监听 isDarkMode 变化，切换 dark-mode 类
watch(isDarkMode, (newVal) => {
  if (newVal) {
    document.documentElement.classList.add('dark-mode')
  } else {
    document.documentElement.classList.remove('dark-mode')
  }
})

const props = defineProps(['isSettingVisible'])
const emit = defineEmits(['closeIsSetting'])
const isSettingVisible = props.isSettingVisible

const closeIsSetting = () => {
  emit('closeIsSetting', false)
}
</script>
<template>
  <transition>
    <div class="total" v-show="isSettingVisible">
      <div class="title">
        <h3>设置</h3>
        <el-button @click="closeIsSetting" class="cancel" color="#faf9f8"
          ><img :src="rightCancel" />
        </el-button>
      </div>
      <div class="content">
        <div class="subtitle">常规</div>
        <div class="choice">
          在删除前确认
          <div class="button">
            <el-switch
              v-model="value1"
              style="--el-switch-on-color: #2564cf; --el-switch-off-color: #ccc"
            />
            <div class="buttontext">{{ value1 ? '开启' : '关闭' }}</div>
          </div>
        </div>
        <div class="choice">
          在顶部添加新任务
          <div class="button">
            <el-switch
              v-model="value2"
              style="--el-switch-on-color: #2564cf; --el-switch-off-color: #ccc"
            />
            <div class="buttontext">{{ value2 ? '开启' : '关闭' }}</div>
          </div>
        </div>
        <div class="choice">
          将带有星标的任务移至顶部
          <div class="button">
            <el-switch
              v-model="value3"
              style="--el-switch-on-color: #2564cf; --el-switch-off-color: #ccc"
            />
            <div class="buttontext">{{ value3 ? '开启' : '关闭' }}</div>
          </div>
        </div>
        <div class="choice">
          播放完成提示音
          <div class="button">
            <el-switch
              v-model="value4"
              style="--el-switch-on-color: #2564cf; --el-switch-off-color: #ccc"
            />
            <div class="buttontext">{{ value4 ? '开启' : '关闭' }}</div>
          </div>
        </div>
        <div class="choice">
          显示右键单击菜单
          <div class="button">
            <el-switch
              v-model="value5"
              style="--el-switch-on-color: #2564cf; --el-switch-off-color: #ccc"
            />
            <div class="buttontext">{{ value5 ? '开启' : '关闭' }}</div>
          </div>
        </div>
        <div class="choice">
          打开提醒通知
          <div class="button">
            <el-switch
              v-model="value6"
              style="--el-switch-on-color: #2564cf; --el-switch-off-color: #ccc"
            />
            <div class="buttontext">{{ value6 ? '开启' : '关闭' }}</div>
          </div>
        </div>
        <div class="choice">
          打开夜间模式
          <div class="button">
            <el-switch
              v-model="isDarkMode"
              style="--el-switch-on-color: #2564cf; --el-switch-off-color: #ccc"
            />
            <div class="buttontext">{{ isDarkMode ? '开启' : '关闭' }}</div>
          </div>
        </div>
        <div class="subtitle">智能列表</div>
        <div class="choice">
          四象限
          <div class="button">
            <el-switch
              v-model="sideStore.menuItems[1].isVisible"
              style="--el-switch-on-color: #2564cf; --el-switch-off-color: #ccc"
            />
            <div class="buttontext">{{ sideStore.menuItems[1].isVisible ? '开启' : '关闭' }}</div>
          </div>
        </div>
        <div class="choice">
          任务详情
          <div class="button">
            <el-switch
              v-model="sideStore.menuItems[2].isVisible"
              style="--el-switch-on-color: #2564cf; --el-switch-off-color: #ccc"
            />
            <div class="buttontext">{{ sideStore.menuItems[2].isVisible ? '开启' : '关闭' }}</div>
          </div>
        </div>
        <div class="choice">
          统计分析
          <div class="button">
            <el-switch
              v-model="sideStore.menuItems[3].isVisible"
              style="--el-switch-on-color: #2564cf; --el-switch-off-color: #ccc"
            />
            <div class="buttontext">{{ sideStore.menuItems[3].isVisible ? '开启' : '关闭' }}</div>
          </div>
        </div>
        <div class="choice">
          已分配给我
          <div class="button">
            <el-switch
              v-model="value11"
              style="--el-switch-on-color: #2564cf; --el-switch-off-color: #ccc"
            />
            <div class="buttontext">{{ value11 ? '开启' : '关闭' }}</div>
          </div>
        </div>
        <div class="choice">
          自动隐藏空的智能列表
          <div class="button">
            <el-switch
              v-model="value12"
              style="--el-switch-on-color: #2564cf; --el-switch-off-color: #ccc"
            />
            <div class="buttontext">{{ value12 ? '开启' : '关闭' }}</div>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>
<style scoped>
.total {
  width: 350px;
  height: 100%;
  background-color: #faf9f8;
  overflow-y: scroll;
  overflow-x: hidden;
  scrollbar-width: thin;
  scrollbar-color: #8f8f8f transparent;
}

.dark-mode .total {
  background-color: #141414 !important;
}
.title {
  width: 100%;
  height: 65px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 10px;
}
.title h3 {
  font-size: 20px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  color: #252423;
  margin: 0 0 0 6px;
  font-weight: 700;
}
.cancel:hover {
  background-color: #cccbca;
}
.cancel:active {
  border: 2px solid #101010;
}
.content {
  width: 350px;
  height: 100%;
  padding: 0 16px;
}
.subtitle {
  width: 318px;
  height: 42px;
  font-size: 18px;
  font-family: microsoft yahei;
  color: #292827;
  padding: 9px 0;
  font-weight: 700;
  margin-bottom: 5px;
}
.choice {
  width: 318px;
  height: 64.47px;
  font-size: 14px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  display: flex;
  flex-direction: column;
  justify-content: center;
  font-weight: 700;
  color: #292827;
  letter-spacing: 0.03em;
}
.button {
  display: flex;
  align-items: center;
}
.buttontext {
  margin: 0 8px;
  font-size: 14px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  color: #292827;
  font-weight: 500;
  letter-spacing: 0.01em;
}
</style>
``` ▋
