<template>
  <div class="StatisticalAnalysis">
    <div class="StatisticalAnalysis-header">
      <StatisticalAnalysisHeader />
    </div>
    <div class="StatisticalAnalysis-chart">
      <div class="piechart">
        <PieChart />
      </div>
      <div class="linechart">
        <!-- <LineChart /> -->
      </div>
    </div>
  </div>
</template>

<script setup>
import { provide, ref, inject } from 'vue'
import StatisticalAnalysisHeader from './StatisticalAnalysis-header/StatisticalAnalysis-header.vue'
import PieChart from './PieChart.vue'
// import LineChart from './LineChart.vue'

const tasks = inject('tasks')

const allTasks = ref(tasks.value)
const completedTasks = ref(allTasks.value.filter(task => task.isCompleted))
const unfinishedTasks = ref(allTasks.value.filter(task => !task.isCompleted && !task.isDeleted))
const deleteTasks = ref(allTasks.value.filter(task => task.isDeleted))

provide('allTasks', allTasks)
provide('completedTasks', completedTasks)
provide('unfinishedTasks', unfinishedTasks)
provide('deleteTasks', deleteTasks)
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
}

.StatisticalAnalysis-chart {
  width: calc(100% - 20px);
  height: calc(65% - 20px);
  margin: 10px;
}

.piechart {
  width: calc(40% - 20px);
  height: 100%;
  margin: 10px;
  float: left;
}

.linechart {
  width: calc(60% - 20px);
  height: 100%;
  margin: 10px;
  float: left;
  background-color: lightblue;
}
</style>
