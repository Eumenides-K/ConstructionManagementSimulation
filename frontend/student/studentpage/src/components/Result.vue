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
    <el-container direction="vertical" class="result-container">
        <div class="result">
            <h1><el-icon><Crop /></el-icon> Your final result</h1>

            <p><el-icon><OfficeBuilding /></el-icon> Building height: <span class="data">{{ store.height }}m</span></p>
            <p><el-icon><DataBoard /></el-icon> Project progress:</p>
            <div class="chart-container">
                <v-chart :option="store.progress" autoresize />
            </div>
            <p><el-icon><Money /></el-icon> Remaining budget: <span class="data">£ {{ store.budget }}</span></p>
        </div>
    </el-container>
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
.particle {
    position: absolute;
    top:0;
    width:100%;
    z-index: 1;
}
.result-container {
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 0 10%;
}

.result {
    background-color: #ffffff;
    border: 1px solid #848587;
    padding: 20px;
    max-width: 38.2%; 
    width: 100%;
    border-radius: 10px;
    box-shadow: 5px 5px 10px rgba(0,0,0,0.15); 
    position: relative;
    z-index: 2;
}

.data {
    color:#0527af
}

.chart-container {
  height:38.2vh;
  width: 28vw;
}
</style>