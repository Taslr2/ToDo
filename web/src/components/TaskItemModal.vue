<template>
  <div class="modal" v-if="showModalProp" @click.stop>
    <div class="modal-content">
      <span class="close-button" @click="closeModal">&times;</span>
      <h2>{{ task.title }}</h2>
      <p>{{ task.details }}</p>
      <p>类别: {{ task.category }}</p>
      <p>
        状态:
        {{ task.isCompleted ? '已完成' : task.isDeleted ? '已删除' : '待完成' }}
      </p>
      <p>预计完成时间: {{ task.expectedCompletionDate }}</p>
      <p>实际完成时间: {{ task.completionDate }}</p>
    </div>
    <div class="modal-info">按下Esc键可关闭详情内容</div>
  </div>
</template>

<script setup>
import { onMounted, onUnmounted, toRefs } from 'vue'
import { useModalStore } from '@/stores/modal'

const props = defineProps({
  task: {
    type: Object,
    required: true,
  },
  showModalProp: {
    type: Boolean,
    required: true,
  },
})

const { showModalProp } = toRefs(props)

const emit = defineEmits(['update:showModalProp', 'isBlur'])

const modalStore = useModalStore()

const closeModal = () => {
  modalStore.whetherShowModal = false;
  emit('update:showModalProp', false);
  emit('isBlur', false)
}

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
