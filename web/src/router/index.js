import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('@/views/main/HomePage.vue'),
      children: [
        {
          path: '',
          name: 'CalendarView',
          component: () => import('@/views/main/content/CalendarView.vue'),
        },
        {
          path: 'fourquadrant',
          name: 'FourQuadrant',
          component: () => import('@/views/main/content/FourQuadrant.vue'),
        },
        {
          path: 'taskdetails',
          name: 'TaskDetails',
          component: () => import('@/views/main/content/TaskDetails.vue'),
        },
        {
          path: 'statisticalanalysis',
          name: 'StatisticalAnalysis',
          component: () =>
            import('@/views/main/content/StatisticalAnalysis/StatisticalAnalysis.vue'),
        },
      ],
    },
  ],
})

export default router
