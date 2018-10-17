import { Message, MessageBox } from 'element-ui'

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
    time = 3 * 1000
  }
  Message({
    message: message,
    type: type,
    duration: time
  })
}

const iConfirm = function (message, confirmButtonCallBack, confirmButtonText, cancelButtonCallBack, cancelButtonText, title, type) {
  const showTitle = title || '提示'
  const showConfirmButtonText = confirmButtonText || '确定'
  const showCancelButtonText = cancelButtonText || '取消'
  const showType = type || 'warning'
  const defaultConfirmButtonFunc = () => {}
  const defaultCancelButtonFunc = () => {
    Message({
      type: 'info',
      message: '已取消'
    })
  }
  const confirmButtonFunc = confirmButtonCallBack || defaultConfirmButtonFunc
  MessageBox(message, showTitle, {
    confirmButtonText: showConfirmButtonText,
    cancelButtonText: showCancelButtonText,
    type: showType
  }).then(confirmButtonFunc).catch(defaultCancelButtonFunc)
}

export default {
  iMsg,
  iConfirm
}
