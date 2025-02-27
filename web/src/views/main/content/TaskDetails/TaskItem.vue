<template>
  <div class="todo-app">
    <div class="task-information">
      <div class="filter-options">
        <label> <input type="radio" v-model="filter" value="all" /> 全部 </label>
        <label> <input type="radio" v-model="filter" value="completed" /> 已完成 </label>
        <label> <input type="radio" v-model="filter" value="active" /> 未完成 </label>
        <label> <input type="radio" v-model="filter" value="deleted" /> 已删除 </label>
      </div>
      <div
        class="task-items"
        v-for="(task, index) in filteredTasks"
        :key="index"
        :class="{
          completed: task.isCompleted && !task.isDeleted,
          deleted: task.isDeleted,
          active: !task.isCompleted && !task.isDeleted,
        }"
        @click="showDetailsModal(index)"
      >
        <div class="item-title">{{ task.title }}</div>
        <div class="item-expectedCompletionDate">{{ task.expectedCompletionDate }}</div>
        <div class="item-status">
          {{ task.isDeleted ? '已删除' : task.isCompleted ? '已完成' : '未完成' }}
        </div>
      </div>
    </div>

    <!-- 弹出框 -->
    <div class="modal" v-if="showModal || modalStore.whetherShowModal" @click.stop>
      <div class="modal-content">
        <span class="close-button" @click="closeModal">&times;</span>
        <h2>{{ currentTask.title }}</h2>
        <p>{{ currentTask.details }}</p>
        <p>类别: {{ currentTask.category }}</p>
        <p>
          状态:
          {{ currentTask.isCompleted ? '已完成' : currentTask.isDeleted ? '已删除' : '待完成' }}
        </p>
        <p>预计完成时间: {{ currentTask.expectedCompletionDate }}</p>
        <p>实际完成时间: {{ currentTask.completionDate }}</p>
      </div>
      <div class="modal-info">按下Esc键可关闭详情内容</div>
    </div>
  </div>
</template>

<script setup>
import { ref, inject, computed, onMounted, onUnmounted, watch } from 'vue'
import { useRoute } from 'vue-router'
import { useModalStore } from '@/stores/modal'

const modalStore = useModalStore()

// 导入 defineEmits
const emit = defineEmits(['isBlur'])

const tasks = inject('tasks')
const filter = ref('all')
const showModal = ref(false)
const currentTask = ref(null)

watch(
  () => modalStore.whetherShowModal,
  (newVal) => {
    if (newVal == true) {
      filteredTasks.value.forEach((task, index) => {
        // console.log(`Task ${index}:`)
        // console.log(`Title: ${task.title}`)
        if (task.title === modalStore.modalTitle) {
          currentTask.value = filteredTasks.value[index]
          return
        }
      })
    }
  },
)
const sortedTasks = computed(() => {
  return tasks.value
    .slice()
    .sort((a, b) => new Date(a.expectedCompletionDate) - new Date(b.expectedCompletionDate))
})

const filteredTasks = computed(() => {
  return sortedTasks.value.filter((task) => {
    if (filter.value === 'all') return true
    if (filter.value === 'active') return !task.isCompleted && !task.isDeleted
    if (filter.value === 'completed') return task.isCompleted && !task.isDeleted
    if (filter.value === 'deleted') return task.isDeleted
    return true
  })
})

// 监听路由参数
const route = useRoute()
filter.value = route.query.filter || 'all'

const showDetailsModal = (index) => {
  currentTask.value = filteredTasks.value[index]
  showModal.value = true
  emit('isBlur', true) // 触发事件
  // console.log(
  //   'filteredTasks.value[index] ' +
  //     filteredTasks.value[index].title +
  //     ' id:' +
  //     filteredTasks.value[index].id +
  //     ' index:' +
  //     index,
  // )
}

const closeModal = () => {
  showModal.value = false
  modalStore.whetherShowModal = false
  emit('isBlur', false) // 关闭时触发事件
}

// 监听键盘事件
const handleKeyDown = (event) => {
  if (event.key === 'Escape') {
    closeModal()
  }
}

onMounted(() => {
  window.addEventListener('keydown', handleKeyDown)
})

onUnmounted(() => {
  window.removeEventListener('keydown', handleKeyDown)
})
</script>

<style scoped>
.todo-app,
.task-information {
  line-height: 1.1;
  padding: 20px;
}

.filter-options {
  margin-bottom: 20px;
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

.filter-options label {
  width: 17%;
  margin-right: 10px;
  padding: 10px 15px;
  border-radius: 5px;
  border: 2px solid transparent;
  transition: all 0.3s;
  cursor: pointer;
  background-color: #ffffff;
  color: #333;
  font-family: 'Arial', sans-serif;
  display: flex;
  align-items: center;
  justify-content: center;
}

.filter-options input[type='radio'] {
  margin-right: 5px;
  position: relative;
  top: 1px;
}

.filter-options input[type='radio']:checked + label {
  background-color: #007bff;
  color: white;
  border-color: #007bff;
  box-shadow: 0 4px 8px rgba(0, 123, 255, 0.3);
}

.filter-options label:hover {
  background-color: #e0e0e0;
  border-color: #007bff;
}

.task-items {
  margin: 10px 0;
  padding: 20px;
  border-radius: 10px;
  color: white;
  transition: all 0.5s;
}

.completed {
  background: #5d55f7;
}

.completed:hover {
  box-shadow: #5d55f7 0px 0px 15px 0px;
}

.deleted {
  background: #938dff;
}

.deleted:hover {
  box-shadow: #938dff 0px 0px 15px 0px;
}

.active {
  background: #0000b2;
}

.active:hover {
  box-shadow: #0000b2 0px 0px 15px 0px;
}

.item-title {
  font-size: 20px;
  font-weight: bold;
}

.item-expectedCompletionDate {
  margin: 5px 0;
}

.item-status {
  font-size: 12px;
}

.modal {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  backdrop-filter: blur(5px);
  z-index: 1000;
}

.modal-content {
  background: linear-gradient(135deg, #ffffff, #f5f5f5);
  padding: 20px;
  border-radius: 15px;
  position: relative;
  width: 80%;
  max-width: 450px;
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
  transition: all 0.3s ease;
  z-index: 1001;
}

.modal-info {
  padding: 10px 20px;
  border-radius: 15px;
  color: #c7c7c7;
  margin-top: 0px;
  z-index: 1002;
}

.modal-content h2 {
  font-size: 24px;
  color: #333;
  margin-bottom: 20px;
  font-weight: bold;
}

.modal-content p {
  font-size: 16px;
  color: #666;
  line-height: 1.1;
  margin-bottom: 10px;
}

.close-button {
  position: absolute;
  top: 10px;
  right: 10px;
  font-size: 24px;
  cursor: pointer;
  color: #888;
  transition: color 0.3s;
  z-index: 1003;
}

.close-button:hover {
  color: #ff4d4f;
}
</style>
