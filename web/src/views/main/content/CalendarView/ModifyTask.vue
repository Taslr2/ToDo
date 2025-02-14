<template>
  <transition name="fade" mode="out-in">
    <div v-if="currentSelectedTask" :key="currentSelectedTask.title" class="task-modify-container">
      <h2>{{ currentSelectedTask.title }}</h2>
      <div v-if="!isEditing" class="task-info-container">
        <div class="task-info">
          <p><strong>详情：</strong>{{ currentSelectedTask.details }}</p>
          <p><strong>类别：</strong>{{ currentSelectedTask.category }}</p>
          <p>
            <strong>预计完成日期：</strong
            >{{ formattedDate(currentSelectedTask.expectedCompletionDate) }}
          </p>
          <p><strong>是否完成：</strong>{{ currentSelectedTask.isCompleted ? '是' : '否' }}</p>
          <p>
            <strong>完成日期：</strong
            >{{
              currentSelectedTask.completionDate
                ? formattedDate(currentSelectedTask.completionDate)
                : '未完成'
            }}
          </p>
          <p><strong>已删除：</strong>{{ currentSelectedTask.isDeleted ? '是' : '否' }}</p>
          <div class="action-buttons">
            <button class="delete-btn" @click="deleteTask">删除</button>
            <button class="edit-btn" @click="startEditing">编辑</button>
          </div>
        </div>

        <div class="Canlendar-Connector-left"></div>
        <div class="Canlendar-Connector-right"></div>
      </div>

      <div v-if="isEditing" class="task-edit-form">
        <label for="title">标题：</label>
        <input id="title" v-model="currentSelectedTask.title" type="text" class="input-field" />
        <label for="details">详情：</label>
        <textarea
          id="details"
          v-model="currentSelectedTask.details"
          class="textarea-field"
        ></textarea>
        <label for="category">类别：</label>
        <input
          id="category"
          v-model="currentSelectedTask.category"
          type="text"
          class="input-field"
        />
        <label for="expectedCompletionDate">预计完成日期：</label>
        <input
          id="expectedCompletionDate"
          v-model="currentSelectedTask.expectedCompletionDate"
          type="date"
          class="input-field"
        />
        <div class="checkbox-group">
          <input
            id="isCompleted"
            v-model="currentSelectedTask.isCompleted"
            type="checkbox"
            class="checkbox"
          />
          <label for="isCompleted">是否完成：</label>
        </div>
        <label for="completionDate">完成日期：</label>
        <input
          id="completionDate"
          v-model="currentSelectedTask.completionDate"
          type="date"
          class="input-field"
        />
        <div class="buttons">
          <button class="save-btn" @click="saveTask">保存</button>
          <button class="cancel-btn" @click="cancelEditing">取消</button>
        </div>
      </div>
    </div>
  </transition>
</template>
  
  <script setup>
import { defineProps, watch, ref, defineEmits } from 'vue'
import moment from 'moment'

const props = defineProps({
  selectedTask: Object,
  year: Number,
  month: Number,
  day: Number,
})

const emit = defineEmits(['taskDeleted'])

const currentSelectedTask = ref(props.selectedTask || null)
const isEditing = ref(false)

const formattedDate = (date) => {
  return date ? moment(date).format('YYYY-MM-DD') : ''
}

const startEditing = () => {
  isEditing.value = true
}

const saveTask = () => {
  console.log('保存任务:', currentSelectedTask.value)
  isEditing.value = false
  // 这里可以添加实际的保存逻辑，比如调用API更新任务信息

  // 保存成功的提示框
  alert('任务保存成功！')
}

const cancelEditing = () => {
  console.log('取消编辑')
  isEditing.value = false
  // 恢复到原始任务信息
  currentSelectedTask.value = props.selectedTask
}

const deleteTask = () => {
  if (confirm('确定要删除此任务吗？')) {
    console.log('删除任务:', currentSelectedTask.value)
    currentSelectedTask.value.isDeleted = true
    // 删除成功的提示框
    alert('任务删除成功！')
  }
}

watch(
  () => props.selectedTask,
  (newTask) => {
    currentSelectedTask.value = newTask || null
    console.log('点击任务:', newTask)
  }
)

watch([() => props.year, () => props.month, () => props.day], () => {
  currentSelectedTask.value = null
  isEditing.value = false
})
</script>
  
  <style scoped>
.task-modify-container {
  height: 460px;
  padding: 22px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  overflow-y: scroll;
}

.task-modify-container::-webkit-scrollbar {
  display: none;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}

.task-modify-container h2 {
  font-size: 1.8em;
  color: #003366;
  background-color: white;
  height: 84px;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.task-info-container {
  height: 310px;
  background-color: white;
  border-radius: 12px;
  margin-top: 22px;
  padding: 15px;
}

.task-info p {
  margin-bottom: 8px;
  line-height: 1.6;
  color: #003366;
  margin-bottom: 15px;
}

.task-info strong {
  color: #4b0082;
}

.edit-btn,
.delete-btn {
  padding: 8px 16px;
  background-color: #847aea;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
  margin-right: 16px;
  float: right;
}

.edit-btn:hover {
  background-color: #0b76bf;
}

.delete-btn:hover {
  background-color: #f44336;
}

.task-edit-form label {
  margin: 4px;
  font-weight: bold;
  color: #4463c9;
  line-height: 30px;
}

.input-field,
.textarea-field {
  margin-bottom: 16px;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  width: 100%;
  box-sizing: border-box;
}

.checkbox-group {
  display: flex;
  align-items: center;
  margin-bottom: 16px;
}

.checkbox-group label {
  margin-left: 8px;
  color: #333;
}

.buttons {
  display: flex;
  justify-content: flex-end;
}

.save-btn,
.cancel-btn {
  padding: 8px 16px;
  margin-left: 16px;
  background-color: #2196f3;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.save-btn:hover {
  background-color: #0b76bf;
}

.cancel-btn:hover {
  background-color: #f44336;
}
</style>
  