<template>
  <div class="container">
    <transition name="slide">
      <aside class="modal_slider">
        <table class="table">
          <tbody>
          <tr>
            <td>Имя:</td>
            <td>{{ userDetails.name }}</td>
          </tr>
          <tr>
            <td>Почта:</td>
            <td>{{ userDetails.email }}</td>
          </tr>
          <tr>
            <td>Логин:</td>
            <td>{{ userDetails.username }}</td>
          </tr>
          </tbody>
        </table>
        <div class="button-group">
          <button @click="goToDetail" class="submit-button">Инструкция</button>
          <button @click="logout" class="submit-button exit"> Выйти </button>
        </div>
      </aside>
    </transition>
  </div>
</template>


<script>
import {fetchWithAuth} from "@/api/apiService";

export default {
  data(){
    return {
      userDetails: {
        id: 0,
        name: "",
        username: "",
        email: "",
      },
    };
  },

  mounted(){
    document.body.style.backgroundColor= "#9e9b9b";
    this.getUserDetails();
  },
  beforeUnmount() {
    document.body.style.backgroundColor= "#f5f5f5";
  },
  methods:{
    goToDetail(){
      this.$router.push('/')
    },
    async logout() {
      try {
        const response = await fetchWithAuth(`${this.$apiBaseUrl}auth/logout`, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          credentials: "include",
        } , this.$apiBaseUrl, this.$router);

        if (response.ok) {
          const result = await response.json();
          console.log("Ответ от сервера:", result);
          this.$router.push('/')
          localStorage.removeItem("userId");
        } else {
          alert(`Ошибка при выходе из системы:`);
        }
      } catch (error) {
        console.error("Ошибка в запросе:", error);
      }
    },
    async getUserDetails() {
      const user_id = localStorage.getItem("userId");

      if (!user_id) {
        console.error("User ID не найден в localStorage");
        return;
      }

      try {
        const response = await fetchWithAuth(`${this.$apiBaseUrl}users/${user_id}`, {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
        } , this.$apiBaseUrl , this.$router);

        if (response.ok && response) {
          const result = await response.json();

          if (result) {
            this.userDetails = result;
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
}
</script>

<style scoped>
.container {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  justify-content: flex-end;
  align-items: flex-start;
  z-index: 50;
  margin-top: 90px;
}
.modal_slider{
  margin-right: 10px;
  background-color: white;
  border: 1px solid #9ac430;
  width: 400px;
  height: 260px;
  border-radius: 10px;
  padding: 10px;
  transition: transform 0.3s ease-in-out;
}
.submit-button {
  width: 120px;
}



</style>