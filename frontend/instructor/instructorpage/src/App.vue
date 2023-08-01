<template>
  <div class="common-layout">
    <el-container>
      <el-header class="app-header">
        <svg viewBox="0 0 1024 1024" width=2.5% height=auto xmlns="http://www.w3.org/2000/svg" style="margin-right: 30px;"><path fill="#333" d="M764.416 254.72a351.68 351.68 0 0 1 86.336 149.184H960v192.064H850.752a351.68 351.68 0 0 1-86.336 149.312l54.72 94.72-166.272 96-54.592-94.72a352.64 352.64 0 0 1-172.48 0L371.136 936l-166.272-96 54.72-94.72a351.68 351.68 0 0 1-86.336-149.312H64v-192h109.248a351.68 351.68 0 0 1 86.336-149.312L204.8 160l166.208-96h.192l54.656 94.592a352.64 352.64 0 0 1 172.48 0L652.8 64h.128L819.2 160l-54.72 94.72zM704 499.968a192 192 0 1 0-384 0 192 192 0 0 0 384 0z"></path></svg>
        <h1 style="color: #333; font-size: 1.618vm; font-weight: 500; font-family: 'Roboto', sans-serif; text-shadow: 4px 4px 10px #222222">Construction Management Simulation Admin Portal</h1>
      </el-header>

      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'App',
}
</script>

<style scoped>
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
