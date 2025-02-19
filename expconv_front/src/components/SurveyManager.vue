<template>
<HeaderForm button-text="Profile" @buttonClick="openModal"/>
  <UserModalForm v-if="showModal" user-details="userDetails" @closeModal="closeModal"/>
</template>

<script>
import {defineComponent} from "vue";
import HeaderForm from "@/components/NavigationsComponents/HeaderForm.vue";
import UserModalForm from "@/components/NavigationsComponents/UserModalForm.vue";
export default defineComponent({
  components: {UserModalForm, HeaderForm},
  data(){
    return {
      userDetails:{
        name:"",
        username:" ",
        email:"",
      },
      showModal:false,
    };
  },
  methods:{
    openModal(){
      this.showModal = true;
    },
    closeModal(){
      this.showModal = false;
    }

  }
})
</script>

<style scoped>


</style>