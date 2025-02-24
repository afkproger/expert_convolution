<template>
  <HeaderForm button-text="Профиль" @buttonClick="openCloseModal" />
  <transition name="slide">
    <UserModalForm v-if="showModal" />
  </transition>
  <div class="workspace">
    <h1 style="font-size: 50px">Опросники</h1>
    <button class="submit-button">Создать новый опросник</button>
    <hr/>
    <div v-if="usersTasks.length" style="width: 60%">
      <TaskPropertyForm
          v-for="task in usersTasks"
          :key="task.id"
          :taskDetails="task"
          @calculate="handleCalculateConvolution"
          @send="handleSendQuestionnaire"
          @delete="handleDeleteTask"
          @update="handleUpdateTask"
      />
    </div>
    <p v-else>Задач пока нет...</p>
  </div>
</template>

<script>
import HeaderForm from "@/components/NavigationsComponents/HeaderForm.vue";
import UserModalForm from "@/components/NavigationsComponents/UserModalForm.vue";
import {fetchWithAuth} from "@/api/apiService";
import TaskPropertyForm from "@/components/SurveyComponents/TaskPropertyForm.vue";

export default {
  components: {TaskPropertyForm, UserModalForm, HeaderForm},
  data() {
    return {
      usersTasks: [],
      showModal: false,
    };
  },
  mounted() {
    this.getUserTasks();
  },
  methods: {
    openCloseModal() {
      this.showModal = !this.showModal;
    },
    async getUserTasks() {
      const user_id = localStorage.getItem("userId");

      if (!user_id) {
        console.error("User ID не найден в localStorage");
        return;
      }

      try {
        const response = await fetchWithAuth(`${this.$apiBaseUrl}users/${user_id}/tasks`, {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
        }, this.$apiBaseUrl, this.$router);

        if (response.ok && response) {
          const result = await response.json();
          console.log(result);
          if (result) {
            this.usersTasks = result;
          } else {
            console.error("Ошибка: пустые данные пользователя");
          }
        } else {
          console.error("Ошибка запроса: ", response.status);
        }
      } catch (e) {
        console.error("Ошибка запроса:", e.message);
      }
    },
    handleCalculateConvolution(id) {
      console.log("Calculate Convolution from parent: " + id);
    },
    handleSendQuestionnaire(id) {
      console.log("Sending Questionnaire from parent: " + id);
    },
    handleUpdateTask(id) {
      console.log("Update Task from parent: " + id);
    },
    async handleDeleteTask(id) {
      try {
        // Делаем запрос на удаление
        const response = await fetchWithAuth(`${this.$apiBaseUrl}tasks/${id}`, {
          method: "DELETE",
          headers: {
            "Content-Type": "application/json",
          }
        }, this.$apiBaseUrl, this.$router);

        if (response.ok) {
          const index = this.usersTasks.findIndex(task => task.id === id);
          if (index !== -1) {
            this.usersTasks.splice(index, 1);
            console.log("Задача успешно удалена из массива.");
          }
        } else {
          console.error("Ответ сервера с ошибкой: ");
        }
      } catch (e) {
        console.error("Ошибка запроса:", e.message);
      }
    }
  }
};
</script>

<style scoped>
.workspace{
  width: calc(100% - 20px);
  margin-top: 90px;
  align-items: center;
  flex-direction: column;
  display: inline-flex;
}

.slide-enter-active, .slide-leave-active {
  transition: transform 0.1s ease-in-out, opacity 0.1s ease-in-out;
}

.slide-enter-from, .slide-leave-to {
  transform: translateX(100%);
  opacity: 0;
}

hr {
  margin: 10px;
}

</style>