import { Message } from 'element-ui'

/**
 * 提示
 * @param {提示消息} message
 * @param {提示类型} type
 * @param {停留时间} time
 */
const iMsg = function (message, type, time) {
  if (!message) {
    message = '出错了！！！'
  }
  if (!type) {
    type = 'error'
  }
  if (!time) {
    time = 5 * 1000
  }
  Message({
    message: message,
    type: type,
    duration: time
  })
}

export default {
  iMsg
}
