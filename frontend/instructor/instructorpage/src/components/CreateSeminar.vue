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
   <el-container direction="vertical" class="seminarCreateContainer">
    <div class="seminarCreate">
        <h1 style="color: #333; font-size: 1.618vm; font-weight: 500; font-family: 'Roboto', sans-serif; text-shadow: 4px 4px 10px #222222">Create a seminar</h1>
        <el-link type="primary" style="position: absolute; top: 10px; left: 10px; cursor: pointer;" @click="back"><el-icon><Back /></el-icon>Back</el-link>
        <el-input v-model="name" placeholder="Please input the name of the seminar (e.g. 2023MGT)" />
        <br>
        <p style="font-family: 'Roboto'">How many groups are there in the seminar? <el-input-number v-model="num" :min="1" :max="100" /></p>
        <el-button type="primary" round @click="submit">Create</el-button>
    </div>
   </el-container>
</template>

<script>
import { store } from '@/store.js'
export default {
  data() {
    return {
      name: '',  
      num: 5,
      store
    }
  },
  methods: {
    submit() {
        const data = {
            title: this.name,
            instructor_id: this.store.signedId
        }
        this.$axios.post('/seminars', data)
        .then(response => {
            console.log("id: " + response.data)

            for (var i = 1; i <= this.num; i++) {
                let groupName = "Group" + i
                this.makeGroup(groupName, response.data)
            }
            alert("You have successfully created a seminar with " + this.num +" groups.")
            this.$router.push('/')
        })
        .catch(error => {
            if (error.response) {
                console.log(error.response.data)
                console.log(error.response.status)
                console.log(error.response.headers)
                alert(error.response.data)
            } else if (error.request) {
                console.log(error.request)
                alert("No response received from the server.")
            } else {
                console.log('Error', error.message)
                alert("Error: " + error.message)
            }
        }) 
    },
    makeGroup(groupName, seminarId) {
        let password_hash = this.generatePassword();
        const data = {
            name: groupName,
            password_hash: password_hash,
            seminar_id: seminarId
        }
        // TODO: deduplication
        this.$axios.post('/groups', data)
        .then(response => {
            console.log(response)
        })
        .catch(error => {
            if (error.response) {
                console.log(error.response.data)
                console.log(error.response.status)
                console.log(error.response.headers)
                alert(error.response.data)
            } else if (error.request) {
                console.log(error.request)
                alert("No response received from the server.")
            } else {
                console.log('Error', error.message)
                alert("Error: " + error.message)
            }
        })
    },
    generatePassword() {
        var length = 8,
            charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789",
            retVal = "";
        for (var i = 0, n = charset.length; i < length; ++i) {
            retVal += charset.charAt(Math.floor(Math.random() * n));
        }
        return retVal;
    },
    back() {
        this.$router.push('/')
    }
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

.seminarCreateContainer {
   height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 0 10%;
}

.seminarCreate {
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