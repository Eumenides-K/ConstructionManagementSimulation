<template>
    <vue-particles
        color="#555"
        :particleOpacity="0.7"
        :particlesNumber="150"
        shapeType="circle"
        :particleSize="4"
        linesColor="#555"
        :linesWidth="1"
        :lineLinked="true"
        :lineOpacity="0.4"
        :linesDistance="150"
        :moveSpeed="2"
        :hoverEffect="true"
        hoverMode="grab"
        :clickEffect="false"
        clickMode="push"
        class="particle"
    />
    <el-container class="gameContainer">
        <el-aside style="flex: 1; display: flex; flex-direction: column; padding-left: 20px; background-color: #f0f0f0; box-shadow: 2px 0px 6px 0px rgba(0,0,0,0.1);">
            <Status />
        </el-aside>

        <el-main style="flex: 1; background-color: #ffffff; box-shadow: -2px 0px 6px 0px rgba(0,0,0,0.1); font-family: 'Roboto'">
            <router-view />
        </el-main>
    </el-container>
</template>

<script>
import { store } from '@/store.js'
import Decision from './Decision.vue'
import Status from './Status.vue'

export default {
    data() {
        return {
            store
        }
    },
    components: {
        Decision,
        Status
    },
    mounted() {
        window.addEventListener('beforeunload', this.beforeUnload);
    },
    unmounted() {
        window.removeEventListener('beforeunload', this.beforeUnload);
    },
    methods: {
        beforeUnload(event) {
            event.preventDefault();
            event.returnValue = 'Are you sure you want to exit now? You will lost all your game progress.';
        }
    }
}
</script>

<style scoped>
.particle {
    position: absolute;
    top:0;
    width:100%;
    z-index: 1;
}

.gameContainer {
    position: relative;
    z-index: 2;
    display: flex;
    height: 100vh;
}
</style>