import storeUtils from '@/utils/storeUtils'
import { userLogin, userLogout } from '@/api/userSystem/user'

const loginUserInfo = storeUtils.getCookie('loginUserInfo') || {}
const userId = loginUserInfo.userId || ''
const nickName = loginUserInfo.nickName || ''
const userName = loginUserInfo.userName || ''
const mobileNo = loginUserInfo.mobileNo || ''
const departmentCode = loginUserInfo.departmentCode || ''
const userState = loginUserInfo.userState || 0
const avatar = loginUserInfo.avatar || 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif'
const roles = loginUserInfo.roles || []
const user = {
  state: {
    userId: userId,
    userName: userName,
    nickName: nickName,
    mobileNo: mobileNo,
    userState: userState,
    departmentCode: departmentCode,
    avatar: avatar,
    roles: roles
  },
  mutations: {
    SET_USER_INFO: (state, userInfo) => {
      state.userId = userInfo.id
      state.userName = userInfo.userName
      state.nickName = userInfo.nickName
      state.mobileNo = userInfo.mobileNo
      state.avatar = userInfo.headUrl || 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif'
      state.userState = userInfo.userState
      state.departmentCode = userInfo.departmentCode
      storeUtils.setCookie('loginUserInfo', state)
    },
    DELETE_USER_INFO: (state) => {
      state.userId = ''
      state.nickName = ''
      state.mobileNo = ''
      state.userName = ''
      state.userState = ''
      state.departmentCode = ''
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
