import { reactive } from "vue"

export const store = reactive({
    isAuthenticated: false,
    signedId: 0,
    week: 1,
    seminarId: 0,
    scd: 0,
    ls: 0,
    sef: 0,
    rc: 0,
    height: 0,
    price: 200000,
    budget: 27500000,
    budgetBefore: 27500000,
    shipmentCost: 150000,
    storageCost: 150000,
    labourCost: 100000,
    storage: 0,
    storageLimit: 10,
    unitsOver: 0,
    overStorage: false,
    storageOver: 0,
    materialPurchased: 0,
    materialCost: 200000,
    labourHired: 0,
    targetHeight: 50,
    progress: {
        title: {
            show: true,
            text: 'height(m)/week',
            left: 'center',
            top: '8%',
            textStyle: {
                fontSize: 14,
                fontWeight: 'normal'
            },
        },
        grid: {
            left: '20%',
        },
        xAxis: {
            type: 'category',
            data: []
        },
        yAxis: {
            type: 'value',
            min: 0,
            max: 100
        },
        series: [
            {
                data: [],
                type: 'line'
            },
        ]
    }
})