import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import '@/assets/css/reset.css'
import '@/assets/css/global.css'
import  'animate.css'
import '@/assets/iconfont/iconfont.css'

const app = createApp(App)

app.use(createPinia())
app.use(router)

app.mount('#app')
