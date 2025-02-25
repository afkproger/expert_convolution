<template>
  <div>
    <label for="taskName">Название опросника</label>
    <input
        type="text"
        id="taskName"
        v-model="taskInfo.name"
        required
        placeholder="Введите название опросника для отображения в списке заданий"
    />
    <label for="taskDescription">Описание опросника:</label>
    <textarea
        id="taskDescription"
        v-model="taskInfo.description"
        required
        placeholder="Введите общий вопрос для опросника"
    ></textarea>
  </div>
</template>

<script>
export default {
  props: {
    taskObject: {
      type: Object,
      required: true
    }
  },
  computed: {
    taskInfo: {
      get() {
        return this.taskObject;
      },
      set(newValue) {
        this.$emit("update:taskObject", newValue);
      }
    }
  }
}
</script>

<style scoped>
</style>
