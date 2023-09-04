<template>
    <h1><el-icon><Checked /></el-icon> Make a plan first before the game start!</h1>
    <div class="tip">
        <p>
            <el-icon><Warning /></el-icon> In this phase you can set the target height of your building and you will know the estimated material and labour cost as well as the estimated remaining budget.
            <span style="color: red;">Note that shipment cost, storage cost and any cost caused by random events are not taken into consideration here.</span> 
            So please make sure you have enough budget to cope with these.
        </p>
    </div>

    <br>

    <div class="plan">
        <p>Decide your target height (between 50 and 100 meters): </p>
        <el-input-number v-model="targetHeight" :min="50" :max="100" />
    </div>

    <br>

    <div class="estimation">
        <p>
            Estimated Material Cost: {{ targetHeight * store.materialCost }}
            <br>
            Estimated Labour Cost: {{ targetHeight * store.labourCost + (targetHeight - 50) * store.labourCost * (targetHeight - 50) / 100 }}
            <br>
            Estimated Remaining Budger: {{ store.budget - targetHeight * store.materialCost - targetHeight * store.labourCost + (targetHeight - 50) * store.labourCost * (targetHeight - 50) / 100 }}
        </p>
    </div>

    <br>

    <el-button type="primary" @click="startGame">Start the game</el-button>


</template>

<script>
import { store } from '@/store.js'

export default {
    data() {
        return {
            store,
            targetHeight: 0,
        }
    },
    methods: {
        startGame() {
            this.store.targetHeight = this.targetHeight
            let id = this.store.seminarId
            this.$axios.get(`/seminars/${id}`)
            .then(response => {
                let seminar = response.data
                if(!seminar.start) {
                    alert("Your instructor has not started the game yet.")
                } else {
                    this.store.scd = seminar.scd
                    this.store.ls = seminar.ls
                    this.store.sef = seminar.sef
                    this.store.rc = seminar.rc
                    this.$router.push('/gameplay')
                }
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
    }
} 
</script>

<style scoped>
.tip {
    text-align: left;
    font-size: small;
    background-color:bisque;
    padding: 15px;
    border-radius: 5px;
    box-shadow: 2px 2px 6px #888888;
    font-family: 'Roboto';
}
.plan {
    text-align: left;
    font-size: small;
    background-color: #ecebe6;
    padding: 15px;
    border-radius: 5px;
    box-shadow: 2px 2px 6px #888888;
    font-family: 'Roboto';
}

.estimation {
    text-align: left;
    font-size: small;
    background-color: #eaf2f8;
    padding: 15px;
    border-radius: 5px;
    box-shadow: 2px 2px 6px #888888;
    font-family: 'Roboto';
}
</style>