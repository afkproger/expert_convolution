<template>
  <div class="container">
    <aside class="modal_slider">
      <h1> Hello world </h1>
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
</style>