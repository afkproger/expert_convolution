<template>
  <div>
    <label for="taskName">Название опросника</label>
    <input
        type="text"
        id="taskName"
        v-model="taskInfo.title"
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

textarea {
  width: 100%;
  padding: 10px;
  margin-top: 5px;
  margin-bottom: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
  box-sizing: border-box;
}
</style>
