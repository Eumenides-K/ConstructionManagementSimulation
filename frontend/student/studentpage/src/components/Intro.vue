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
    <el-container style="display: flex; height: 100vh;" class="introContainer">
        <el-aside style="flex: 1.2; display: flex; flex-direction: column; padding-left: 20px; background-color: #f0f0f0; box-shadow: 2px 0px 6px 0px rgba(0,0,0,0.1);">
            <h1 style="margin-top: 20px; text-align: left; font-size: larger;">Prepare to play</h1>
            <p style="margin-top: 10px; margin-bottom: 20px; text-align: left; font-size: smaller;">Being familiar with this information will help you complete your project successfully and on time.</p>
            <Tab />
        </el-aside>

      <el-main style="flex: 0.8; background-color: #ffffff; box-shadow: -2px 0px 6px 0px rgba(0,0,0,0.1); font-family: 'Roboto'">
        <div v-if="start === true">
            <Plan/>
        </div>

        <div v-else>
            <h1>Ready to start?</h1>
            <br>
            <el-button type="primary" size="large" style="width: 61.8%;" @click="dialog1Visible = true">View Tutorial</el-button>

            <el-dialog
                    v-model="dialog1Visible"
                    title="Tutorial"
                    width="61.8%"
                    height=auto
                >
                    <p style="text-align: left; font-size: medium;">Make your management decision every week as follows: </p>
                    <img :src="imagePath" style="width: 61.8%; height: auto;">
                    <p style="text-align: left; font-size: medium;">Also keep checking your progress and alerts in the left-hand side.</p>
                    <p style="text-align: left; font-size: medium;">Read the "Prepare to play" part carefully before start the game.</p>
                    <template #footer>
                    <span class="dialog-footer">
                        <el-button @click="dialog1Visible = false">Close</el-button>
                    </span>
                    </template>
                </el-dialog>

            <br>
            <br>
            <el-button size="large" style="width: 61.8%;" @click="start = true">Play Now</el-button>
            <!-- <el-button size="large" style="width: 61.8%;" @click="startGame">Play Now</el-button> -->
            <br><br>
            <el-link type="primary" @click="logout">Log out</el-link>
        </div>
        
    </el-main>
    </el-container>

</template>

<script>
import { store } from '@/store.js'
import Tab from './Tab.vue'
import Plan from './Plan.vue'

export default {
    data() {
        return {
            imagePath: require('@/assets/tutorial.jpg'),
            store,
            activeTab: 'tab1',
            dialog1Visible: false,
            start: false,

        }
    },
    components: {
        Tab,
        Plan
    },
    created() {
        let id = this.store.signedId
        this.$axios.get(`/groups/seminarid/${id}`)
        .then(response => {
            this.store.seminarId = response.data
        })
        .catch(error => {
            if (error.response) {
                console.log(error.response.data)
                console.log(error.response.status)
                console.log(error.response.headers)
            } else if (error.request) {
                console.log(error.request)
            } else {
                console.log('Error', error.message)
            }
            alert("Failed to get seminar infomation. Please try again or contact the administrator.")
            this.store.isAuthenticated = false
            this.store.signedId = 0
            this.$router.push('login')
            })
    },
    methods: {
        logout() {
            this.$router.push('/login')
            this.store.signedId = 0
            this.isAuthenticated =false
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

.introContainer {
    position: relative;
    z-index: 2;
}

</style>