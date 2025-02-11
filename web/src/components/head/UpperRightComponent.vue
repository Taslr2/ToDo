<script setup>
import { ref, defineEmits } from 'vue'
const [isRightVisible, isSettingVisible, isHelpVisible, isNewVisible, isPersonalVisible] = [
  ref(false),
  ref(false),
  ref(false),
  ref(false),
  ref(false),
]
const activeBox = ref('')

const emit = defineEmits(['showSetting', 'showHelp', 'showNew', 'showPersonal'])
const handleMouseDown = (box) => {
  activeBox.value = box
}

const handleMouseUp = () => {
  activeBox.value = ''
}

const makeSettingVisible = () => {
  isRightVisible.value = true
  isSettingVisible.value = true
  emit('showSetting', isRightVisible.value, isSettingVisible.value)
}
const makeHelpVisible = () => {
  isRightVisible.value = true
  isHelpVisible.value = true
  emit('showHelp', isRightVisible.value, isHelpVisible.value)
}
const makeNewVisible = () => {
  isRightVisible.value = true
  isNewVisible.value = true
  emit('showNew', isRightVisible.value, isNewVisible.value)
}
const makePersonalVisible = () => {
  isRightVisible.value = true
  isPersonalVisible.value = true
  emit('showPersonal', isRightVisible.value, isPersonalVisible.value)
}
</script>

<template>
  <div class="whole">
    <div
      :class="['gear', { active: activeBox === 'gear' }]"
      @mousedown="handleMouseDown('gear')"
      @mouseup="handleMouseUp"
      @click="makeSettingVisible"
    >
      <img src="@/assets/svg/gear.svg" alt="" />
    </div>
    <div
      :class="['questionMark', { active: activeBox === 'questionMark' }]"
      @mousedown="handleMouseDown('questionMark')"
      @mouseup="handleMouseUp"
      @click="makeHelpVisible"
    >
      <img src="@/assets/svg/question-mark.svg" alt="" />
    </div>
    <div
      :class="['paperPlane', { active: activeBox === 'paperPlane' }]"
      @mousedown="handleMouseDown('paperPlane')"
      @mouseup="handleMouseUp"
      @click="makeNewVisible"
    >
      <img src="@/assets/svg/paper-plane.svg" alt="" />
    </div>
    <div
      :class="['personalCenter', { active: activeBox === 'personalCenter' }]"
      @mousedown="handleMouseDown('personalCenter')"
      @mouseup="handleMouseUp"
      @click="makePersonalVisible"
    >
      <img src="@/assets/svg/personal-center.svg" alt="" />
    </div>
  </div>
</template>

<style scoped>
.gear:hover,
.questionMark:hover,
.paperPlane:hover,
.personalCenter:hover {
  background-color: #005a9e;
  cursor: pointer;
}

.whole {
  width: 192px;
  height: 48px;
  display: flex;
  align-items: center;
}

.gear,
.questionMark,
.paperPlane,
.personalCenter {
  width: 48px;
  height: 48px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.gear.active,
.questionMark.active,
.paperPlane.active,
.personalCenter.active {
  background-color: #173f85;
  border: 0.5px solid white;
  box-sizing: border-box;
}
</style>
