<template>
  <div class="container">
    <div class="form-container">
      <header>
        <img src="../../assets/pages/login_logo.png" alt="Логотип СПБ ФИЦ РАН" class="logo">
        <h1>Войти в систему</h1>
      </header>
      <form  @submit.prevent = "handleSubmit">
        <label for="username">Логин:</label>
        <input type="text" id="username" name="username" placeholder="Введите логин" required v-model="formData.username">

        <label for="password">Пароль:</label>
        <input type="password" id="password" name="password" placeholder="Введите пароль" required v-model="formData.password">

        <input type="submit" class="submit-button" value="Войти">
        <p class="register-prompt">
          Нет аккаунта?
          <router-link to="/register" class="link">Зарегистрироваться</router-link>
        </p>
      </form>
    </div>

    <h1> </h1>
  </div>
</template>

<script>
export default {
  data(){
    return{
      formData:{
        username: '',
        password: ''
      },
      user_id: null
    };
  },
  methods:{
    async handleSubmit() {
      try {
        const response = await fetch(`${this.$apiBaseUrl}auth/login`, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          credentials: "include",
          body: JSON.stringify({
            username: this.formData.username,
            password: this.formData.password,
          }),
        });

        if (response.ok) {
          const result = await response.json();
          console.log("Ответ от сервера:", result);

          if (result && result.userId) {
            this.user_id = result.userId;
            console.log("User ID:", this.user_id);
            localStorage.setItem("userId", JSON.stringify(this.user_id));
            await this.$router.push("/workspace");
          } else {
            alert("Ошибка в работе системы: userId не найден");
          }
        } else {
          const result = await response.json();
          alert(`Ошибка при входе в систему: ${result.message}`);
        }
      } catch (error) {
        console.error("Ошибка в запросе:", error);
      }
    }
  }
}

</script>

<style scoped>

.form-container {
  max-width: 300px;
}

.register-prompt {
  margin: 10px;
  font-size: 15px;
  text-align: center;
}
</style>