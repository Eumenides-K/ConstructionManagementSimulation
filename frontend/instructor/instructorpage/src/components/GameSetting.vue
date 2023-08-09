<template>
<h1 style="color: #333; font-size: 1.618vm; font-weight: 500; font-family: 'Roboto', sans-serif; text-shadow: 4px 4px 10px #222222">Scenario Settings</h1>
<p class="tips">
    You can choose the default scenario setting or customnize your own settings. There are 10 weeks in one game run. 
    Add some unaticipated events in some of weeks to test students' ability to cope with unexpected situation in project management.
    There are four kinds of unaticipated events:
    <ul style="font-style:italic; font-family:Georgia, 'Times New Roman', Times, serif">
        <li>Supply Chain Disruptions: increase the price of construction materials in short term</li>
        <li>Labour Strike: delay the schedule of construction</li>
        <li>Sudden Equipment Failure: delay the progress of construction and cost a maintenance fee</li>
        <li>Regulatory Changes: increase the price of construction materials in long term</li>
    </ul>
    * Note that one kind of event above can only happen ONCE during a game. Adding 3 unaticipated events in total in a single game is recommended, but you can also choose to add less or more.
    Considering some of smart students can complete the project using less than 10 weeks (so that they can spend less on storage costs), so try to add events in earlier weeks. 
    And usually it is pointless to set multiple events in one same week although you can do it.
</p>
<div class="radio">
    <el-radio-group v-model="defaultSettings">
        <el-radio :label=true size="large">Default Scenario Settings</el-radio>
        <el-radio :label=false size="large">Customized Scenario Settings</el-radio>
    </el-radio-group>
</div>
<br v-if="!defaultSettings">
<div v-if="!defaultSettings" class="form">
    
    <el-form :model="form" label-width=auto>
        <el-form-item label="Supply Chain Disruptions at week: ">
            <el-select v-model="scd">
                <el-option
                    v-for="week in weeks"
                    :key="week.value"
                    :label="week.label"
                    :value="week.value">
                </el-option>
            </el-select>
        </el-form-item>

        <el-form-item label="Labour Strike at week: ">
            <el-select v-model="ls">
                <el-option
                    v-for="week in weeks"
                    :key="week.value"
                    :label="week.label"
                    :value="week.value">
                </el-option>
            </el-select>
        </el-form-item>

        <el-form-item label="Sudden Equipment Failure at week: ">
            <el-select v-model="sef">
                <el-option
                    v-for="week in weeks"
                    :key="week.value"
                    :label="week.label"
                    :value="week.value">
                </el-option>
            </el-select>
        </el-form-item>

        <el-form-item label="Regulatory Changes at week: ">
            <el-select v-model="rc">
                <el-option
                    v-for="week in weeks"
                    :key="week.value"
                    :label="week.label"
                    :value="week.value">
                </el-option>
            </el-select>
        </el-form-item>
    </el-form>
</div> 

<br>
<div class="result">
    <p v-if="scd != 0">Supply Chain Disruption at week {{ scd }}</p>
    <p v-if="ls != 0">Labour Strike at week  {{ ls }}</p>
    <p v-if="sef != 0">Sudden Equipment Failure at week {{ sef }}</p>
    <p v-if="rc != 0">Regulatory Changes at week {{ rc }}</p>
    <p v-if="scd == 0 && ls == 0 && sef == 0 && rc == 0">No unaticipated events is set.</p>
</div>
<br>
<el-button type="primary" round @click="dialogVisible = true">Start the game</el-button>
<el-dialog
    v-model="dialogVisible"
    title="Warning"
    width="30%">    
    <span>Are you sure you want to start a new game run? You cannot modify the scenario settings after the game starts.</span>
    <template #footer>
        <span class="dialog-footer">
            <el-button @click="dialogVisible = false">Cancel</el-button>
            <el-button type="primary" @click="startGame" style="font-family: 'Roboto'">Confirm</el-button>
        </span>
    </template>
</el-dialog>
</template>

<script>
import { store } from '@/store.js'
export default {
    data() { 
        return {
            defaultSettings: true,
            scd: 2,
            ls: 4,
            sef: 7,
            rc: 0,
            weeks: [
                { label: 'None', value: 0 },
                { label: '1', value: 1 },
                { label: '2', value: 2 },
                { label: '3', value: 3 },
                { label: '4', value: 4 },
                { label: '5', value: 5 },
                { label: '6', value: 6 },
                { label: '7', value: 7 },
                { label: '8', value: 8 },
                { label: '9', value: 9 },
                { label: '10', value: 10 }
            ],
            dialogVisible: false,
            store
        }
    },
    methods: {
        startGame() {
            const data = {
                id: this.store.selectedSeminarId,
                title: this.store.selectedSeminarName,
                instructor_id: this.store.signedId,
                start: true,
                scd: this.scd,
                ls: this.ls,
                sef: this.sef,
                rc: this.rc
            }
            console.log(data)
            this.$axios.put('/seminars', data).
            then(response => {
                console.log(response.data)
                this.$router.push('result')
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
    },
    watch: {
        defaultSettings(newDefaultSettings) {
            if (newDefaultSettings) {
                this.scd = 2
                this.ls = 4
                this.sef = 7
                this.rc = 0
            } else {
                this.scd = 0
                this.ls = 0
                this.sef = 0
                this.rc = 0
            }
        } 
    }
}
</script>

<style scoped>
.tips {
    background-color: #eaf2f8;
    padding: 15px;
    border-radius: 5px;
    box-shadow: 2px 2px 6px #888888;
    font-family: 'Roboto';
}

.radio {
    background-color: #eaf8eb;
    padding: 15px;
    border-radius: 5px;
    box-shadow: 2px 2px 6px #888888;
    font-family: 'Roboto';
}

.result {
    background-color: #f8f4ea;
    padding: 15px;
    border-radius: 5px;
    box-shadow: 2px 2px 6px #888888;
    font-family: 'Roboto';
}

.form {
    background-color: #ecebe6;
    padding: 15px;
    border-radius: 5px;
    box-shadow: 2px 2px 6px #888888;
    font-family: 'Roboto';
}
</style>