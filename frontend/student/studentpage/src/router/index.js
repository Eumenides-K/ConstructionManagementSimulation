import { createRouter, createWebHistory } from 'vue-router'

import Login from '@/components/Login.vue'
import Intro from '@/components/Intro.vue'
import Game from '@/components/Game.vue'
import Result from '@/components/Result.vue'
import Decision from '@/components/Decision.vue'

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
        component: Intro,
    },
    {
        path: '/gameplay',
        name: 'Game',
        component: Game,
        children: [
            {
                path: 'result',
                name: 'Result',
                component: Result
            },
            {
                path: '',
                name: 'Decision',
                component: Decision
            }
        ]
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