<template>

<el-tabs v-model="activeTab" style="text-align: left; width: 90%">
        <el-tab-pane label="Project Brief" name="tab1">
            <div style="display: flex; flex-direction: column; align-items: center;">                       
                <p>
                    In this simulation you are the project manager at a building site. 
                    Each week, you have to decide how many units of construction material to order and how many workers to hire based on your assumptions about the project progress.
                    <br><br>
                    According to the client's requirement, the building should be as high as possible and the project has to be completed within 10 weeks but you can also complete it ahead of schedule.
                    Your performance will be evaluated by the heights of the building and your total expenditure. Depending on the configuration set by your instructor, you may encounter some unanticipated events.
                    Try to overcome it and deliver your project on time.
                </p>
                <br>
                <img :src="imagePath1" style="width: 38.2%; height: auto;">
            </div>
        </el-tab-pane>
        <el-tab-pane label="Managing Your Project" name="tab2">
            <div style="display: flex; flex-direction: column; align-items: center;">   
                <p>
                    You have a limited total budget of the project which you are going to use to order construction material and employ construction workers.
                    <br><br>
                    Each week you can order unlimited amounts of construction material as long as your budget can afford. No matter how much you order, there is always a fixed shipping cost.
                    <br><br>
                    Notice that even the largest-size construction team you can employ can only use a certain amount of material in a week.
                    The remaining material needs to be stored so there is a fixed storage cost for the a certain amount of surplus material each week.
                    There is always a storage cost each week, but if your surplus material excess the storage limit, you will be charged for more storage cost.  
                    Try to complete the project as soon as possible to save on storage cost.
                    <br><br>
                    The labor cost will increase with the height of the building.
                    <br><br>
                    There will be several unexpected events which will largely influence (raise or lower) the price of the construction material or prevent your project from going smoothly.
                    You should always take them into consideration when you make the decision.
                </p>
                <br>
                <br>
                <img :src="imagePath2" style="width: 25%; height: auto;">
            </div>
        </el-tab-pane>
        <el-tab-pane label="Expenditure and Evaluation Data" name="tab3" :lazy=true>
            <Data />
        </el-tab-pane>
    </el-tabs>     
</template>

<script>
import Data from './Data.vue'

export default {
    data() {
        return {
            imagePath1: require('@/assets/building-sites-icon.png'),
            imagePath2: require('@/assets/mgt-icon.png'),
            activeTab: 'tab1'
        }
    },
    components: {
        Data
    },
}
</script>

