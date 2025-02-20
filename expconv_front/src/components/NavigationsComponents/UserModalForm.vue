<template>
  <h1> Hello World </h1>
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
    closeModal(){
      this.$emit("closeModal" ,false);
    }
  }
}
</script>