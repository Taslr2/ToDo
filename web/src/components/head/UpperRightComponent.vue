<script setup>
import { ref } from 'vue'

const activeBox = ref('')

const handleMouseDown = (box) => {
  activeBox.value = box
}

const handleMouseUp = () => {
  activeBox.value = ''
}

const gearFunction = () => {
  console.log('gearFunction')
}
const questionMarkFunction = () => {
  console.log('questionMarkFunction')
}
const paperPlaneFunction = () => {
  console.log('paperPlaneFunction')
}
const personalCenterFunction = () => {
  console.log('personalCenterFunction')
}
</script>

<template>
  <div class="whole">
    <div
      :class="['gear', { active: activeBox === 'gear' }]"
      @mousedown="handleMouseDown('gear')"
      @mouseup="handleMouseUp"
      @click="gearFunction"
    >
      <img src="@/assets/svg/gear.svg" alt="" />
    </div>
    <div
      :class="['questionMark', { active: activeBox === 'questionMark' }]"
      @mousedown="handleMouseDown('questionMark')"
      @mouseup="handleMouseUp"
      @click="questionMarkFunction"
    >
      <img src="@/assets/svg/question-mark.svg" alt="" />
    </div>
    <div
      :class="['paperPlane', { active: activeBox === 'paperPlane' }]"
      @mousedown="handleMouseDown('paperPlane')"
      @mouseup="handleMouseUp"
      @click="paperPlaneFunction"
    >
      <img src="@/assets/svg/paper-plane.svg" alt="" />
    </div>
    <div
      :class="['personalCenter', { active: activeBox === 'personalCenter' }]"
      @mousedown="handleMouseDown('personalCenter')"
      @mouseup="handleMouseUp"
      @click="personalCenterFunction"
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
