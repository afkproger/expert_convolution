<template>
  <div class="container">
    <div class="form-container">
      <header>
        <img src="../assets/pages/login_logo.png" alt="Логотип СПБ ФИЦ РАН" class="logo">
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
      userTokens:{
        id: 0,
        username: '',
        accusesToken: '',
        refreshToken: ''
      }
    };
  },
  methods:{
    async handleSubmit(){
      try {
        const response = await fetch(`${this.$apiBaseUrl}auth/login`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            username: this.formData.username,
            password: this.formData.password,
          })
        });

        if (response.ok){
          const result = await response.json();
          if (result){
            this.userTokens = result;
            localStorage.setItem('userTokens', JSON.stringify(this.userTokens));
          }else {
            alert("Ошибка в работе системы")
          }
        }else {
          const result = await response.json();
          alert(`Ошибка при входе в систему ${result.message}`);
        }
      }catch(error){
        console.error(error);
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