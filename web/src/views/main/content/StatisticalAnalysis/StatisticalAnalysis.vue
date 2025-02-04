<template>
  <div class="StatisticalAnalysis">
    <div class="StatisticalAnalysis-header">
      <StatisticalAnalysisHeader
        :allTasks="allTasks"
        :allCount="allCount"
        :completedTasks="completedTasks"
        :completedCount="completedCount"
        :unfinishedTasks="unfinishedTasks"
        :unfinishedCount="unfinishedCount"
        :deleteTasks="deleteTasks"
        :deleteCount="deleteCount"
      />
    </div>
    <div class="StatisticalAnalysis-chart">
      <div class="piechart">
        <!-- <PieChart : -->
      </div>
      <div class="linechart"></div>
    </div>
  </div>
</template>

<script setup>
import { ref, inject } from 'vue'
import StatisticalAnalysisHeader from './StatisticalAnalysis-header/StatisticalAnalysis-header.vue'
import { PieChart } from '@/views/main/content/StatisticalAnalysis/PieChart.vue'

const tasks = inject('tasks')

const allTasks = tasks.value
console.log('所有任务:', allTasks)
const allCount = allTasks.length
console.log('所有任务数量:', allCount)

const completedTasks = tasks.value.filter((task) => task.isCompleted)
console.log('已完成的任务:', completedTasks)
const completedCount = completedTasks.length
console.log('已完成的任务数量:', completedCount)

const unfinishedTasks = tasks.value.filter((task) => !task.isCompleted && !task.isDeleted)
console.log('待完成的任务:', unfinishedTasks)
const unfinishedCount = unfinishedTasks.length
console.log('待完成的任务数量:', unfinishedCount)

const deleteTasks = tasks.value.filter((task) => task.isDeleted)
console.log('已放弃的任务:', deleteTasks)
const deleteCount = deleteTasks.length
console.log('已放弃的任务数量:', deleteCount)
</script>

<style scoped>
.StatisticalAnalysis {
  width: 100%;
  height: 100%;
  background-color: #f4f7fb;
}

.StatisticalAnalysis-header {
  width: calc(100% - 20px);
  height: calc(35% - 20px);
  margin: 10px;
  /* background-color: pink; */
}

.StatisticalAnalysis-chart {
  width: calc(100% - 20px);
  height: calc(65% - 20px);
  margin: 10px;
  /* background-color: lightblue */
}

.piechart {
  width: 40%;
  height: 100%;
  float: left;
  background-color: yellow;
}
</style>
