<template>
<el-tabs v-model="activeTab" style="text-align: left; width: 100%;     overflow: visible;">
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
                    The warehouse capacity has reached its limit, and the excess <span class="data">{{ store.unitsOver }} units</span> of excess building materials cost <span class="data">£ {{ store.storageOver * store.storageCost }}</span> to store.
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
            <p v-if="this.store.week === 1">Remaining budget: <span class="data">£ {{ this.store.budget }}</span></p>
            <p v-else>
                Remaining budget:
                <br>
                <div class="cal">
                    <p>£ {{ store.budgetBefore }}</p>
                    <p v-if="store.materialPurchased !== 0">  - {{ store.materialPurchased }} * £ {{ store.materialCost }} (material cost)</p>
                    <p v-if="store.materialPurchased !== 0">  - £ {{ store.shipmentCost }} (shipment cost)</p>
                    <p v-if="store.labourHired !== 0">  - {{ store.labourHired }} * £ {{ store.labourCost }} (labour cost)</p>
                    <p>  - £ {{ store.storageCost }} (storage cost)</p>
                    <p v-if="store.overStorage === true">  - £ {{ store.storageCost * store.storageOver }} (extra storage cost)</p>
                    <p v-if="store.week - 1 !== 0 && store.week - 1 === store.sef">  - £ 200000 (equipment maintenance)</p>
                    <p>
                          = 
                        <span v-if="store.budget !== 0">£ {{ store.budget }}</span>
                        <span v-else style="color: red;">£ {{ store.budget }}</span>
                    </p>
                </div>
            </p>
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

.cal {
    color:#0527af;
    font-size: small;
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