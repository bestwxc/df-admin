import axios from 'axios'
import { Message } from 'element-ui'
import layer from './layer'
import store from '@/store'
import router from '../router'
axios.defaults.withCredentials = true
const service = axios.create({
  baseURL: process.env.NODE_ENV === 'development' ? '' : process.env.BASE_API // api 的 base_url
  // timeout: 5000 // request timeout
})
service.interceptors.response.use(
  res => {
    const status = res.status
    if (status === 200) {
      const errorNo = res.data.errorNo
      if (errorNo === 0) {
        return res.data
      } else {
        Message({
          message: res.data.errorInfo + '[' + errorNo + ']',
          type: 'error',
          duration: 3 * 1000
        })
        if (errorNo === -999) {
          layer.iConfirm('登陆已失效，重新登陆？', () => {
            store.dispatch('Logout').then(() => {
              location.reload()
            })
          })
        }
        if (errorNo === -998) {
          router.replace('/401')
        }
        return Promise.reject(new Error())
      }
    } else {
      return Promise.reject(new Error('http请求错误,status:' + status))
    }
  },
  error => {
    console.log('err' + error)
    Message({
      message: error.message,
      type: 'error',
      duration: 3 * 1000
    })
    return Promise.reject(error)
  }
)
export default service
