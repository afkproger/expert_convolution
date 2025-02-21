<template>
  <div class="container">
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
  </div>
</template>


<script>
export default {
  props:{
    userDetails:{
      type:Object,
      required: true
    }
  },
  methods:{
    goToDetail(){
      this.$router.push('/')
    },
    async logout() {
      try {
        const response = await fetch(`${this.$apiBaseUrl}auth/logout`, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          credentials: "include",
        });

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
    }
  }
}
</script>

<style scoped>
.container {
  justify-content: flex-end;
  height: auto;
  align-items: flex-start;
  margin-top: 90px;
}
.modal_slider{
  font-size: 20px;
  margin-right: 10px;
  background-color: white;
  border: 1px solid #9ac430;
  width: 400px;
  height: 260px;
  border-radius: 10px;
  padding: 10px;
}
.submit-button {
  width: 120px;
}
.button-group{
  margin-bottom: 10px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  padding: 10px;

}

</style>