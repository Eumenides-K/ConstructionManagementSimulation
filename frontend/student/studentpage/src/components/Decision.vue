<template>
    <h1 style="text-align: left; font-size: 24px;">
        <el-icon><Operation /></el-icon> Decision for <span class="data">Week {{ this.store.week }}</span>
    </h1>

    <div class="warning" v-if="this.store.week === this.store.scd || this.store.week === this.store.rc">
        <p v-if="this.store.week === this.store.scd">
            <el-icon><Warning /></el-icon> A supply chain disruption is currently taking place. The material cost this week can be increased.
        </p>
        <p v-if="this.store.week === rc">
            <el-icon><Warning /></el-icon> The goverment just made some regulatory changes. The material cost will be lowered from now on.
        </p>
    </div>

    <br v-if="this.store.week === this.store.scd || this.store.week === this.store.rc">

    <div class="material">
        <h2 style="font-size: 18px"><el-icon><Shop /></el-icon> PURCHASING MATERIAL</h2>       
        <p>Currently <span class="data">{{ this.store.storage }}</span> units of material are in stock.</p>
        <p>The price of material per unit this week is <span class="data">£ {{ livePrice }}</span> and the shipment cost is <span class="data">£ {{ this.store.shipmentCost }}</span>.</p>
        <strong>Decide hom many units of construction material to purchase:</strong>
        <br><br>
        <span><el-input-number v-model="materialNum" :min="0" :max="100" /><p v-if="isInvalidInput" style="color: red;">Please enter a valid number.</p></span>
        <br>       
        <p>Note that there is always a storage Cost of at least <span class="data">£ {{ this.store.storageCost }}</span> per week for every ten surplus units of material.</p>
        <p style="color: red;">The maxium units of material can be stored is <span class="data">{{ this.store.storageLimit }}</span>. If you have more than {{ this.store.storageLimit }} units left by the end of the week, the excess will be discarded.</p>
    </div>

    <br>

    <div class="labour">
        <h2 style="font-size: 18px;"><el-icon><Avatar /></el-icon> EMPLOYING LABOUR</h2>
        <strong>Decide your construction team size: </strong>
        <p>The labour cost per unit is <span class="data">£ {{ this.store.labourCost }}</span>. One unit of labour can only handle one unit of material each week.</p>
        <div class="number-input">
            <button @click="decrement">-</button>
            <div class="icons">
            <span
                v-for="n in 10"
                :key="n"
                :class="{ active: n <= value }"
                class="icon"
            ><el-icon><User /></el-icon></span>
            </div>
            <button @click="increment">+</button>
        </div>
    </div>

    <br>

    <div class="result">
        <p v-if="this.store.budget - this.livePrice * this.materialNum - this.store.shipmentCost - this.value * this.store.labourCost > 0">
            <el-icon><Grid /></el-icon> Your estimated remaining budget is 
            <span v-if="materialNum === 0" class="data">£ {{ this.store.budget - value * this.store.labourCost }}</span>
            <span v-else class="data">£ {{ this.store.budget - livePrice * materialNum - this.store.shipmentCost - value * this.store.labourCost }}</span>.
        </p>
        <p v-else style="color:red">Your budget is not enough, please remake your decision.</p>
        <div class="button-container">
            <el-button type="primary" size="large" :disabled="this.store.budget - livePrice * materialNum - this.store.shipmentCost - value * this.store.labourCost <= 0 || this.store.week === 10" @click="dialog1Visible=true">
                Submit
            </el-button>
            <el-dialog
                v-model="dialog1Visible"
                title="Warning"
                width="30%"
            >
                <span>Are you sure you want to submit your decision and go to next week?</span>
                <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialog1Visible = false">Cancel</el-button>
                    <el-button type="primary" @click="nextWeek">
                    Confirm
                    </el-button>
                </span>
                </template>
            </el-dialog>
            <el-button type="danger" size="large" :disabled="this.store.budget - livePrice * materialNum - value * this.store.labourCost < 0"  @click="dialog2Visible = true">
                Deliver Project
            </el-button>
            <el-dialog
                v-model="dialog2Visible"
                title="Warning"
                width="30%"
            >
                <span v-if="store.week < 10">Are you sure you want to submit your decision and deliver the project ahead of schedule? You still have <span class="data">{{ 10 - store.week }} weeks</span> left</span>
                <span v-else>Are you sure you want to submit your decision and deliver the project?</span>
                <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialog2Visible = false">Cancel</el-button>
                    <el-button type="primary" @click="deliver">
                    Confirm
                    </el-button>
                </span>
                </template>
            </el-dialog>
        </div>   
    </div>
    <div v-if="
    (materialNum !== 0 &&
    this.store.budget - livePrice * materialNum - this.store.shipmentCost - value * this.store.labourCost < 0) ||
    (materialNum === 0 && this.store.budget - value * this.store.labourCost < 0)
    ">
        <br>
        <strong style="text-align: right; color: red;">
            Your budget is not enough!
        </strong>
    </div>
