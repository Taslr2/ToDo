<script setup>
import HeadNavigation from '@/components/head/HeadNavigation.vue'
import SideNavigation from '@/components/sidebar/SideNavigation.vue'
import SettingWindow from '@/components/RightPopUpWindows/SettingWindow.vue'
import HelpWindow from '@/components/RightPopUpWindows/HelpWindow.vue'
import NewFutures from '@/components/RightPopUpWindows/NewFutures.vue'
import PersonalInformation from '@/components/RightPopUpWindows/PersonalInformation.vue'
import { ref, provide, onMounted, onBeforeUnmount } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import axios from 'axios'

const isSidebarVisible = ref(true)
const headNavigationRef = ref(null)
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
  console.log('成功接收到更新侧边栏可见性的消息')
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

const taskList = ref([])

axios
  .get('http://localhost:8080/todo/showTodos')
  .then((response) => {
    console.log(response.data)
    taskList.value = response.data
  })
  .catch((error) => console.log(error))

provide('tasks', taskList)

const handleClickOutside = (event) => {
  if (event.target.closest('.head')) {
    return
  }

  if (headNavigationRef.value) {
    headNavigationRef.value.handleClickOutside()
  }
}

const showCalendar = (test) => {
  router.push({ name: 'CalendarView' })
  console.log(test)
}

onMounted(() => {
  document.addEventListener('click', handleClickOutside)
  if (route.path !== '/') {
    router.push('/')
  }
})

onBeforeUnmount(() => {
  document.removeEventListener('click', handleClickOutside)
})
</script>

<template>
  <div>
    <div class="head">
      <HeadNavigation
        ref="headNavigationRef"
        @showCalendar="showCalendar"
        @showSetting="makeSettingVisible"
        @showHelp="makeHelpVisible"
        @showNew="makeNewVisible"
        @showPersonal="makePersonalVisible"
        :isUpperRightVisible="isRightVisible"
      />
    </div>

    <div class="main-container">
      <transition name="leftside">
        <div class="leftcontent" v-if="isSidebarVisible">
          <SideNavigation
            @updateVisibility="handleUpdateVisibility"
            :isSidebarVisible="isSidebarVisible"
          />
        </div>
      </transition>
      <div class="content">
        <RouterView
          :is-sidebar-visible="isSidebarVisible"
          @makeSidebarVisible="convertSidebarVisible"
        />
      </div>
      <transition name="slide">
        <div class="right" v-show="isRightVisible">
          <transition-group name="characterslide">
            <SettingWindow
              v-show="isSettingVisible"
              @closeIsSetting="makeRightInvisible"
              key="setting"
            />

            <HelpWindow v-show="isHelpVisible" @closeIsHelp="makeRightInvisible" key="help" />

            <NewFutures v-show="isNewVisible" @closeIsNew="makeRightInvisible" key="new" />

            <PersonalInformation
              v-show="isPersonalVisible"
              @closeIsPersonal="makeRightInvisible"
              key="personal"
            />
          </transition-group>
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

.leftcontent {
  width: 290px;
  height: 100%;
}
.content {
  width: 100%;
}
.content-padding {
  z-index: 999;
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
.slide-leave-active,
.characterslide-enter-active {
  transition: transform 0.4s ease;
}

.slide-enter-from,
.slide-leave-to,
.characterslide-enter-from,
.characterslide-leave-to {
  transform: translateX(100%);
}
</style>
