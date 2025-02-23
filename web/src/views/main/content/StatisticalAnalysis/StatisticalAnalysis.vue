<template>
  <div class="StatisticalAnalysis">
    <div class="StatisticalAnalysis-header">
      <StatisticalAnalysisHeader
        :is-sidebar-visible="props.isSidebarVisible"
        @makeSidebarVisible="SidebarVisible"
      />
    </div>
    <div class="StatisticalAnalysis-chart">
      <div class="piechart">
        <PieChart />
      </div>
      <div class="linechart">
        <LineChart />
      </div>
      <!-- <div class="download">
        <a href="#">下载分析数据</a>
      </div> -->
    </div>
  </div>
</template>

<script setup>
import { provide, ref, inject, defineProps, defineEmits, computed } from 'vue'
import StatisticalAnalysisHeader from './StatisticalAnalysis-header/StatisticalAnalysis-header.vue'
import PieChart from './PieChart.vue'
import LineChart from './LineChart.vue'

const tasks = inject('tasks')

const allTasks = ref(tasks.value)
const completedTasks = computed(()=> allTasks.value.filter((task) => task.isCompleted))
const unfinishedTasks = computed(()=> allTasks.value.filter((task) => !task.isCompleted && !task.isDeleted))
const deleteTasks = computed(()=> allTasks.value.filter((task) => !task.isCompleted && task.isDeleted))

console.log('completedTasks111', completedTasks.value)

const props = defineProps(['isSidebarVisible'])
const emit = defineEmits(['makeSidebarVisible'])
const svgVisible = ref(true)
provide('allTasks', allTasks)
provide('completedTasks', completedTasks)
provide('unfinishedTasks', unfinishedTasks)
provide('deleteTasks', deleteTasks)

const SidebarVisible = (visibility) => {
  svgVisible.value = !visibility
  emit('makeSidebarVisible', svgVisible.value)
}
</script>

<style scoped>
.StatisticalAnalysis {
  width: 100%;
  height: 100%;
  background-color: #faf9f8;
}

.dark-mode .StatisticalAnalysis {
  background-color: #121212;
  color: #cccccc;
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
  /* background-color: lightblue; */
}

/* .download {
  position: relative;
  width: calc(60% - 20px);
  height: 40%;
  float: left;
}

.download a {
  position: absolute;
  top: 30%;
  left: 25%;
  transform: translate(-50%, -50%);
  width: 150px;
  height: 40px;
  text-align: center;
  line-height: 40px;
  color: #fff;
  font-size: 16px;
  text-decoration: none;
  font-family: sans-serif;
  box-sizing: border-box;
  background: linear-gradient(90deg, #03a9f4, #f441a5, #ffeb3b, #03a9f4);
  background-size: 400%;
  border-radius: 30px;
  z-index: 1;
}

.download a:hover {
  animation: animate 8s linear infinite;
}

.download a::before {
  content: '';
  position: absolute;
  inset: -5px;
  z-index: -1;
  background: linear-gradient(90deg, #03a9f4, #f441a5, #ffeb3b, #03a9f4);
  background-size: 400%;
  border-radius: 40px;
  opacity: 0;
  transition: all 0.3s ease-in-out;
}

.download a:hover::before {
  filter: blur(20px);
  opacity: 1;
  animation: animate 8s linear infinite;
}

@keyframes animate {
  from {
    background-position: 0%;
  }
  to {
    background-position: 400%;
  }
} */
</style>
