import Vue from 'vue'
import App from './App.vue'
// 引入路由
import Router from 'vue-router'

import './plugins/element'
import {routes} from "./route/route";

Vue.config.productionTip = false
Vue.use(Router)
//实例化Router
const router = new Router({
  routes,//实际是个数组
  mode: 'history'
});

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
