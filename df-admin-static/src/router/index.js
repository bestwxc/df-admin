import Vue from 'vue'
import Router from 'vue-router'
import Layout from '@/components/layout/Layout'
import pages from './pages'

Vue.use(Router)

const baseRouteMap = [
  {path: '/login', component: pages['Login'], hidden: true},
  {path: '/404', component: pages['Page404'], hidden: true},
  {
    path: '',
    component: Layout,
    redirect: 'dashboard',
    children: [{
      // name: 'Dashboard',
      path: 'dashboard',
      component: pages['Dashboard'],
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
    path: '/data',
    name: '基础数据',
    component: Layout,
    meta: {
      title: '基础数据',
      icon: 'fa fa-home fa-lg'
    },
    children: [{
      path: 'tree',
      name: '基础设置',
      component: pages['TreeNode'],
      meta: {
        title: '基础设置',
        icon: 'example'
      }
    }, {
      path: 'division',
      name: '行政区域',
      component: pages['Division'],
      meta: {
        title: '行政区域',
        icon: 'example'
      }
    }]
  },
  {
    path: '/userSystem',
    name: '用户、角色、资源、权限',
    component: Layout,
    meta: {
      title: '用户、角色、资源、权限',
      icon: 'example'
    },
    children: [{
      path: 'user',
      name: '用户设置',
      component: pages['User'],
      meta: {
        title: '用户设置',
        icon: 'example'
      }
    }, {
      path: 'resource',
      name: '资源设置',
      component: pages['Resource'],
      meta: {
        title: '资源设置',
        icon: 'example'
      }
    }, {
      path: 'role',
      name: '角色设置',
      component: pages['Role'],
      meta: {
        title: '角色设置',
        icon: 'example'
      }
    }, {
      path: 'department',
      name: '部门设置',
      component: pages['Department'],
      meta: {
        title: '部门设置',
        icon: 'example'
      }
    }]
  }
]

export default new Router({
  scrollBehavior: () => ({ y: 0 }),
  routes: baseRouteMap
})
