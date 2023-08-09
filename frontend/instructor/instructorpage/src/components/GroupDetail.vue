<template>
    <h1 style="color: #333; font-size: 1.618vm; font-weight: 500; font-family: 'Roboto', sans-serif; text-shadow: 4px 4px 10px #222222">Groups Detail</h1>
  <el-table :data="groupData" :default-sort="{ prop: 'name', order: 'descending' }" stripe border style="width: 95%" id="table">
    <el-table-column prop="name" label="Name"/>
    <el-table-column prop="id" label="Group ID"/> 
    <el-table-column prop="password_hash" label="Password"/>
  </el-table>

  <el-link type="primary" @click="deriveExcel">Download as Excel</el-link>

    <p style="font-family: 'Roboto'">* Students use the group ID and password to sign in to the game.
        <div style="color: red;">
        One group can only log in on one computer at a time.</div>
    </p>
    <div>
        <el-button type="danger" round @click="dialogVisible = true" style="margin-right: 10px; font-family: 'Roboto'">Delete this seminar</el-button>
    </div>
    <el-dialog
        v-model="dialogVisible"
        title="Warning"
        width="30%"
    >    
        <span>Are you sure you want to delete this seminar? (Students can no longer log in to the game if you delete the seminar)</span>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="dialogVisible = false">Cancel</el-button>
                <el-button type="primary" @click="deleteSeminar" style="font-family: 'Roboto'">Confirm</el-button>
            </span>
        </template>
    </el-dialog>
    <br>
    <div>
        <el-button type="info" round @click="reselect">Reselect a seminar</el-button>
    </div>
</template>

<script>
import { store } from '@/store.js'
import * as XLSX from 'xlsx'

export default {
    data() {
        return {
            store,
            groupData: [],
            dialogVisible: false
        }
    },
    created() {
        let seminar_id = this.store.selectedSeminarId
        console.log('finding groups...')
        this.$axios.get(`/groups/seminar/${seminar_id}`)
         .then(response => {
            this.groupData = response.data
         })
         .catch(error => {
            console.log(error)
         })
    },
    methods: {
        deriveExcel() {
            let workbook = XLSX.utils.table_to_book(document.getElementById('table')); 
            try {
                XLSX.writeFile(workbook, 'Groups Info.xlsx');
                alert('The Excel file is successfully downloaded.')
            } catch (e) {
                alert(e)
            }
        },
        deleteSeminar() {
            this.dialogVisible = false
            let id = this.store.selectedSeminarId
            this.$axios.delete(`/seminars/${id}`)
            .then(response => {
                alert("You have successfully deleted this seminar.")
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
        reselect() {
            this.$router.push('/')
        }
    },
    watch: {
        groupData: {
            immediate: true,
            handler(newValue) {
                newValue.sort((a, b) => a.name.localeCompare(b.name));
            },
        },
    }
}
</script>

<style scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>