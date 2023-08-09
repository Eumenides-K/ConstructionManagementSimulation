import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import VueParticles from 'vue-particles'
import router from './router'
import axios from 'axios'
import App from './App.vue'

axios.defaults.baseURL = 'http://localhost:8088'

const app = createApp(App)

app.config.globalProperties.$axios = axios

app.use(ElementPlus)
app.use(VueParticles)
app.use(router)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

app.mount('#app')
