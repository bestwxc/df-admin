import store from '@/store'
export function isLogin () {
  console.log(store)
  const user = store.state.user
  return !!user && !!user.userName
}
