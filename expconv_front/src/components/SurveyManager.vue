<template>
<HeaderForm button-text="Профиль" @buttonClick="openCloseModal"/>
  <UserModalForm v-if="showModal" :user-details="userDetails"/>
</template>

<script>
import HeaderForm from "@/components/NavigationsComponents/HeaderForm.vue";
import UserModalForm from "@/components/NavigationsComponents/UserModalForm.vue";
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
      showModal: false,
    };
  },
  mounted() {
    this.getUserDetails();
  },
  methods: {
    openCloseModal() {
      this.showModal = !this.showModal;
    },
    async getUserDetails() {
      const user_id = localStorage.getItem("userId");

      if (!user_id) {
        console.error("User ID не найден в localStorage");
        return;
      }

      try {
        const response = await fetch(`${this.$apiBaseUrl}users/${user_id}`, {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
          credentials: "include",
        });

        if (response.ok) {
          const result = await response.json();
          console.log("Ответ от сервера:", result); // Проверяем, что сервер отправил

          if (result) {
            this.userDetails = result;
            console.log("userDetails обновлен:", this.userDetails); // Теперь должно работать
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


</style>