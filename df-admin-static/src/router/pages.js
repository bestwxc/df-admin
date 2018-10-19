const Page401 = r => require.ensure([], () => r(require('@/views/errorPage/401')), 'Page401')
const Page404 = r => require.ensure([], () => r(require('@/views/errorPage/404')), 'Page404')
const Login = r => require.ensure([], () => r(require('@/views/login')), 'Login')
const Dashboard = r => require.ensure([], () => r(require('@/views/dashboard/index')), 'Dashboard')
const TreeNode = r => require.ensure([], () => r(require('@/views/data/TreeNode/index')), 'TreeNode')
const Division = r => require.ensure([], () => r(require('@/views/data/Division/index')), 'Division')
const SystemMenu = r => require.ensure([], () => r(require('@/views/system/SystemMenu/index')), 'SystemMenu')
const Resource = r => require.ensure([], () => r(require('@/views/userSystem/Resource/index')), 'Resource')
const User = r => require.ensure([], () => r(require('@/views/userSystem/User/index')), 'User')
const Role = r => require.ensure([], () => r(require('@/views/userSystem/Role/index')), 'Role')
const Department = r => require.ensure([], () => r(require('@/views/userSystem/Department/index')), 'Department')
export default {
  Page401,
  Page404,
  Login,
  Dashboard,
  TreeNode,
  Division,
  SystemMenu,
  Resource,
  User,
  Role,
  Department
}
