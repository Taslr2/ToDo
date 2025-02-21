<script setup>
import { ref, defineEmits, defineProps, watch } from 'vue'
const [isRightVisible, isSettingVisible, isHelpVisible, isNewVisible, isPersonalVisible] = [
  ref(false),
  ref(false),
  ref(false),
  ref(false),
  ref(false),
]

const activeBox = ref('')

const [settingBgColor, helpBgColor, newBgColor, personalBgColor] = [
  ref(''),
  ref(''),
  ref(''),
  ref(''),
]

const emit = defineEmits(['showSetting', 'showHelp', 'showNew', 'showPersonal'])
const props = defineProps({
  isUpperRightVisible: {
    type: Boolean,
    required: true,
  },
})

watch(
  () => props.isUpperRightVisible,
  (newValue) => {
    if (newValue === false) {
      settingBgColor.value = helpBgColor.value = newBgColor.value = personalBgColor.value = ''
    }
  },
)
const handleMouseDown = (box) => {
  activeBox.value = box
}

const handleMouseUp = () => {
  activeBox.value = ''
}

const makeSettingVisible = () => {
  if( isRightVisible.value === true &&
    isSettingVisible.value === true){
    isRightVisible.value = false
    isSettingVisible.value = false
  }else{
  isRightVisible.value = true
  isSettingVisible.value = true
}
  isHelpVisible.value = isPersonalVisible.value = isNewVisible.value = false
  helpBgColor.value = newBgColor.value = personalBgColor.value = ''
  emit('showSetting', isRightVisible.value, isSettingVisible.value)
}
const makeHelpVisible = () => {
  if (isRightVisible.value === true && isHelpVisible.value === true) {
    isRightVisible.value = false
    isHelpVisible.value = false
  } else {
    isRightVisible.value = true
    isHelpVisible.value = true
  }
  isSettingVisible.value = isPersonalVisible.value = isNewVisible.value = false
  settingBgColor.value = newBgColor.value = personalBgColor.value = ''
  emit('showHelp', isRightVisible.value, isHelpVisible.value)
}

const makeNewVisible = () => {
  if (isRightVisible.value === true && isNewVisible.value === true) {
    isRightVisible.value = false
    isNewVisible.value = false
  } else {
    isRightVisible.value = true
    isNewVisible.value = true
  }
  isSettingVisible.value = isHelpVisible.value = isPersonalVisible.value = false
  settingBgColor.value = helpBgColor.value = personalBgColor.value = ''
  emit('showNew', isRightVisible.value, isNewVisible.value)
}

const makePersonalVisible = () => {
  if (isRightVisible.value === true && isPersonalVisible.value === true) {
    isRightVisible.value = false
    isPersonalVisible.value = false
  } else {
    isRightVisible.value = true
    isPersonalVisible.value = true
  }
  isSettingVisible.value = isHelpVisible.value = isNewVisible.value = false
  settingBgColor.value = helpBgColor.value = newBgColor.value = ''
  emit('showPersonal', isRightVisible.value, isPersonalVisible.value)
}


const handleMouseLeave = (type) => {
  switch (type) {
    case 'setting':
      settingBgColor.value = isSettingVisible.value ? '#f3f2f1' : ''
      break
    case 'help':
      helpBgColor.value = isHelpVisible.value ? '#f3f2f1' : ''
      break
    case 'new':
      newBgColor.value = isNewVisible.value ? '#f3f2f1' : ''
      break
    case 'personal':
      personalBgColor.value = isPersonalVisible.value ? '#f3f2f1' : ''
      break
  }
}
</script>

<template>
  <div class="whole">
    <div
      :class="['gear', { active: activeBox === 'gear' }]"
      @mousedown="handleMouseDown('gear')"
      @mouseup="handleMouseUp"
      @click="makeSettingVisible"
      @mouseleave="handleMouseLeave('setting')"
      :style="{ backgroundColor: settingBgColor }"
    >
      <img
        src="@/assets/svg/gear.svg"
        :class="{ 'colored-icon': settingBgColor === '#f3f2f1' }"
        alt=""
      />
    </div>
    <div
      :class="['questionMark', { active: activeBox === 'questionMark' }]"
      @mousedown="handleMouseDown('questionMark')"
      @mouseup="handleMouseUp"
      @click="makeHelpVisible"
      @mouseleave="handleMouseLeave('help')"
      :style="{ backgroundColor: helpBgColor }"
    >
      <img
        src="@/assets/svg/question-mark.svg"
        :class="{ 'colored-icon': helpBgColor === '#f3f2f1' }"
        alt=""
      />
    </div>
    <div
      :class="['paperPlane', { active: activeBox === 'paperPlane' }]"
      @mousedown="handleMouseDown('paperPlane')"
      @mouseup="handleMouseUp"
      @click="makeNewVisible"
      @mouseleave="handleMouseLeave('new')"
      :style="{ backgroundColor: newBgColor }"
    >
      <img
        src="@/assets/svg/paper-plane.svg"
        :class="{ 'colored-icon': newBgColor === '#f3f2f1' }"
        alt=""
      />
    </div>
    <div
      :class="['personalCenter', { active: activeBox === 'personalCenter' }]"
      @mousedown="handleMouseDown('personalCenter')"
      @mouseup="handleMouseUp"
      @click="makePersonalVisible"
      @mouseleave="handleMouseLeave('personal')"
      :style="{ backgroundColor: personalBgColor }"
    >
      <img
        src="@/assets/svg/personal-center.svg"
        :class="{ 'colored-icon': personalBgColor === '#f3f2f1' }"
        alt=""
      />
    </div>
  </div>
</template>

<style scoped>
.gear:hover,
.questionMark:hover,
.paperPlane:hover,
.personalCenter:hover {
  background-color: #005a9e !important;
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
  transition: background-color 0.3s ease;
}

.gear.active,
.questionMark.active,
.paperPlane.active,
.personalCenter.active {
  background-color: #173f85;
  border: 0.5px solid white;
  box-sizing: border-box;
}

.gear:hover,
.questionMark:hover,
.paperPlane:hover,
.personalCenter:hover img {
  filter: none !important;
}

.colored-icon {
  filter: brightness(0) saturate(100%) invert(14%) sepia(32%) saturate(1066%) hue-rotate(206deg)
    brightness(97%) contrast(95%);
}
</style>
