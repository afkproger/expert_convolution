<template>
  <header>
    <div class="header-container">
      <a href="https://spcras.ru/" target="_blank">
        <img src="../../assets/pages/logo.jpg" alt="Ссылка на сайт">
      </a>
    </div>
    <div class="header-nav">
      <ul>
        <li> <a href="https://spcras.ru/spiiras/" class="link" target="_blank"> Об институте </a> </li>
        <li> <a href="https://litsam.ru/index.php/ru/homepage-ru" class="link" target="_blank"> О лаборатории </a> </li>
      </ul>
      <button @click="handleClick" class="submit-button">{{buttonText}}</button>
    </div>
  </header>
</template>

<script>
export default {
  props: {
    buttonText:{
      type: String,
      required: true
    }
  },
  methods: {
    handleClick(){
      this.$emit('buttonClick');
    }
  }
}
</script>

<style scoped>

header{
  margin: 0 10px 10px 10px;
  padding: 10px;
  display:flex;
  width: calc(100% - 40px);
  height: 60px;
  justify-content: space-between;
  position: fixed;
  z-index: 99;
  top: 0;
  background-color: white;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.header-container img{
  width: 50px;
  height: 50px;
}
.header-nav{
  width: 60%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;

}

.header-nav ul{
  display: flex;
  list-style: none;
}
.header-nav ul li{
  margin: 0 10px 10px 10px;
}

.submit-button{
  margin-right: 50px;
  width: 100px;
  height: 40px;
}

.link {
  font-size: 24px;
}

</style>