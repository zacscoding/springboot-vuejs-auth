<template>
  <div>
    <h2>Me</h2>
    <div>
      <label>User Info:</label>
      <pre>{{email}}</pre>
    </div>
    <div>
      <label>Roles : </label>
      <pre>{{roles}}</pre>
    </div>
  </div>

</template>

<script>
  import {AXIOS} from "../common/http-common";

  export default {
    data() {
      return {
        email: null,
        roles: null
      }
    },
    created() {
      AXIOS.get('/me')
      .then(({data}) => {
        this.email = data.email;
        this.roles = data.roles.join(',');
      })
      .catch((err) => {
        console.error("Failed to get me", err);
      });
    }
  }
</script>

<style scoped>

</style>
