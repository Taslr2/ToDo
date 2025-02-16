// import { ref, computed } from 'vue'
// import { defineStore } from 'pinia'

// export const useCounterStore = defineStore('counter', () => {
//   const count = ref(0)
//   const doubleCount = computed(() => count.value * 2)
//   function increment() {
//     count.value++
//   }

//   return { count, doubleCount, increment }
// })
import { ref } from 'vue'
import { defineStore } from 'pinia'

import calendarIcon from '@/assets/svg/calendar.svg'
import sunIcon from '@/assets/svg/sun.svg'
import houseIcon from '@/assets/svg/house.svg'
import bellIcon from '@/assets/svg/bell.svg'

const [isanalysisvisible, istaskvisible, iscalendarvisible, isfourquadrantvisible] = [
  ref(true),
  ref(true),
  ref(true),
  ref(true),
]
export const useSideStore = defineStore('counter', () => {
  const menuItems = [
    {
      text: '日历',
      iconClass: 'sun',
      iconSrc: calendarIcon,
      route: '/',
      isVisible: iscalendarvisible,
    },
    {
      text: '四象限',
      iconClass: 'calendar',
      iconSrc: sunIcon,
      route: '/fourquadrant',
      isVisible: isfourquadrantvisible,
    },
    {
      text: '任务详情',
      iconClass: 'house',
      iconSrc: houseIcon,
      route: '/taskdetails',
      isVisible: istaskvisible,
    },
    {
      text: '统计分析',
      iconClass: 'house',
      iconSrc: bellIcon,
      route: '/statisticalanalysis',
      isVisible: isanalysisvisible,
    },
  ]
  return { menuItems, isanalysisvisible, istaskvisible, isfourquadrantvisible }
})
