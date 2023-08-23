<template>
    <h1 style="color: #333; font-size: larger; font-weight: 500; font-family: 'Roboto', sans-serif; text-shadow: 4px 4px 10px #222222"><el-icon><List /></el-icon> Result</h1>
    <div class="result">
    <h2 style="font-size: large;"><el-icon><Operation /></el-icon> Scenario Settings</h2>
    <p v-if="store.scd != 0">Supply Chain Disruption at week {{ store.scd }}</p>
    <p v-if="store.ls != 0">Labour Strike at week  {{ store.ls }}</p>
    <p v-if="store.sef != 0">Sudden Equipment Failure at week {{ store.sef }}</p>
    <p v-if="store.rc != 0">Regulatory Changes at week {{ store.rc }}</p>
    <p v-if="store.scd == 0 && store.ls == 0 && store.sef == 0 && store.rc == 0">No unaticipated events is set.</p>
    <br>
    <el-button type="danger" @click="dialogVisible = true">End game</el-button>
    <el-dialog
        v-model="dialogVisible"
        title="Warning"
        width="30%"
    >
    <span>Are you sure you want to end this game run? Please make sure every group has finished this game run.</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="endGame">
          Confirm
        </el-button>
      </span>
    </template>
  </el-dialog>
</div>
</template>

<script>
import { store } from '@/store.js'

export default {
    data() {
        return {
            store,
            dialogVisible: false,
        }
    },
    methods: {
        endGame() {
            this.dialogVisible = false
            const data = {
                id: this.store.selectedSeminarId,
                title: this.store.selectedSeminarName,
                instructor_id: this.store.signedId,
                start: false,
                scd: 0,
                ls: 0,
                sef: 0,
                rc: 0
            }
            this.$axios.put('/seminars', data).
            then(response => {
                console.log(response.data)
                
                this.store.gameStart = false
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
        }
    }
}
</script>

<style scoped>
.result {
    background-color: #f8f4ea;
    padding: 15px;
    border-radius: 5px;
    box-shadow: 2px 2px 6px #888888;
    font-family: 'Roboto';
}
</style>