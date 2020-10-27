import login from '../components/login' //引入组件
import main_page from '../components/main_page'
export const routes = [{
  name: 'login',
  path: '/',
  component: login
}, {
  name: 'main_page',
  path: '/main_page',
  component: main_page
}];
