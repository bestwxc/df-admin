import Vue from 'vue'
import Router from 'vue-router'
import Layout from '@/components/layout/Layout'
import pages from './pages'

Vue.use(Router)

const baseRouteMap = [
  {path: '/login', component: pages['login'], hidden: true},
  {path: '/404', component: pages['page404'], hidden: true},
  {
    path: '',
    component: Layout,
    redirect: 'dashboard',
    children: [{
      // name: 'Dashboard',
      path: 'dashboard',
      component: pages['dashboard'],
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
      component: pages['treeNode'],
      meta: {
        title: '基础设置',
        icon: 'example'
      }
    }, {
      path: 'division',
      name: '行政区域',
      component: pages['division'],
      meta: {
        title: '行政区域',
        icon: 'example'
      }
    }, {
      path: 'SystemMenu',
      name: '系统菜单',
      component: pages['SystemMenu'],
      meta: {
        title: '系统菜单',
        icon: 'example'
      }
    }]
  },
  {
    path: '/org',
    name: '用户、角色、资源、权限',
    component: Layout,
    meta: {
      title: '用户、角色、资源、权限',
      icon: 'example'
    }
  }
]

export default new Router({
  scrollBehavior: () => ({ y: 0 }),
  routes: baseRouteMap
})
