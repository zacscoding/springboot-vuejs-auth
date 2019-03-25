import Vue from 'vue'
import Router from 'vue-router'
import Home from "../components/Home";
import Login from "../components/Login";
import store from "../store";
import Me from "../components/Me";

Vue.use(Router)

const requireAuth = () => (to, from, next) => {
  console.log('beforeEnter :: ', store.getters.isAuthenticated);
  if (store.getters.isAuthenticated) {
    return next();
  }
  next('/login?returnPath=me')
};

export default new Router({
  routes: [
    {
      path     : '/',
      name     : 'Home',
      component: Home
    },
    {
      path     : '/login',
      name     : 'Login',
      component: Login
    },
    {
      path       : '/me',
      name       : 'Me',
      component  : Me,
      beforeEnter: requireAuth()
    }
  ]
})
