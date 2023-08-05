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
    <el-container style="display: flex; height: 100vh;" class="groupContainer">
      <el-aside style="flex: 0.618; display: flex; justify-content: center; background-color: #f0f0f0; box-shadow: 2px 0px 6px 0px rgba(0,0,0,0.1);">
        <div style="max-width: 90%;">
          <GroupDetail />
        </div>
      </el-aside>
      <el-main style="flex: 1.382; background-color: #ffffff; box-shadow: -2px 0px 6px 0px rgba(0,0,0,0.1);">
        <GameSetting />
      </el-main>
    </el-container>
  </template>
  
 

<script>
import { store } from '@/store.js'
import GroupDetail from './GroupDetail.vue'
import GameSetting from './GameSetting.vue'

export default {
    data() {
        store
    },
    components: {
        GroupDetail,
        GameSetting
    },
    beforeUnmounted() {
        this.store.selectedSeminarName = ''
        this.store.selectedSeminarId = 0
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
.groupContainer {
    position: relative;
    z-index: 2;
}
</style>