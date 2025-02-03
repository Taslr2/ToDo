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
          component: () => import('@/views/main/content/MyDay.vue'),
        },
        {
          path: 'inplan',
          name: 'InPlan',
          component: () => import('@/views/main/content/InPlan.vue'),
        },
        {
          path: 'alltasks',
          name: 'AllTasks',
          component: () => import('@/views/main/content/AllTasks.vue'),
        },
      ],
    },
  ],
})

export default router
