import Vue from 'vue'
import Router from 'vue-router'
import Layout from '@/components/layout/Layout'

Vue.use(Router)

// 异步引入页面
const page404 = r => require.ensure([], () => r(require('@/views/404')), 'page404')
const login = r => require.ensure([], () => r(require('@/views/login')), 'login')
const dashboard = r => require.ensure([], () => r(require('@/views/dashboard/index')), 'dashboard')
const treeNode = r => require.ensure([], () => r(require('@/views/base/treeNode/index')), 'treeNode')
const division = r => require.ensure([], () => r(require('@/views/base/division/index')), 'division')

const baseRouteMap = [
  {path: '/login', component: login, hidden: true},
  {path: '/404', component: page404, hidden: true},
  {
    path: '',
    component: Layout,
    redirect: 'dashboard',
    children: [{
      // name: 'Dashboard',
      path: 'dashboard',
      component: dashboard,
      meta: {
        title: 'DashBoard',
        icon: 'dashboard'
      }
    }]
  },
  {
    path: '/system',
    name: '系统设置',
    component: Layout,
    meta: {
      title: '系统设置',
      icon: 'fa fa-home fa-lg'
    },
    children: [{
      path: 'tree',
      name: '基础设置',
      component: treeNode,
      meta: {
        title: '基础设置',
        icon: 'example'
      }
    }, {
      path: 'division',
      name: '行政区域',
      component: division,
      meta: {
        title: '行政区域',
        icon: 'example'
      }
    }]
  }
]

const asyncRouteMap = [
]

export {
  baseRouteMap,
  asyncRouteMap
}

export default new Router({
  scrollBehavior: () => ({ y: 0 }),
  routes: baseRouteMap
})
