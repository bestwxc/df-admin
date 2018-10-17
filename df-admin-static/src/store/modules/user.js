import storeUtils from '@/utils/storeUtils'
import { userLogin, userLogout } from '@/api/userSystem/user'

const loginUserInfo = storeUtils.getCookie('loginUserInfo') || {}
const userName = loginUserInfo.userName || ''
const avatar = loginUserInfo.avatar || 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif'
const roles = loginUserInfo.roles || []
const user = {
  state: {
    userName: userName,
    avatar: avatar,
    roles: roles
  },
  mutations: {
    SET_USER_INFO: (state, userInfo) => {
      state.userName = userInfo.userName
      state.avatar = userInfo.headUrl || 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif'
      let temp = {}
      temp.userName = state.userName
      temp.avatar = state.avatar
      temp.roles = state.roles
      storeUtils.setCookie('loginUserInfo', temp)
    },
    DELETE_USER_INFO: (state) => {
      state.userName = ''
      state.avatar = 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif'
      state.roles = []
      storeUtils.removeCookie('loginUserInfo')
    }
  },
  actions: {
    Login ({commit}, userInfo) {
      const userName = userInfo.userName.trim()
      const password = userInfo.password.trim()
      const info = {
        userName,
        password
      }
      return new Promise((resolve, reject) => {
        userLogin(info).then(res => {
          const result = res.result || {}
          result.userName = userName
          commit('SET_USER_INFO', result)
          resolve(result)
        }).catch(error => {
          reject(error)
        })
      })
    },
    Logout ({commit}, payLoad) {
      return new Promise((resolve, reject) => {
        userLogout({}).then(res => {
          const result = res.result
          commit('DELETE_USER_INFO')
          resolve(result)
        }).then(error => {
          reject(error)
        })
      })
    }
  }
}
export default user
