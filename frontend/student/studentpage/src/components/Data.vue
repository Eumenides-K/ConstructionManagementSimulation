<template>
    <ul>
      <li>
        <h1>Total Budget: £ 25,000,000</h1>
      </li>
      <li>
        <h1>Material Cost: £ 200,000 per unit (at the beginning of the game)</h1>
        One unit of building material can be used to build one meter of the building.
        <br>
        <br>
        The price will slightly fluctuate due to the weekly market change and can be changed dramactically after some unanticipated events.
      </li>
      <li>
        <h1>Labour Cost: </h1>
        The labour cost will increase as the height increase after the building reaches a certain height.
        <br>
        <br>
        One unit of labour can built one meter of the building per week.
        <br>
        <br>
        The following chart shows how the cost of employing each unit of labor per week changes with the height of the building:
        <br>
        <div class="chart-container">
          <v-chart :option="labourCost" autoresize />
        </div>
      </li>
      <li>
        <h1>Storage Cost: £ 120,000 per week</h1>
      </li>
      <li>
      <h1>Shipping Cost: £ 150,000 per shipment</h1> 
      </li>
      <li>
        <h1>Planning Cost: TBD</h1>
      </li>
      <li>
        <h1>Evaluation Methods: TBD</h1>  
      </li>
    </ul>

</template>
  
<script>
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
  components: {
    VChart
  },
  data() {
    return {
      labourCost: {
        title: {
          show: true,
          text: 'labour cost(£)/heights(m)',
          left: 'center',
          top: '8%',
          textStyle: {
            fontSize: 14,
            fontWeight: 'normal'
          },
        },
        grid: {
          left: '20%',
        },
        xAxis: {},
        yAxis: {},
        series: [
          {
            data: [
              [0, 100000],
              [10, 100000],
              [20, 100000],
              [30, 100000],
              [40, 100000],
              [50, 100000],
              [60, 120000],
              [70, 140000],
              [80, 160000],
              [90, 180000],
              [100, 200000],
            ],
            type: 'line'
          }
        ]
      }
    };
  }
};
</script>
<style scoped>
.chart-container {
  height:30vh;
  width: 18vw;
}
</style>

