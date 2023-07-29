import { createRouter, createWebHistory } from 'vue-router'
import Login from '@/components/Login'
import Registration from '@/components/Registration'

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/registration',
    name: 'Registration',
    component: Registration
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router