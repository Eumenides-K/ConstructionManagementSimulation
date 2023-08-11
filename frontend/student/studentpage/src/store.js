import { reactive } from "vue"

export const store = reactive({
    isAuthenticated: false,
    signedId: 0,
    week: 1
})