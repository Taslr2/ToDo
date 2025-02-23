<template>
  <div id="pieChart" ref="pieChart" style="width: 100%; height: 100%"></div>
</template>

<script setup>
import { ref, onMounted, inject } from 'vue'
import * as echarts from 'echarts'

const pieChart = ref(null)

const allTasks = inject('allTasks')
const completedTasks = inject('completedTasks')
const unfinishedTasks = inject('unfinishedTasks')
const deleteTasks = inject('deleteTasks')

const allCount = ref(allTasks.value.length)
const completedCount = ref(completedTasks.value.length)
const unfinishedCount = ref(unfinishedTasks.value.length)
const deleteCount = ref(deleteTasks.value.length)

const WorkCount = ref(allTasks.value.filter((task) => task.category === 'Work').length)
const StudyCount = ref(allTasks.value.filter((task) => task.category === 'Study').length)
const LifeCount = ref(allTasks.value.filter((task) => task.category === 'Social').length)
const OtherCount = ref(allTasks.value.length - WorkCount.value - StudyCount.value - LifeCount.value)

onMounted(() => {
  // 初始化 ECharts 实例
  const chartDom = document.getElementById('pieChart')
  const myChart = echarts.init(chartDom)

  // 配置饼图选项
  const option = {
    tooltip: {
      trigger: 'item',
      formatter: '{a} <br/>{b}: {c} ({d}%)',
    },
    legend: {
      width: '55%',
      orient: 'horizontal',
      left: 'left',
      padding: 10,
      itemWidth: 25, // 每个图例项的宽度
      itemHeight: 14, // 每个图例项的高度
      itemGap: 20, // 图例项之间的间隔
      textStyle: {
        color: '#000', // 默认颜色
      },
    },
    series: [
      {
        name: '任务状态',
        type: 'pie',
        selectedMode: 'single',
        radius: [0, '30%'],
        center: ['50%', '55%'],
        label: {
          position: 'inner',
          fontSize: 14,
        },
        labelLine: {
          show: false,
        },
        data: [
          { value: completedCount.value, name: '已完成' },
          { value: unfinishedCount.value, name: '待完成' },
          { value: deleteCount.value, name: '已放弃' },
        ],
        formatter: function (name) {
          if (name === '已放弃') {
            return '{name|' + name + '}<br/>{icon|}'
          }
          return '{name|' + name + '}'
        },
        rich: {
          name: {
            width: 80, // 设置每个图例项的宽度
          },
          icon: {
            width: 0, // 使用零宽度来强制换行
            height: 0,
          },
        },
      },
      {
        name: '任务状态',
        type: 'pie',
        radius: ['45%', '60%'],
        center: ['50%', '55%'],
        labelLine: {
          length: 30,
        },
        label: {
        //   formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
          formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  ',
          backgroundColor: '#F6F8FC',
          borderColor: '#8C8D8E',
          borderWidth: 1,
          borderRadius: 4,
          rich: {
            a: {
              color: '#6E7079',
              lineHeight: 22,
              align: 'center',
            },
            hr: {
              borderColor: '#8C8D8E',
              width: '100%',
              borderWidth: 1,
              height: 0,
            },
            b: {
              color: '#4C5058',
              fontSize: 14,
              fontWeight: 'bold',
              lineHeight: 33,
            },
            per: {
              color: '#fff',
              backgroundColor: '#4C5058',
              padding: [3, 4],
              borderRadius: 4,
            },
          },
        },
        data: [
          { value: WorkCount.value, name: '工作' },
          { value: StudyCount.value, name: '学习' },
          { value: LifeCount.value, name: '生活' },
          { value: OtherCount.value, name: '其他' },
        ],
      },
    ],
  }

  // 设置图表选项
  myChart.setOption(option);
  console.log(allCount);

  // 监听夜间模式切换
  const updateChartDarkMode = () => {
    const isDarkMode = document.documentElement.classList.contains('dark-mode');
    myChart.setOption({
      legend: {
        textStyle: {
          color: isDarkMode ? '#ccc' : '#000',
        },
      },
    });
  };

  const observer = new MutationObserver(updateChartDarkMode);
  observer.observe(document.documentElement, { attributes: true, attributeFilter: ['class'] });

  updateChartDarkMode(); // 初始化时调用一次
})
</script>

<style scoped>
#pieChart {
  width: 100%;
  height: 100%;
}
</style>
