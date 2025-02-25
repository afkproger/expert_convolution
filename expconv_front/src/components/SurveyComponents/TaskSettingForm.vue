<template>
  <div class="task-settings">
    <h2>Создание нового опросника</h2>
    <form @submit.prevent="submitTaskDetails">
      <InfoForm v-model:task-object="task" />
      <ScaleForm v-model:scale="task.scale"/>
      <IndicatorsForm v-model:indicators="task.indicators"/>
    </form>
  </div>
</template>

<script>
import ScaleForm from "@/components/SurveyComponents/TaskSettings/ScaleForm.vue";
import IndicatorsForm from "@/components/SurveyComponents/TaskSettings/IndicatorsForm.vue";
import InfoForm from "@/components/SurveyComponents/TaskSettings/InfoForm.vue";
export default {
  components: {ScaleForm, InfoForm , IndicatorsForm},
  methods: {
    submitTaskDetails (){
      console.log("Данные отправились на сервер");
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
  background-color: #f9f9f9;
  border-radius: 8px;
  max-width: 800px;
  margin: auto;
}
.task-settings h2{
  text-align: center;
}
</style>