<template>
  <div class="task-settings">
    <h2>{{ isEditMode ? 'Редактирование опросника' : 'Создание нового опросника' }}</h2>
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
import { fetchWithAuth } from "@/api/apiService";

export default {
  components: { ScaleForm, InfoForm, IndicatorsForm },
  props: {
    taskData: Object,
  },
  data() {
    return {
      task: this.taskData
          ? { ...this.taskData }
          : {
            title: "",
            description: "",
            scale: [],
            indicators: [],
          },
      userId: localStorage.getItem("userId"),
    };
  },
  computed: {
    isEditMode() {
      return !!this.task.id;
    },
  },
  methods: {
    async submitTaskDetails() {
      const responseData = {
        title: this.task.title,
        description: this.task.description,
        scale: this.task.scale,
        indicators: this.task.indicators,
      };

      try {
        const method = this.isEditMode ? "PUT" : "POST"; // Меняем метод
        const response = await fetchWithAuth(
            `${this.$apiBaseUrl}users/${this.userId}/tasks`,
            {
              method,
              headers: { "Content-Type": "application/json" },
              body: JSON.stringify(responseData),
            },
            this.$apiBaseUrl,
            this.$router
        );

        if (response.ok) {
          this.$emit("taskSaved"); // Обновляем родительский список задач
          this.goToWorkspace();
        }
      } catch (err) {
        console.error(err);
      }
    },
    goToWorkspace() {
      this.$emit("closeForm");
    },
  },
};
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