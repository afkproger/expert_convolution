<template>
  <div class ="container">
    <div class="form-container">
      <header>
        <img src="../assets/pages/login_logo.png" alt="Логотип СПБ ФИЦ РАН" class="logo">
        <h1>Регистрация</h1>
      </header>
      <form @submit.prevent = "handleSubmit">
        <label for="name"> Имя:</label>
        <input type="text" id="name"  v-model="formData.name" required  placeholder="Введите ваше имя" >

        <label for="username"> Логин:</label>
        <input type="text" id="username" required  placeholder="Введите ваш логин" v-model="formData.username">

        <label for="password"> Пароль:</label>
        <input type="password" id="password" required placeholder="Придумайте пароль" v-model="formData.password">

        <label for="confirm_password"> Повторите пароль:</label>
        <input type="password" id="confirm_password" required  placeholder="Повторите пароль" v-model="formData.confirm_password">

        <label for="email">Введите вашу почту</label>
        <input type="email" id="email" required  placeholder="Введите вашу почту:" v-model="formData.email">
        <button type="submit" class="submit-button"> Зарегестироваться </button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      formData: {
        name: '',
        username: '',
        password: '',
        confirm_password: '',
        email: '',
      },
    };
  },
  methods: {
    checkConfirmPassword(password , confirm_password) {
      return password === confirm_password;
    },
    async handleSubmit() {
      if (!this.checkConfirmPassword(this.formData.password , this.formData.confirm_password)) {
        alert("Пароли не совпадают");
        return;
      }

      try {
        console.log(this.$apiBaseUrl);
        const response = await fetch(`${this.$apiBaseUrl}auth/register`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            name: this.formData.name,
            username: this.formData.username,
            password: this.formData.password,
            passwordConfirmation: this.formData.confirm_password,
            email: this.formData.email,
          })
        });

        if (response.ok) {
          const result = await response.json();
          console.log(result);
          await this.$router.push('/login');
        }else {
          const error = await response.json();
          console.error(error);
          alert("Ошибка при регистрации");
        }
      }catch(err) {
        console.error(`Ошибка при запросе: ${err}`);
      }
    }
  }
};
</script>
<style scoped>

.form-container {
  max-width: 400px;
  max-height: 650px;
}

</style>