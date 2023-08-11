<template>
  <el-container>
    <el-header class="app-header">
      <img :src="imagePath" style="width: auto; height: 150%; margin-right: 30px;" >
      <h1 style="color: #333; font-size: 1.618vm; font-weight: 500; font-family: 'Roboto', sans-serif; text-shadow: 4px 4px 10px #222222">Construction Project Management Simulation</h1>
    </el-header>

    <el-main>
      <router-view />
    </el-main>
  </el-container>
</template>

<script>


export default {
  name: 'App',
  data() {
    return {
      imagePath: require('@/assets/tools.png')
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.app-header {
  display: flex;
  align-items: center;
  background-color: #848587;
  padding: 10px;
  border-bottom: 1px solid #848587;
  position: relative;
  z-index: 2;
}
</style>
