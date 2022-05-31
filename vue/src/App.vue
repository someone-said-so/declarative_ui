<template>
  <div>{{count}}</div>
  <button @click="increment">+</button>
  <Child msg="from parent" @feedback="feedbackMethod" />
</template>

<script>
import { ref } from 'vue'
import Child from './components/Child.vue'

export default {
  setup(props) {
    const count = ref(0)
    const increment = () => count.value++
    const feedbackMethod = (v) => alert(v)
    return {
      count,
      increment,
      feedbackMethod,
    }
  },
  components: {
    Child
  },
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
