<template>
<HeaderForm button-text="Профиль" @buttonClick="openCloseModal"/>
  <transition name="slide">
    <UserModalForm v-if="showModal"/>
  </transition>
  <div class="workspace">
    <h1>Опросники</h1>
    <button class="submit-button"> Создать новый опросник</button>
  </div>
</template>

<script>
import HeaderForm from "@/components/NavigationsComponents/HeaderForm.vue";
import UserModalForm from "@/components/NavigationsComponents/UserModalForm.vue";
import {fetchWithAuth} from "@/api/apiService";

export default {
  components: {UserModalForm, HeaderForm},
  data() {
    return {
      userDetails: {
        id: 0,
        name: "",
        username: "",
        email: "",
      },
      user_id: localStorage.getItem("userId"),
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
        } , this.$apiBaseUrl , this.$router);

        if (response.ok && response) {
          const result = await response.json();

          if (result) {
            // тут добавить обработку для получения данных о задачах от пользователя
          } else {
            console.error("Ошибка: пустые данные пользователя");
          }
        } else {
          console.error("Ошибка запроса: ", response.status);
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
  display: flex;
  align-items: center;
  flex-direction: column;
}

.slide-enter-active, .slide-leave-active {
  transition: transform 0.1s ease-in-out, opacity 0.1s ease-in-out;
}

.slide-enter-from, .slide-leave-to {
  transform: translateX(100%);
  opacity: 0;
}

</style>