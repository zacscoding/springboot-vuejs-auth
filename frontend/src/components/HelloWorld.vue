<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <h2>Essential Links</h2>
    <ul>
      <li>
        <a
          href="https://vuejs.org"
          target="_blank"
        >
          Core Docs
        </a>
      </li>
      <li>
        <a
          href="https://forum.vuejs.org"
          target="_blank"
        >
          Forum
        </a>
      </li>
      <li>
        <a
          href="https://chat.vuejs.org"
          target="_blank"
        >
          Community Chat
        </a>
      </li>
      <li>
        <a
          href="https://twitter.com/vuejs"
          target="_blank"
        >
          Twitter
        </a>
      </li>
      <br>
      <li>
        <a
          href="http://vuejs-templates.github.io/webpack/"
          target="_blank"
        >
          Docs for This Template
        </a>
      </li>
    </ul>
    <h2>Ecosystem</h2>
    <ul>
      <li>
        <a
          href="http://router.vuejs.org/"
          target="_blank"
        >
          vue-router
        </a>
      </li>
      <li>
        <a
          href="http://vuex.vuejs.org/"
          target="_blank"
        >
          vuex
        </a>
      </li>
      <li>
        <a
          href="http://vue-loader.vuejs.org/"
          target="_blank"
        >
          vue-loader
        </a>
      </li>
      <li>
        <a
          href="https://github.com/vuejs/awesome-vue"
          target="_blank"
        >
          awesome-vue
        </a>
      </li>
    </ul>
  </div>
</template>

<script>
  import {AXIOS} from "../common/http-common";
  import querystring from "querystring";

  export default {
    name: 'HelloWorld',
    data() {
      return {
        msg: 'Welcome to Your Vue.js App'
      }
    },
    created() {
      console.log('created..', AXIOS);
      const requestBody = {
        username  : 'user@email.com',
        password  : 'user',
        grant_type: 'password'
      };

      const config = {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        auth   : {
          username: 'myApp',
          password: 'pass'
        }
      };

      // https://github.com/axios/axios/issues/350
      AXIOS.post('/oauth/token', querystring.stringify(requestBody), config)
      .then((result) => {
        console.log('post result .. ', result)
      })
      .catch((err) => {
        console.error("post error", err);
      });

      /*AXIOS({
        method : 'post',
        url    : '/oauth/token',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        auth   : {
          username: 'myApp',
          password: 'pass'
        },
        data   : body
      }).then(result => console.log('post result .. ', result));*/

      /*AXIOS.get('/home').then(
        result => {
          console.log('result :: ', result);
          this.username = result.data
        }
      );*/
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }
</style>
