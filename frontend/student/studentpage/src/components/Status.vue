<template>
<el-tabs v-model="activeTab" style="text-align: left; width: 100%;">
    <el-tab-pane label="Instruction" name="tab1" :lazy=true>
        <Tab />
    </el-tab-pane>
    <el-tab-pane label="Dashboard" name="tab2" :lazy=true style="width:95%;">
        <h1 style="text-align: left; font-size: 24px;"><el-icon><Odometer /></el-icon> Dashboard for <span class="data">Week {{ store.week }}</span></h1>
        <div class="warning">
            <h2 style="font-size: 18px;"><el-icon><Warning /></el-icon> Alerts</h2>
            <p v-if="store.week !== 10 &&
                store.week !== store.rc && 
                store.week !== store.scd &&
                ((store.week - 1 === 0) || (store.week - 1 !== store.ls)) &&
                ((store.week - 1 === 0) || (store.week - 1 !== store.sef)) &&
                store.budget > 0 &&
                store.overStorage === false"
            >
                No alerts this week.
            </p>
            <ul v-else style="color:red">
                <li v-if="store.week === 10">
                    It is the last week. You have to deliver the project this week.
                </li>
                <li v-if="store.budget <= 0">
                    Your budget is not enough!
                </li>
                <li v-if="store.week - 1 !== 0 && store.week - 1 === store.ls">
                    The workers at the construction site participated in the strike last week and failed to complete the building project as planned.
                </li>
                <li v-if="store.week - 1 !== 0 && store.week - 1 === store.sef">
                    A sudden equipment failure took place last week. It cost <span class="data"></span>£ 200,000 to repair and delayed the schedule of construction.
                </li>
                <li v-if="store.week === store.scd">
                    A supply chain disruption is currently taking place. The material cost this week can be increased.
                </li>
                <li v-if="store.week === store.rc">
                    The goverment just made some regulatory changes. The material cost will be lowered from now on.
                </li>
                <li v-if="store.overStorage === true">
                    The warehouse capacity has reached its limit, and <span class="data">{{ store.unitsOver }} units</span> of excess building materials have been discarded.
                </li>
            </ul>
        </div>

        <br>

        <div class="pp">
            <h2 style="font-size: 18px;"><el-icon><Finished /></el-icon> Project Progress</h2>
            <p>Building height: <span class="data">{{ store.height }}m</span></p>
            <div v-if="store.height > 0">
                <p>Project progress:</p>
                <div class="chart-container">
                    <v-chart :option="store.progress" autoresize />
                </div>
            </div>
        </div>

        <br>

        <div class="br">
            <h2 style="font-size: 18px"><el-icon><Money /></el-icon> Budget and Resources</h2>
            <p>Material in storage: <span class="data">{{ this.store.storage }} units</span></p>
            <p>Remaining budget: <span class="data">£ {{ this.store.budget }}</span></p>
        </div>

    </el-tab-pane>
</el-tabs>
</template>



<script>
import { store } from '@/store.js'
import { DynamicSizeGrid } from 'element-plus';
import Tab from './Tab.vue'
import 'echarts/lib/component/title';
import { use } from 'echarts/core';
import { LineChart } from 'echarts/charts';
import { GridComponent, TooltipComponent } from 'echarts/components';
import { CanvasRenderer } from 'echarts/renderers';
import VChart from 'vue-echarts';

use([
  CanvasRenderer,
  LineChart,
  GridComponent,
  TooltipComponent
]);

export default {
    data() {
        return {
            store,
            activeTab: 'tab2'
        };
    },
    components: { 
        DynamicSizeGrid,
        Tab,
        VChart
    }
}
</script>

<style scoped>
.data {
    color:#0527af
}
.warning {
    background-color: pink;
    padding: 15px;
    border-radius: 5px;
    box-shadow: 2px 2px 6px #888888;
    font-family: 'Roboto';
    text-align: left;
}

.pp {
    background-color: #eaf2f8;
    padding: 15px;
    border-radius: 5px;
    box-shadow: 2px 2px 6px #888888;
    font-family: 'Roboto';
    text-align: left;
}

.br {
    background-color: #ecebe6;
    padding: 15px;
    border-radius: 5px;
    box-shadow: 2px 2px 6px #888888;
    font-family: 'Roboto';
    text-align: left;
}

.chart-container {
  height:30vh;
  width: 38.2vw;
}

</style>