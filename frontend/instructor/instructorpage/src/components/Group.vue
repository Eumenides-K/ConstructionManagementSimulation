<template>
    <div class="common-layout">
        <el-container style="display: flex;">
            <el-aside style="flex: 1;"><GroupDetail /></el-aside>
            <el-main style="flex: 1;"><GameSetting /></el-main>
        </el-container>
     </div>
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