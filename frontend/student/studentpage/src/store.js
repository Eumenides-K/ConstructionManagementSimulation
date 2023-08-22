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
    budget: 25000000,
    shipmentCost: 150000,
    storageCost: 120000,
    labourCost: 100000,
    storage: 0,
    storageLimit: 8,
    overStorage: false,
    unitsOver: 0,
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