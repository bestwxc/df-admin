import Vue from 'vue'
import Router from 'vue-router'
import Layout from '@/components/layout/Layout'

Vue.use(Router)

// 异步引入页面
const page404 = r => require.ensure([], () => r(require('@/views/404')), 'page404')
const login = r => require.ensure([], () => r(require('@/views/login')), 'login')
const dashboard = r => require.ensure([], () => r(require('@/views/dashboard/index')), 'dashboard')

const baseRouteMap = [
  {path: '/login', component: login, hidden: true},
  {path: '/404', component: page404, hidden: true},
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    hidden: true,
    children: [{
      path: 'dashboard',
      component: dashboard
    }]
  }
]

const asyncRouteMap = [
]

export default new Router({
  scrollBehavior: () => ({ y: 0 }),
  routes: baseRouteMap
})

export {
  baseRouteMap,
  asyncRouteMap
}
