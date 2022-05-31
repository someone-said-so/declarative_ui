<template>
  <div>{{ msg }}</div>
  <button @click="feedback">feedback</button>
</template>

<script>
export default {
  name: 'Child',
  props: {
    msg: String
  },
  methods: {
    feedback() {
      this.$emit("feedback", "toParent");
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
