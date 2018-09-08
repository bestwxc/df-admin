import axios from 'axios'
import { Message } from 'element-ui'
const service = axios.create({
  baseURL: process.env.BASE_API, // api 的 base_url
  timeout: 5000 // request timeout
})
service.interceptors.response.use(
  res => {
    const status = res.status
    if (status === 200) {
      const errorNo = res.data.errorNo
      if (errorNo === 0) {
        return res.data.result
      } else {
        Message({
          message: res.data.errorInfo + '[' + errorNo + ']',
          type: 'error',
          duration: 5 * 1000
        })
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
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)
export default service
