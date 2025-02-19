<script setup>
import UpperRightComponent from '@/components/head/UpperRightComponent.vue'
import SearchBox from '@/components/head/SearchBox.vue'
import { ref, defineEmits, defineProps, onMounted, onBeforeUnmount } from 'vue'

const [isRightVisible, isSettingVisible, isHelpVisible, isNewVisible, isPersonalVisible] = [
  ref(''),
  ref(''),
  ref(''),
  ref(''),
  ref(''),
]

const activeBox = ref('')
const searchBoxRef = ref(null)

const refreshPage = () => {
  window.location.reload()
}
const handleMouseDown = (box) => {
  activeBox.value = box
}

const emit = defineEmits(['showSetting', 'showHelp', 'showNew', 'showPersonal', 'showCalendar'])
const props = defineProps({
  isUpperRightVisible: {
    type: Boolean,
    required: true,
  },
})

const makeSettingVisible = (right, setting) => {
  isRightVisible.value = right
  isSettingVisible.value = setting
  emit('showSetting', isRightVisible.value, isSettingVisible.value)
}
const makeHelpVisible = (right, help) => {
  isRightVisible.value = right
  isHelpVisible.value = help
  emit('showHelp', isRightVisible.value, isHelpVisible.value)
}
const makeNewVisible = (right, new_) => {
  isRightVisible.value = right
  isNewVisible.value = new_
  emit('showNew', isRightVisible.value, isNewVisible.value)
}
const makePersonalVisible = (right, personal) => {
  isRightVisible.value = right
  isPersonalVisible.value = personal
  emit('showPersonal', isRightVisible.value, isPersonalVisible.value)
}
const handleClickOutside = (event) => {
  if (!event) return

  const target = event?.target
  if (!target) return

  if (target.closest('.init-button')) {
    return
  }

  if (searchBoxRef.value) {
    if (!searchBoxRef.value.$el.contains(target) && !target.closest('.right')) {
      searchBoxRef.value.closeEditing()
    }
  }
}

const handleShowCalendar = (test) => {
  emit('showCalendar',test)
  console.log("HeadNavigation成功发送:"+test)
}


onMounted(() => {
  document.addEventListener('click', handleClickOutside)
})

onBeforeUnmount(() => {
  document.removeEventListener('click', handleClickOutside)
})

// 暴露方法
defineExpose({
  handleClickOutside,
})
</script>

<template>
  <div class="total">
    <div :class="['icon', { active: activeBox === 'icon' }]" @mousedown="handleMouseDown('icon')">
      <img src="@/assets/svg/sign.svg" alt="" @click="refreshPage" />
    </div>
    <div class="title" @click="refreshPage">To Do</div>
    <div class="search">
      <SearchBox ref="searchBoxRef" @showCalendar="handleShowCalendar" />
    </div>
    <div class="right">
      <UpperRightComponent
        @showSetting="makeSettingVisible"
        @showHelp="makeHelpVisible"
        @showNew="makeNewVisible"
        @showPersonal="makePersonalVisible"
        :isUpperRightVisible="props.isUpperRightVisible"
      />
    </div>
  </div>
</template>

<style scoped>
.total {
  display: flex;
  align-items: center;
  height: 48px;
  width: 100%;
  background-color: rgb(37, 100, 207);
  position: relative;
}
.icon {
  width: 48px;
  height: 48px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
}
.icon:hover {
  background-color: #005a9e;
  cursor: pointer;
}
.title {
  font-size: 16px;
  color: white;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 48px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  margin: 0 10px;
  font-weight: 500;
}
.title:hover {
  cursor: pointer;
  text-decoration: underline;
}
.search {
  flex: 1;
  display: flex;
  justify-content: center;
}
.right {
  width: 192px;
  height: 48px;
}
.icon.active {
  background-color: #173f85;
  border: 0.5px solid white;
  box-sizing: border-box;
}
</style>
