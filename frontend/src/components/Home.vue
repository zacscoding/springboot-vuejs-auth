<template>
  <div>
    <h2>Home</h2>
    <div> {{ greeting }}</div>
  </div>

</template>

<script>
  import {AXIOS} from "../common/http-common";

  export default {
    data() {
      return {
        greeting: ''
      }
    },
    created() {
      AXIOS.get('/home')
      .then((result) => this.greeting = result.data);
    }
  }
</script>

<style scoped>

</style>
