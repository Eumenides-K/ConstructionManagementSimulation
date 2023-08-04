import { reactive } from "vue"

export const store = reactive({
    isAuthenticated: false,
    signedEmail: '',
    signedId: 0,
    selectedSeminarId: 0,
    selectedSeminarName: ''
})