<template>
  <div class="login">
    <header>
      <img src="../assets/login_logo.png" alt="Логотип СПБ ФИЦ РАН" class="logo">
      <h1>Войти в систему</h1>
    </header>
    <form class="login-form" @submit.prevent = "handleSubmit">
      <label for="username">Логин:</label>
      <input type="text" id="username" name="username" placeholder="Введите логин" required v-model="formData.username">

      <label for="password">Пароль:</label>
      <input type="password" id="password" name="password" placeholder="Введите пароль" required v-model="formData.password">

      <input type="submit" class="submit-button" value="Войти">
      <p class="register-prompt">
        Нет аккаунта? <a href="register.html" class="register-link" target="_self" >Зарегистрируйтесь </a>
      </p>
    </form>
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
    };
  },
  methods:{
    async handleSubmit(){
      try {
        const response = await fetch('/api/user/login', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            username: this.data.username,
            password: this.data.password,
          })
        });

        if (response.ok){
          const result = await response.json();
          console.log(result);
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

<style>


</style>