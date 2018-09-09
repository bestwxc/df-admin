const page404 = r => require.ensure([], () => r(require('@/views/404')), 'page404')
const login = r => require.ensure([], () => r(require('@/views/login')), 'login')
const dashboard = r => require.ensure([], () => r(require('@/views/dashboard/index')), 'dashboard')
const treeNode = r => require.ensure([], () => r(require('@/views/base/treeNode/index')), 'treeNode')
const division = r => require.ensure([], () => r(require('@/views/base/division/index')), 'division')

export default {
  page404,
  login,
  dashboard,
  treeNode,
  division
}
