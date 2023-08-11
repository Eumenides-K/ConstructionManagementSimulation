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
        <el-aside style="flex: 1.382; display: flex; flex-direction: column; padding-left: 20px; background-color: #f0f0f0; box-shadow: 2px 0px 6px 0px rgba(0,0,0,0.1);">
    
            <h1 style="margin-top: 20px; text-align: left; font-size: larger;">Prepare to play</h1>
            <p style="margin-top: 10px; margin-bottom: 20px; text-align: left; font-size: smaller;">Being familiar with this information will help you complete your project successfully and on time.</p>
            
            <el-tabs class="demo-tabs" style="text-align: left; width:85%">
                <el-tab-pane label="Project Brief">
                    <div style="display: flex; flex-direction: column; align-items: center;">                       
                        <p>
                            In this simulation you are the project manager at a building site. 
                            Each week, you have to decide how many units of construction material to order and how many workers to hire based on your assumptions about the project progress.
                            According to the client's requirement, the building should be as high as possible and the project has to be completed within 10 weeks but you can also complete it ahead of schedule.
                            Your performance will be evaluated by the heights of the building and your total expenditure. Depending on the configuration set by your instructor, you may encounter some unanticipated events.
                            Try to overcome it and deliver your project on time.
                        </p>
                        <br>
                        <img :src="imagePath1" style="width: 38.2%; height: auto;">
                    </div>
                </el-tab-pane>
                <el-tab-pane label="Managing Your Project" >
                    <div style="display: flex; flex-direction: column; align-items: center;">   
                        <p>
                            You have a limited total budget of the project which you are going to use to order construction material and employ construction workers.
                            Each week you can order unlimited amounts of construction material as long as your budget can afford. No matter how much you order, there is always a fixed shipping cost.
                            However, even the largest-size construction team you can employ can only use a certain amount of material in a week.
                            The remaining material needs to be stored so there is a fixed storage cost for the surplus material each week. 
                            However, the amount of material that can be stored is limited and the excess will be wasted.
                            Try to complete the project as soon as possible to save on storage cost.
                            There will be several unexpected events which will largely influence (raise or lower) the price of the construction material or prevent your project from going smoothly.
                            You should always take them into consideration when you make the decision.
                        </p>
                        <br>
                        <br>
                        <img :src="imagePath2" style="width: 25%; height: auto;">
                    </div>
                </el-tab-pane>
            </el-tabs>          
        </el-aside>

      <el-main style="flex: 0.618; background-color: #ffffff; box-shadow: -2px 0px 6px 0px rgba(0,0,0,0.1); font-family: 'Roboto'">
        <h1>Ready to start?</h1>
        <br>
        <el-button type="primary" size="large" style="width: 61.8%;">View Tutorial</el-button>
        <br>
        <br>
        <el-button size="large" style="width: 61.8%;" @click="startGame">Play Now</el-button>
      </el-main>
    </el-container>

</template>

<script>
import { store } from '@/store.js'
export default {
    data() {
        return {
            imagePath1: require('@/assets/building-sites-icon.png'),
            imagePath2: require('@/assets/mgt-icon.png')
        }
    },
    methods: {
        startGame() {

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