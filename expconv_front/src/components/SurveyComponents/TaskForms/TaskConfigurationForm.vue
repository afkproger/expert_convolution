<template>
  <div class="task-settings">
    <h2>Создание нового опросника</h2>
    <form @submit.prevent="submitTaskDetails">
      <InfoForm v-model:task-object="task" />
      <ScaleForm v-model:scale-object="task.scale"/>
      <IndicatorsForm v-model:indicator-object="task.indicators"/>
    </form>
    <div class="button-group">
      <button @click="goToWorkspace" class="submit-button">К задачам</button>
      <button @click="submitTaskDetails" class="submit-button"> Сохранить </button>
    </div>
  </div>
</template>

<script>
import ScaleForm from "@/components/SurveyComponents/ConvolutionSettings/ScaleForm.vue";
import IndicatorsForm from "@/components/SurveyComponents/ConvolutionSettings/IndicatorsForm.vue";
import InfoForm from "@/components/SurveyComponents/ConvolutionSettings/InfoForm.vue";
export default {
  components: {ScaleForm, InfoForm , IndicatorsForm},
  methods: {
    submitTaskDetails (){
      console.log("Данные отправились на сервер");
      console.log(this.task);
    },
    goToWorkspace (){
      this.$emit("closeForm")
    }
  },
  data(){
    return {
      task:{
        name: "",
        description: "",
        scale: [],
        indicators: []
      }
    }
  }
}

</script>

<style scoped>

.task-settings {
  padding: 30px;
  border-radius: 8px;
  max-width: 800px;
  margin: 90px auto 10px auto;
  align-items: center;
  background-color: #f9f9f9;
}
.task-settings h2{
  text-align: center;
}
.button-group {
  margin-top: 20px;
}
</style>