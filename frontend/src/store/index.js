import Vue from 'vue'
import Vuex from 'vuex'
import querystring from "querystring";
import {AXIOS} from "../common/http-common";

Vue.use(Vuex);

const enhanceAccessToken = () => {
  const {accessToken} = localStorage;
  if (!accessToken) {
    return;
  }

  AXIOS.defaults.headers.common['Authorization'] = `Bearer ${accessToken}`;
};

enhanceAccessToken();

export default new Vuex.Store({
  state    : {
    accessToken: null
  },
  getters  : {
    isAuthenticated(state) {
      state.accessToken = state.accessToken || localStorage.accessToken;
      return state.accessToken
    }
  },
  mutations: {
    LOGIN(state, {access_token}) {
      console.log(state);
      state.accessToken = access_token;
      localStorage.accessToken = access_token;
    },
    LOGOUT(state) {
      state.accessToken = null;
      delete localStorage.accessToken;
    }
  },
  actions  : {
    LOGIN({commit}, {email, password}) {
      console.log('request login ', email, password);
      const requestBody = {
        username  : email,
        password  : password,
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

      return AXIOS.post('/oauth/token', querystring.stringify(requestBody),
        config)
      .then(({data}) => {
        console.log(data);
        commit('LOGIN', data);
        AXIOS.defaults.headers.common['Authorization'] = `Bearer ${data.access_token}`;
      })
      .catch((err) => {
        console.error("failed to access token", err);
      })
    },
    LOGOUT({commit}) {
      AXIOS.defaults.headers.common['Authorization'] = undefined;
      commit('LOGOUT');
    }
  }
})
