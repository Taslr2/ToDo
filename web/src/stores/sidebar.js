import { defineStore } from 'pinia'
import calendarIcon from '@/assets/svg/calendar.svg'
import sunIcon from '@/assets/svg/sun.svg'
import houseIcon from '@/assets/svg/house.svg'
import bellIcon from '@/assets/svg/bell.svg'

export const useSideStore = defineStore('sidebar', {
  state: () => ({
    selectedIndex: 0, 
    isanalysisvisible: true,
    istaskvisible: true,
    iscalendarvisible: true,
    isfourquadrantvisible: true,
    menuItems: [
      {
        text: '日历',
        iconClass: 'sun',
        iconSrc: calendarIcon,
        route: '/',
        isVisible: true,
      },
      {
        text: '四象限',
        iconClass: 'calendar',
        iconSrc: sunIcon,
        route: '/fourquadrant',
        isVisible: true,
      },
      {
        text: '任务详情',
        iconClass: 'house',
        iconSrc: houseIcon,
        route: '/taskdetails',
        isVisible: true,
      },
      {
        text: '统计分析',
        iconClass: 'house',
        iconSrc: bellIcon,
        route: '/statisticalanalysis',
        isVisible: true,
      },
    ],
  }),
  actions: {
    initSelectedIndex(currentPath) {
      const index = this.menuItems.findIndex((item) => item.route === currentPath)
      if (index !== -1) {
        this.selectedIndex = index
      }
    },
  },
})
