import { createRouter, createWebHistory } from 'vue-router'

import Login from '@/components/Login.vue'
import Intro from '@/components/Intro.vue'
import Game from '@/components/Game.vue'

import { store } from '@/store.js'

const routes = [
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/',
        name: 'Intro',
        component: Intro
    },
    {
        path: '/gameplay',
        nmae: 'Game',
        component: Game
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

router.beforeEach((to, from, next) => {
    if (!store.isAuthenticated && to.name !== 'Login') {
        next({ name: 'Login'})
    } else {
        next()
    }
})

export default router