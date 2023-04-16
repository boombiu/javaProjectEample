import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/views/index'
/* Layout */
/*import Layout from '@/layout'*/

Vue.use(Router)


// 公共路由
export const constantRoutes = [
  {
    path: '/',
    name: 'main',
    component: () => import('@/views/index')
    
  },
  {
    path: '/login',
    component: () => import('@/components/loginsignup/login')
    
  }

]




export default new Router({
  mode: 'history', // 去掉url中的#
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})
