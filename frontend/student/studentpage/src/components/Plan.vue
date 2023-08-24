<template>
<h1><el-icon><Checked /></el-icon> Make a plan first before the game start!</h1>
            <div class="plan">
                <p>week1: </p>
                <span>
                    Decide hom many units of construction material to purchase:
                    <el-input-number v-model="materialNum" :min="0" :max="100" />
                </span>
                <span>
                    Decide your construction team size: 
                    <div class="number-input">
                        <button @click="decrement1">-</button>
                        <div class="icons">
                        <span
                            v-for="n in 10"
                            :key="n"
                            :class="{ active: n <= week1.labour }"
                            class="icon"
                        ><el-icon><User /></el-icon></span>
                        </div>
                        <button @click="increment1">+</button>
                    </div>
                </span>
            </div>

            <div class="result">
                <p>Your units of material in storage: {{ storage }}</p>
                <p>Your estimated remaining budget: {{ budget }}</p>
                <p>Your estimated height: {{ height }}</p>
            
            </div>
</template>

<script>
import { store } from '@/store.js'

export default {
    data() {
        return {
            store,
            week1: {material: 0, labour: 0},
            week2: {material: 0, labour: 0},
            week3: {material: 0, labour: 0},
            week4: {material: 0, labour: 0},
            week5: {material: 0, labour: 0},
            week6: {material: 0, labour: 0},
            week7: {material: 0, labour: 0},
            week8: {material: 0, labour: 0},
            week9: {material: 0, labour: 0},
            week10: {material: 0, labour: 0},
            budget: 25000000,
            height: 0,
            storage: 0,
        }
    },
    methods: {
        increment1() {
            if (this.week1.labour < 10) {
                this.week1.labour++;
            }
        },
        decrement1() {
            if (this.week1.labour > 0) {
                this.week1.labour--;
            }
        },
    },
    watch: {
        week1(newWeek1) {
            let allMaterial = this.week1.material + this.storage
        }
    }
}
</script>

<style scoped>
.number-input {
  display: flex;
  align-items: center;
}

.icons {
  display: flex;
}

</style>