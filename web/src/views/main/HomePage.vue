<script setup>
import HeadNavigation from '@/components/head/HeadNavigation.vue'
import SideNavigation from '@/components/sidebar/SideNavigation.vue'
import SettingWindow from '@/components/RightPopUpWindows/SettingWindow.vue'
import HelpWindow from '@/components/RightPopUpWindows/HelpWindow.vue'
import NewFutures from '@/components/RightPopUpWindows/NewFutures.vue'
import PersonalInformation from '@/components/RightPopUpWindows/PersonalInformation.vue'
import { ref, provide, onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { tasks as taskList } from '@/api/tasks.js' // 引入任务数组

const isSidebarVisible = ref(true)
const [isRightVisible, isSettingVisible, isHelpVisible, isNewVisible, isPersonalVisible] = [
  ref(false),
  ref(false),
  ref(false),
  ref(false),
  ref(false),
]
const router = useRouter()
const route = useRoute()

const handleUpdateVisibility = (visibility) => {
  isSidebarVisible.value = visibility
}

const convertSidebarVisible = (visibility) => {
  isSidebarVisible.value = visibility
}

const makeSettingVisible = (right, setting) => {
  isHelpVisible.value = isNewVisible.value = isPersonalVisible.value = false
  setTimeout(() => {
    isRightVisible.value = right
    isSettingVisible.value = setting
  }, 100)
}
const makeHelpVisible = (right, help) => {
  isSettingVisible.value = isNewVisible.value = isPersonalVisible.value = false
  setTimeout(() => {
    isRightVisible.value = right
    isHelpVisible.value = help
  }, 100)
}
const makeNewVisible = (right, newFutures) => {
  isSettingVisible.value = isHelpVisible.value = isPersonalVisible.value = false
  setTimeout(() => {
    isRightVisible.value = right
    isNewVisible.value = newFutures
  }, 100)
}
const makePersonalVisible = (right, personal) => {
  isSettingVisible.value = isHelpVisible.value = isNewVisible.value = false
  setTimeout(() => {
    isRightVisible.value = right
    isPersonalVisible.value = personal
  }, 100)
}
const makeRightInvisible = (invisbility) => {
  isRightVisible.value = invisbility
}
const tasks = ref(taskList)

provide('tasks', tasks)

onMounted(() => {
  if (route.path !== '/') {
    router.push('/')
  }
})
</script>

<template>
  <div>
    <div class="head">
      <HeadNavigation
        @showSetting="makeSettingVisible"
        @showHelp="makeHelpVisible"
        @showNew="makeNewVisible"
        @showPersonal="makePersonalVisible"
      />
    </div>
    <div class="main-container">
      <SideNavigation
        @updateVisibility="handleUpdateVisibility"
        :isSidebarVisible="isSidebarVisible"
      />
      <div class="content">
        <RouterView
          :is-sidebar-visible="isSidebarVisible"
          @makeSidebarVisible="convertSidebarVisible"
        />
      </div>
      <transition name="slide">
        <div class="right" v-show="isRightVisible">
          <SettingWindow v-show="isSettingVisible" @closeIsSetting="makeRightInvisible" />
          <HelpWindow v-show="isHelpVisible" @closeIsHelp="makeRightInvisible" />
          <NewFutures v-show="isNewVisible" @closeIsNew="makeRightInvisible" />
          <PersonalInformation v-show="isPersonalVisible" @closeIsPersonal="makeRightInvisible" />
        </div>
      </transition>
    </div>
  </div>
</template>

<style scoped>
.main-container {
  display: flex;
  height: calc(100vh - 48px);
  overflow: hidden;
  position: relative;
}

.sidebar {
  width: 290px;
  background-color: #fff;
}

.content {
  width: 100%;
  transition: width 0.5s ease;
}

.right {
  position: absolute;
  top: 0;
  right: 0;
  width: 350px;
  height: 100%;
  background-color: #faf9f8;
  z-index: 2;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.slide-enter-active,
.slide-leave-active {
  transition: transform 0.5s ease;
}

.slide-enter-from,
.slide-leave-to {
  transform: translateX(100%);
}
</style>
