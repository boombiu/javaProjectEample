import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'

Vue.use(Router)


// 公共路由
export const constantRoutes = [
  {
    path: '/',
    component: () => import('@/components/loginsignup/login'),
    
  }
]




export default new Router({
  routes: constantRoutes
})
