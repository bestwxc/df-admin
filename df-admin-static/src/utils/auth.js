import store from '@/store'
export function isLogin () {
  const user = store.state.user
  return !!user && !!user.userName
}
