import Vue from 'vue'
import Router from 'vue-router'
import Layout from '@/components/layout/Layout'
import pages from './pages'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css'// progress bar style
import { isLogin } from '@/utils/auth'

Vue.use(Router)

const baseRouteMap = [
  {path: '/login', component: pages['Login'], hidden: true},
  {
    path: '',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '401',
        component: pages['Page401']
      },
      {
        path: '404',
        component: pages['Page404']
      }
    ]
  },
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

const whiteList = [
  '/login'
]

const router = new Router({
  scrollBehavior: () => ({ y: 0 }),
  routes: baseRouteMap
})
router.beforeEach((to, from, next) => {
  NProgress.start()
  console.log('beforeEach')
  if (isLogin()) {
    if (to.path === '/login') {
      next({ path: '/' })
      NProgress.done()
    } else {
      next()
    }
  } else {
    if (whiteList.indexOf(to.path) !== -1) {
      next()
    } else {
      next(`/login?redirect=${to.path}`) // 否则全部重定向到登录页
      NProgress.done() // if current page is login will not trigger afterEach hook, so manually handle it
    }
  }
})
router.afterEach((to, from) => {
  console.log('afterEach')
  NProgress.done()
})
export default router
