<template>
  <div class="todo-app">
    <div class="task-information">
      <div class="filter-options">
        <label>
          <input type="radio" v-model="filter" value="all" /> 全部
        </label>
        <label>
          <input type="radio" v-model="filter" value="active" /> 未完成
        </label>
        <label>
          <input type="radio" v-model="filter" value="completed" /> 已完成
        </label>
        <label>
          <input type="radio" v-model="filter" value="deleted" /> 已删除
        </label>
      </div>
      <div
        class="task-items"
        v-for="(task, index) in filteredTasks"
        :key="index"
        :class="{ completed: task.isCompleted && !task.isDeleted, deleted: task.isDeleted, active: !task.isCompleted && !task.isDeleted }"
        @click="showDetailsModal(index)"
      >
        <div class="item-title">{{ task.title }}</div>
        <div class="item-expectedCompletionDate">{{ task.expectedCompletionDate }}</div>
        <div class="item-status">{{ task.isDeleted ? '已删除' : task.isCompleted ? '已完成' : '未完成' }}</div>
      </div>
    </div>

    <!-- 弹出框背景 -->
    <div class="modal-overlay" v-if="showModal" @click="closeModal"></div>

    <!-- 弹出框 -->
    <div class="modal" v-if="showModal" @click.stop>
      <div class="modal-content">
        <span class="close-button" @click="closeModal">&times;</span>
        <h2>{{ currentTask.title }}</h2>
        <p>{{ currentTask.details }}</p>
        <p>Category: {{ currentTask.category }}</p>
        <p>Status: {{ currentTask.isCompleted ? 'Completed' : currentTask.isDeleted ? 'Deleted' : 'Active' }}</p>
        <p>Expected Completion Date: {{ currentTask.expectedCompletionDate }}</p>
        <p>Real Completion Date: {{ currentTask.completionDate }}</p>
      </div>
      <div class="modal-info">按下Esc键可关闭详情内容</div>
    </div>
  </div>
</template>

<script setup>
import { ref, inject, computed, onMounted, onUnmounted } from 'vue';

const tasks = inject('tasks');
const filter = ref('all');
const showModal = ref(false);
const currentTask = ref(null);

const sortedTasks = computed(() => {
  return tasks.value.slice().sort((a, b) => new Date(a.expectedCompletionDate) - new Date(b.expectedCompletionDate));
});

const filteredTasks = computed(() => {
  return sortedTasks.value.filter(task => {
    if (filter.value === 'all') return true;
    if (filter.value === 'active') return !task.isCompleted && !task.isDeleted;
    if (filter.value === 'completed') return task.isCompleted && !task.isDeleted;
    if (filter.value === 'deleted') return task.isDeleted;
    return true;
  });
});

const emit = defineEmits(['isBlur']);

const showDetailsModal = (index) => {
  currentTask.value = filteredTasks.value[index];
  showModal.value = true;
  emit('isBlur', true);  // 触发事件，并传递参数
};

const closeModal = () => {
  showModal.value = false;
  emit('isBlur', false); // 关闭时触发事件，并传递参数
};

// 监听键盘事件
const handleKeyDown = (event) => {
  if (event.key === 'Escape') {
    closeModal();
  }
};

onMounted(() => {
  window.addEventListener('keydown', handleKeyDown);
});

onUnmounted(() => {
  window.removeEventListener('keydown', handleKeyDown);
});
</script>

<style scoped>
.todo-app, .task-information {
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
  /* font-size: 16px; */
  display: flex;
  align-items: center;
  justify-content: center;
}

.filter-options input[type="radio"] {
  margin-right: 5px;
  position: relative;
  top: 1px;
}

.filter-options input[type="radio"]:checked + label {
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
  /* background: linear-gradient(135deg, #ec958f, #e17785); */
  background: #d58dff;
}

.completed:hover {
  box-shadow: #d58dff 0px 0px 15px 0px;
}

.deleted {
  /* background: linear-gradient(135deg, #867ef5, #e388e8); */
  background: #9896f1;
}

.deleted:hover {
  box-shadow: #9896f1 0px 0px 15px 0px;
}

.active {
  /* background: linear-gradient(135deg, #64b3dd, #82eda3); */
  background: #edb1f1;
}

.active:hover {
  box-shadow: #edb1f1 0px 0px 15px 0px;
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
  background-color: rgba(0, 0, 0, 0.5); /* 添加半透明背景 */
  backdrop-filter: blur(5px); /* 添加模糊效果 */
  z-index: 1000; /* 确保 modal 显示在其他组件之上 */
}

.modal-content {
  background: linear-gradient(135deg, #ffffff, #f5f5f5); /* 渐变背景 */
  padding: 20px;
  border-radius: 15px; /* 增大圆角 */
  position: relative;
  width: 80%;
  max-width: 450px;
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2); /* 增加阴影 */
  transition: all 0.3s ease;
  z-index: 1001; /* 确保 modal 内容显示在其他组件之上 */
}

.modal-info {
  /* background-color: #f5f5f5; */
  padding: 10px 20px; /* 内边距 */
  border-radius: 15px; /* 圆角 */
  color: #c7c7c7; /* 字体颜色 */
  margin-top: 0px; /* 上边距 */
  z-index: 1002; /* 确保 modal 信息显示在其他组件之上 */
}

.modal-content h2 {
  font-size: 24px; /* 增加标题字体大小 */
  color: #333; /* 改变标题字体颜色 */
  margin-bottom: 20px; /* 增加标题和段落之间的间距 */
  font-weight: bold; /* 加粗标题 */
}

.modal-content p {
  font-size: 16px; /* 调整段落字体大小 */
  color: #666; /* 改变段落字体颜色 */
  line-height: 1.1; /* 增加行高，提高可读性 */
  margin-bottom: 10px; /* 增加段落之间的间距 */
}

.close-button {
  position: absolute;
  top: 10px;
  right: 10px;
  font-size: 24px;
  cursor: pointer;
  color: #888;
  transition: color 0.3s;
  z-index: 1003; /* 确保关闭按钮显示在其他组件之上 */
}

.close-button:hover {
  color: #ff4d4f; /* 鼠标悬停时的颜色变化 */
}
</style>