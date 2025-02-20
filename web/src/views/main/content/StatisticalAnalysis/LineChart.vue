<template>
  <div
    id="lineChart"
    ref="lineChart"
    style="width: 100%; height: 70%; position: relative; top: 100px"
  ></div>
</template>

<script setup>
import { ref, onMounted, computed, inject } from 'vue'
import * as echarts from 'echarts'

const lineChart = ref(null)

const allTasks = inject('allTasks')
const completedTasks = inject('completedTasks')

console.log('completedTasks222', completedTasks.value)

// 处理数据，统计每天完成的任务数量及其分类
const taskCountByTime = computed(() => {
  const completedTasksArray = completedTasks.value
  const dateCountMap = {}

  completedTasksArray.forEach((task) => {
    const date = new Date(task.expectedCompletionDate).toString() // 提取日期
    console.log('date = ', date)
    const category = task.category // 提取任务分类
    if (!dateCountMap[date]) {
      dateCountMap[date] = { Work: 0, Study: 0, Social: 0, Other: 0 }
    }
    dateCountMap[date][category] += 1
    console.log('dateCountMap[date] = ', dateCountMap[date])
  })

  console.log('dateCountMap = ', dateCountMap)

  const dates = Object.keys(dateCountMap)
    .map((date) => new Date(date))
    .sort((a, b) => a - b)

  const latest20Dates = dates.slice(-20).map((date) => date.toString())
  console.log('dates', dates)

  const workCounts = latest20Dates.map((date) => dateCountMap[date]['Work'] || 0)
  const studyCounts = latest20Dates.map((date) => dateCountMap[date]['Study'] || 0)
  const lifeCounts = latest20Dates.map((date) => dateCountMap[date]['Social'] || 0)
  const otherCounts = latest20Dates.map((date) => dateCountMap[date]['Other'] || 0)
  const totalCounts = latest20Dates.map(
    (date) =>
      (dateCountMap[date]['Work'] || 0) +
      (dateCountMap[date]['Study'] || 0) +
      (dateCountMap[date]['Social'] || 0) +
      (dateCountMap[date]['Other'] || 0)
  )

  return {
    times: latest20Dates,
    workCounts: workCounts,
    studyCounts: studyCounts,
    lifeCounts: lifeCounts,
    otherCounts: otherCounts,
    totalCounts: totalCounts,
  }
})

onMounted(() => {
  // 初始化 ECharts 实例
  const chartDomLine = document.getElementById('lineChart')
  const myChartLine = echarts.init(chartDomLine)

  // 配置折线图选项
  const optionLine = {
    tooltip: {
      trigger: 'axis',
    },
    legend: {
      data: ['工作', '学习', '生活', '其他', '总数'],
    },
    xAxis: {
      type: 'category',
      data: taskCountByTime.value.times, // 使用处理后的日期数据
      axisLabel: {
        formatter: function (value) {
          const date = new Date(value)
          const year = date.getFullYear()
          const month = String(date.getMonth() + 1).padStart(2, '0') // 月份从0开始，需加1
          const day = String(date.getDate()).padStart(2, '0')
          return `${year}-${month}-${day}` // 格式化为 yyyy-mm-dd
        },
      },
    },
    yAxis: {
      type: 'value',
      splitNumber: 8, // 调整 Y 轴刻度数量
    },
    grid: {
      top: '10%', // 调整图表上边距
      bottom: '10%', // 调整图表下边距
      left: '15%', // 调整图表左边距
      right: '15%', // 调整图表右边距
    },
    series: [
      {
        name: '工作',
        type: 'line',
        data: taskCountByTime.value.workCounts, // 使用处理后的任务数量数据
        smooth: true,
        areaStyle: {
          color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
            { offset: 0, color: 'rgba(0, 0, 128, 0.5)' }, // 起始颜色，半透明的蓝色
            { offset: 1, color: 'rgba(0, 0, 128, 0)' }, // 结束颜色，完全透明的蓝色
          ]),
        },
      },
      {
        name: '学习',
        type: 'line',
        data: taskCountByTime.value.studyCounts, // 使用处理后的任务数量数据
        smooth: true,
        areaStyle: {
          color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
            { offset: 0, color: 'rgba(0, 128, 0, 0.5)' }, // 起始颜色，半透明的绿色
            { offset: 1, color: 'rgba(0, 128, 0, 0)' }, // 结束颜色，完全透明的绿色
          ]),
        },
      },
      {
        name: '生活',
        type: 'line',
        data: taskCountByTime.value.lifeCounts, // 使用处理后的任务数量数据
        smooth: true,
        areaStyle: {
          color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
            { offset: 0, color: 'rgba(255, 165, 0, 0.5)' }, // 起始颜色，半透明的橙色
            { offset: 1, color: 'rgba(255, 165, 0, 0)' }, // 结束颜色，完全透明的橙色
          ]),
        },
      },
      {
        name: '其他',
        type: 'line',
        data: taskCountByTime.value.otherCounts, // 使用处理后的任务数量数据
        smooth: true,
        areaStyle: {
          color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
            { offset: 0, color: 'rgba(128, 0, 128, 0.5)' }, // 起始颜色，半透明的紫色
            { offset: 1, color: 'rgba(128, 0, 128, 0)' }, // 结束颜色，完全透明的紫色
          ]),
        },
      },
      {
        name: '总数',
        type: 'line',
        data: taskCountByTime.value.totalCounts, // 使用处理后的任务数量数据
        smooth: true,
        areaStyle: {
          color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
            { offset: 0, color: 'rgba(159, 191, 219, 0.5)' }, // 起始颜色，半透明的黄色
            { offset: 1, color: 'rgba(159, 191, 219, 0)' }, // 结束颜色，完全透明的黄色
          ]),
        },
      },
    ],
  }

  // 设置图表选项
  myChartLine.setOption(optionLine)
})
</script>

<style scoped>
#lineChart {
  width: 100%;
  height: 100%;
}
</style>
