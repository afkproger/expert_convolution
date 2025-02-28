<template>
  <h3>Шкала оценок
    <span class="info-icon" @click="showTooltip = !showTooltip">?</span>
    <span v-if="showTooltip" class="tooltip">
            Введите лингвистическую оценку (например: низкое, высокое, большое) и вес от 0.0 до 1.0
    </span>
  </h3>
  <div v-for="(scaleItem, index) in taskScale" :key="index" class="task-item">
    <input type="text" v-model="scaleItem.grade" placeholder="Лингвистические оценка" required />
    <input
        type="text"
        v-model="scaleItem.weight"
        placeholder="Вес (от 0.0 до 1.0)"
        required
    />
    <button type="button" class="submit-button exit" @click="removeScaleItem(index)">Удалить</button>
  </div>
  <button type="button" class="submit-button" @click="addScaleItem">Добавить значение шкалы</button>
</template>

<script>
export default {
  props:{
    scaleObject:{
      type: Array,
      required: true
    }
  },
  data(){
    return{
      showTooltip:false,
    }
  },
  computed:{
    taskScale:{
      get(){
        return this.scaleObject;
      },
      set(val){
        this.$emit('update:scaleObject',val)
      }
    }
  },
  methods:{
    addScaleItem(){
      this.taskScale.push({ grade: '', weight: null });
    },
    removeScaleItem(index){
      this.taskScale.splice(index,1)
    }
  }
}
</script>

<style scoped>


</style>