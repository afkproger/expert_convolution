<template>
  <div class="task-property-form">
    <img src="../../assets/pages/change_convolution.png" @click="updateConvolution(taskDetails.id)" class="change-convolution">
    <h2>{{taskDetails.title}}</h2>
    <p> {{taskDetails.description}} </p>
    <p> Статус: {{taskDetails.status}} </p>
    <div class="button-group">
      <button @click="calculateConvolution(taskDetails.id)" class="submit-button">Рассчитать свёртку</button>
      <button @click="sendQuestionnaire(taskDetails.id)" class="submit-button">Отправить опрос экспертам</button>
      <button @click="deleteTask(taskDetails.id)" class="submit-button delete-button">Удалить задачу</button>
    </div>
  </div>
</template>

<script>
export default {
  props:{
    taskDetails:{
      type:Object,
      required:true
    }
  },
  methods:{
    calculateConvolution(id){
      this.$emit('calculate',id)
    },
    sendQuestionnaire(id){
      this.$emit("send" , id)
    },
    deleteTask(id){
      this.$emit("delete" , id)
    },
    updateConvolution(id){
      this.$emit("update" , id)
    }
  }
}

</script>

<style scoped>
.task-property-form {
  position: relative; /* Добавлено для корректного позиционирования вложенных элементов */
  align-items: center;
  width: calc(100% - 40px);
  box-shadow: -2px -2px 4px rgba(61, 105, 16, 0.5),
  2px 2px 4px rgba(61, 105, 16, 0.5);
  border-radius: 10px;
  padding: 5px;
  margin: 15px;
}

.task-property-form:hover {
  background-color: #dcdcdc;
}

.task-property-form h2,
.task-property-form p {
  text-align: center;
}

.change-convolution {
  position: absolute;
  top: 10px;
  right: 10px;
  width: 35px;
  height: 35px;
  cursor: pointer;
}

</style>