</template>

<script>
import { store } from '@/store.js'

export default {
    data() {
        return {
            store,
            livePrice: 200000,
            maxMaterial: 100,
            materialNum: 0,
            value: 0,
            dialog1Visible: false,
            dialog2Visible: false,
            isInvalidInput: false
        }
    },
    created() {
        // Price of a unit of construction material
        if (this.store.week === this.store.rc) {
            this.store.price = 150000
        }
        this.livePrice = this.store.price + Math.floor(Math.random() * (20001)) - 10000
        // The maxium number of units of material that can be purchased according to the budget
        this.maxMaterial = Math.floor((this.store.budget - this.store.shipmentCost) / this.livePrice)
        // The labour cost per unit
        if (this.store.height > 50) {
            this.store.labourCost = 100000 + 2000 * (this.store.height - 50) / 50
        }
    },
    watch: {
        materialNum(newValue) {
            if (typeof newValue !== 'number' || newValue < 0) {
                this.isInvalidInput = true;
            } else {
                this.isInvalidInput = false;
            }
        },
    },
    methods: {
        increment() {
        if (this.value < 10) {
            this.value++;
        }
        },
        decrement() {
        if (this.value > 0) {
            this.value--;
        }
        },
        nextWeek() {
            this.dialog1Visible = false

            this.store.budgetBefore = this.store.budget

            if (this.store.height > 50) {
                this.store.labourCost = 100000 + 2000 * (this.store.height - 50) / 50
            }

            if (this.materialNum === 0) {
                this.store.budget = this.store.budget - this.value * this.store.labourCost - this.store.storageCost
            } else {
                this.store.budget = this.store.budget - this.livePrice * this.materialNum - this.store.shipmentCost - this.value * this.store.labourCost - this.store.storageCost
            }

            if (this.store.week === this.store.sef) {
                this.store.budget = this.store.budget - 200000
            }

            if (this.store.week === this.store.ls || this.store.week === this.store.sef) {
                if (this.materialNum + this.store.storage > this.store.storageLimit) {
                    this.store.storage = this.materialNum + this.store.storage
                    this.store.overStorage = true
                    this.store.unitsOver = this.materialNum + this.store.storage - this.store.storageLimit
                    if (this.store.unitsOver % this.store.storageLimit === 0) {
                        this.store.storageOver = this.store.unitsOver / this.store.storageLimit
                    } else {
                        this.store.storageOver = Math.floor(this.store.unitsOver / this.store.storageLimit) + 1
                    }
                } else {
                    this.store.storage = this.store.storage + this.materialNum
                    this.store.overStorage = false
                }
            } else {
                let material = this.store.storage + this.materialNum
                if (material <= this.value) {
                    this.store.height = this.store.height + material
                    this.store.storage = 0
                    this.store.overStorage = false
                } else {
                    this.store.height = this.store.height + this.value
                    if (material - this.value <= this.store.storageLimit) {
                        this.store.storage = material - this.value
                        this.store.overStorage = false
                    } else {
                        this.store.storage = material - this.value
                        this.store.overStorage = true
                        this.store.unitsOver = material - this.value - this.store.storageLimit
                        if (this.store.unitsOver % this.store.storageLimit === 0) {
                            this.store.storageOver = this.store.unitsOver / this.store.storageLimit
                        } else {
                            this.store.storageOver = Math.floor(this.store.unitsOver / this.store.storageLimit) + 1
                        }
                    }
                }
            }

            this.store.budget = this.store.budget - this.store.storageCost * this.store.storageOver
            if (this.store.budget < 0) {
                this.store.budget = 0
            }
            
            this.store.progress.xAxis.data.push('week' + this.store.week.toString())
            this.store.progress.series[0].data.push(this.store.height)
            this.store.materialPurchased = this.materialNum
            this.store.materialCost = this.livePrice
            this.store.labourHired = this.value

            // renew data
            this.store.week++
            if (this.store.week === this.store.rc) {
                this.store.price = 150000
            }
            this.livePrice = this.store.price + Math.floor(Math.random() * (20001)) - 10000
            if (this.store.week === this.store.scd) {
                this.livePrice = this.livePrice * 2
            }
            this.maxMaterial = Math.floor((this.store.budget - this.store.shipmentCost) / this.livePrice)
            if (this.store.height > 50) {
                this.store.labourCost = 100000 + 2000 * (this.store.height - 50) / 50
            }
            this.materialNum = 0
            this.value = 0
        },
        deliver() {
            this.store.budgetBefore = this.store.budget

            if (this.store.height > 50) {
                this.store.labourCost = 100000 + 2000 * (this.store.height - 50) / 50
            }

            if (this.materialNum === 0) {
                this.store.budget = this.store.budget - this.value * this.store.labourCost - this.store.storageCost
            } else {
                this.store.budget = this.store.budget - this.livePrice * this.materialNum - this.store.shipmentCost - this.value * this.store.labourCost - this.store.storageCost
            }

            if (this.store.week === this.store.sef) {
                this.store.budget = this.store.budget - 200000
            }

            if (this.store.week === this.store.ls || this.store.week === this.store.sef) {
                if (this.materialNum + this.store.storage > this.store.storageLimit) {
                    this.store.storage = this.materialNum + this.store.storage
                    this.store.overStorage = true
                    this.store.unitsOver = this.materialNum + this.store.storage - this.store.storageLimit
                    if (this.store.unitsOver % this.store.storageLimit === 0) {
                        this.store.storageOver = this.store.unitsOver / this.store.storageLimit - 1
                    } else {
                        this.store.storageOver = this.store.unitsOver / this.store.storageLimit
                    }
                } else {
                    this.store.storage = this.store.storage + this.materialNum
                    this.store.overStorage = false
                }
            } else {
                let material = this.store.storage + this.materialNum
                if (material <= this.value) {
                    this.store.height = this.store.height + material
                    this.store.storage = 0
                    this.store.overStorage = false
                } else {
                    this.store.height = this.store.height + this.value
                    if (material - this.value <= this.store.storageLimit) {
                        this.store.storage = material - this.value
                        this.store.overStorage = false
                    } else {
                        this.store.storage = material - this.value
                        this.store.overStorage = true
                        this.store.unitsOver = material - this.value - this.store.storageLimit
                        if (this.store.unitsOver % this.store.storageLimit === 0) {
                            this.store.storageOver = this.store.unitsOver / this.store.storageLimit - 1
                        } else {
                            this.store.storageOver = this.store.unitsOver / this.store.storageLimit
                        }
                    }
                }
            }

            this.store.budget = this.store.budget - this.store.storageCost * this.store.storageOver
            if (this.store.budget < 0) {
                this.store.budget = 0
            }

            this.store.progress.xAxis.data.push('week' + this.store.week.toString())
            this.store.progress.series[0].data.push(this.store.height)
            this.store.materialPurchased = this.materialNum
            this.store.materialCost = this.livePrice
            this.store.labourHired = this.value

            this.$router.push({ name: 'Result' });
        },
    }
}
</script>

<style scoped>
.material {
    background-color: #f8f4ea;
    padding: 15px;
    border-radius: 5px;
    box-shadow: 2px 2px 6px #888888;
    font-family: 'Roboto';
    text-align: left;
}
.warning {
    background-color: pink;
    padding: 15px;
    border-radius: 5px;
    box-shadow: 2px 2px 6px #888888;
    font-family: 'Roboto';
    text-align: left;
}

.labour {
    background-color: #eaf2f8;
    padding: 15px;
    border-radius: 5px;
    box-shadow: 2px 2px 6px #888888;
    font-family: 'Roboto';
    text-align: left;
}

.result {
    background-color: #ecebe6;
    padding: 15px;
    border-radius: 5px;
    box-shadow: 2px 2px 6px #888888;
    font-family: 'Roboto';
    text-align: left;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.data {
    color:#0527af
}

.number-input {
  display: flex;
  align-items: center;
}

.icons {
  display: flex;
}

.icon {
  color: grey;
  font-size: 24px;
  margin: 0 2px;
}

.icon.active {
  color: black;
}

.button-container {
    display: flex;
    gap: 10px; 
}
</style>