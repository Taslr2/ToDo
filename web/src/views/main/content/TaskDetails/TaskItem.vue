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
      >
        <div class="item-title">{{ task.title }}</div>
        <div class="item-expectedCompletionDate">{{ task.expectedCompletionDate }}</div>
        <div class="item-status">{{ task.isDeleted ? '已删除' : task.isCompleted ? '已完成' : '未完成' }}</div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, inject, computed } from 'vue'

const tasks = inject('tasks');
const filter = ref('all');

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
  margin-right: 10px;
  padding: 10px 15px;
  border-radius: 5px;
  border: 2px solid transparent;
  transition: all 0.3s;
  cursor: pointer;
  background-color: #ffffff;
  color: #333;
  font-family: 'Arial', sans-serif;
  font-size: 16px;
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
  transition: all 0.3s;
}

.completed {
  background: linear-gradient(135deg, #ec958f, #e17785);
}

.completed:hover {
  box-shadow: #ec958f 0px 0px 15px 0px;
}

.deleted {
  background: linear-gradient(135deg, #867ef5, #e388e8);
}

.deleted:hover {
  box-shadow: #867ef5 0px 0px 15px 0px;
}

.active {
  background: linear-gradient(135deg, #64b3dd, #82eda3);
}

.active:hover {
  box-shadow: #64b3dd 0px 0px 15px 0px;
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
</style>