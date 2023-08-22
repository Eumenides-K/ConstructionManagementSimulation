<template>
        <div class="result">
            <h1 style="font-size: 24px;"><el-icon><Crop /></el-icon> Your final result</h1>

            <p><el-icon><OfficeBuilding /></el-icon> Building height: <span class="data">{{ store.height }}m</span></p>
            <p><el-icon><DataBoard /></el-icon> Project progress:</p>
            <div class="chart-container">
                <v-chart :option="store.progress" autoresize />
            </div>
            <p><el-icon><Money /></el-icon> Remaining budget: <span class="data">£ {{ store.budget }}</span></p>
        </div>
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
.result {
    text-align: left; 
    background-color: #ecebe6;
    padding: 15px;
    border-radius: 5px;
    box-shadow: 2px 2px 6px #888888;
    font-family: 'Roboto';
}
.data {
    color:#0527af
}

.chart-container {
  height:38.2vh;
  width: 28vw;
  background-color: #eaf2f8;
}
</style>