<template>
  <h3>Показатели и вопросы
    <span class="info-icon" @click=" showIndicatorsHint = ! showIndicatorsHint">?</span>
    <span v-if=" showIndicatorsHint" class="tooltip">
            Введите название показателя и вопрос для оценки его вклада в общую эффективность
    </span>
  </h3>
  <div v-for="(valueItem, index) in taskIndicators" :key="index" class="indicator-container">
      <input type="text" v-model="valueItem.indicator" placeholder="Наименование индикатора" required />
      <textarea
          v-model="valueItem.question"
          placeholder="Вопрос для определения эффективности"
          required
          class="question-textarea"
      ></textarea>
      <button type="button" class="submit-button exit" @click="removeIndicatorItem(index)">Удалить</button>
  </div>
  <button type="button" class="submit-button" @click="addIndicatorItem">Добавить показатель</button>
</template>

<script>
export default {
  data(){
    return {
      showIndicatorsHint: false,

    }
  },
  methods: {
    addIndicatorItem(){
      this.taskIndicators.push({ indicator: '', question: '' });
    },
    removeIndicatorItem(index){
      this.taskIndicators.splice(index, 1);
    }
  },
  props:{
    indicatorObject:{
      type:Array,
      required:true
    }
  },
  computed:{
    taskIndicators:{
      get(){
        return this.indicatorObject;
      },
      set(value){
        this.$emit('update:indicatorObject',value);
      }
    }
  }

}
</script>

<style scoped>
input[type="text"],
textarea {
  width: 100%;
  padding: 10px;
  margin-top: 5px;
  margin-bottom: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
  box-sizing: border-box;
}
.indicator-container {
  padding: 10px;
}
</style>