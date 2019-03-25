<template>
  <div>
    <h2>Home</h2>
    <div> {{ data }}</div>
  </div>

</template>

<script>
  import {AXIOS} from "../common/http-common";

  export default {
    data() {
      return {
        data: ''
      }
    },
    created() {
      AXIOS.get('/home')
      .then((result) => {
        this.data = result.data
      })
      .catch((err) => {
        console.error("Failed to get /home", err);
      })
    }
  }
</script>

<style scoped>

</style>
