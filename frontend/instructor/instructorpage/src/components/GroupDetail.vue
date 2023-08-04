<template>
    <h1 style="color: #333; font-size: 1.618vm; font-weight: 500; font-family: 'Roboto', sans-serif; text-shadow: 4px 4px 10px #222222">Groups Detail</h1>
  <el-table :data="groupData" :default-sort="{ prop: 'name', order: 'descending' }" stripe border style="width: 61.8%">
    <el-table-column prop="name" label="Name"/>
    <el-table-column prop="password_hash" label="Password"/>
  </el-table>
</template>

<script>
import { store } from '@/store.js'

export default {
    data() {
        return {
            store,
            groupData: []
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