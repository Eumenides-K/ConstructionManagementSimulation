import { createRouter, createWebHistory } from 'vue-router'

import Login from '@/components/Login.vue'
import Registration from '@/components/Registration.vue'
import Home from '@/components/Home.vue'
import CreateSeminar from '@/components/CreateSeminar.vue'
import Group from '@/components/Group.vue'
import Result from '@/components/Result.vue'

import { store } from '@/store.js'


const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/registration',
    name: 'Registration',
    component: Registration
  },
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/createseminar',
    name: 'CreateSeminar',
    component: CreateSeminar
  },
  {
    path: '/group',
    name: 'Group',
    component: Group
  },
  {
    path: '/result',
    name: 'Result',
    component: Result
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {
  if (!store.isAuthenticated && to.name !== 'Login' && to.name !== 'Registration') {
    next({ name: 'Login' })
  } else {
    next()
  }
});

export default router