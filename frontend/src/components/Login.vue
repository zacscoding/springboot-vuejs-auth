<template>
  <div>
    <h2>Login</h2>
    <form @submit.prevent="onSubmit(email, password)">
      <input type="text" v-model="email" placeholder="Email address">
      <input type="password" v-model="password" placeholder="Password">
      <input type="submit" value="Login">
    </form>
    <p><i></i></p>
  </div>

</template>

<script>
  export default {
    data() {
      return {
        email   : 'user@email.com',
        password: 'user',
        msg     : ''
      }
    },
    methods: {
      onSubmit(email, password) {
        this.$store.dispatch('LOGIN', {email, password})
        .then(() => this.redirect())
        .catch(({message}) => this.msg = message);
      },
      redirect() {
        console.log('redirect is called', window.location);
        // TODO :: check returnPath
        const {search} = window.location;
        const tokens = search.replace(/^\?/, '').split('&')
        let {returnPath} = tokens.reduce((qs, tkn) => {
          const pair = tkn.split('=')
          qs[pair[0]] = decodeURIComponent(pair[1])
          return qs
        }, {});

        if (!returnPath) {
          returnPath = '/';
        }

        // 리다이렉트 처리
        this.$router.push(returnPath)
      }
    }
  }
</script>

<style scoped>

</style>
