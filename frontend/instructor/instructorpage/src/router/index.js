import { createRouter, createWebHistory } from 'vue-router'
import Login from '@/components/Login'
import Registration from '@/components/Registration'
import Home from '@/components/Home'

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
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {
  if (!store.isAuthenticated && to.name !== 'Login' && to.name !== 'Registration') {
    next({ name: 'Login' });
  } else {
    next();
  }
});

export default router