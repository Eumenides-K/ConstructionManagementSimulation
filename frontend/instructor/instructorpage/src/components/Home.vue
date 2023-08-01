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
   <el-container direction="vertical" class="seminarChoiceContainer">
      <div class="seminarChoice">
         <h1 style="color: #333; font-size: 1.618vm; font-weight: 500; font-family: 'Roboto', sans-serif; text-shadow: 4px 4px 10px #222222">Welcome, {{ this.store.signedEmail }}</h1>
         <div v-if="seminars.length === 0">
            <p style="font-family: 'Roboto'">You have not created any seminars yet. Please create one.</p>
         </div>
         <div v-else>
            <p>Please select a seminar:</p>
               <el-select v-model="selectedSeminar" class="m-2" placeholder="Select a seminar" size="large">
                  <el-option
                     v-for="seminar in seminars"
                     :key="seminar.id"
                     :value="seminar.title"
                  />
               </el-select>
         </div>
         <br>
         <el-button type="primary" round @click="createSeminar">Create a seminar</el-button>
      </div>
   </el-container>
</template>

<script>
   import { store } from '@/store.js'
   export default {
      data() {
         return {
            store,
            seminars: []
         }
      },
      created() {
         let instructorId = this.store.signedId
         console.log('finding seminars...')
         this.$axios.get(`/seminars/instructor/${instructorId}`)
         .then(response => {
            this.seminars = response.data;
         })
         .catch(error => {
            console.log(error);
         });
      },
      methods: {
         createSeminar() {
            this.$router.push('/createseminar')
         }     
      }
   }
</script>

<style scoped>
.particle{
    position: absolute;
    top:0;
    width:100%;
    z-index: 1;
}

.seminarChoiceContainer {
   height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 0 10%;
}

.seminarChoice {
   background-color: #ffffff;
   border: 1px solid #848587;
   padding: 20px;
   max-width: 38.2%; 
   width: 100%;
   border-radius: 10px;
   box-shadow: 5px 5px 10px rgba(0,0,0,0.15); 
   position: relative;
   z-index: 2;
   text-align: center;
}
</style>