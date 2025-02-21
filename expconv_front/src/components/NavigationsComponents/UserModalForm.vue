<template>
  <div class="container">
    <aside class="modal_slider">
      <table class="table">
        <tbody>
        <tr>
          <td>Имя</td>
          <td>{{ userDetails.name }}</td>
        </tr>
        <tr>
          <td>Почта</td>
          <td>{{ userDetails.email }}</td>
        </tr>
        <tr>
          <td>Логин</td>
          <td>{{ userDetails.username }}</td>
        </tr>
        </tbody>
      </table>
      <div>
        <button @click="goToDetail" class="submit-button">Инструкция</button>
        <button @click="logout" class="submit-button"> Выйти </button>
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
    logout(){
      console.log('logout');
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
  height: 400px;
  border-radius: 10px;
  padding: 10px;
}
.submit-button {
  width: 100px;
}

</style>