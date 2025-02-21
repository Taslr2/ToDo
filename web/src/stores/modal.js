import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useModalStore = defineStore('modal', () => {
  // state
  const whetherShowModal = ref(false)
  const modalTitle = ref('')
  return {
    whetherShowModal,
    modalTitle,
  }
})
