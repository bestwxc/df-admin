import request from '@/utils/request'

let basePath = '/api/admin'

/**
 * 登陆
 * @param {参数}} data
 */
export async function userLogin (data) {
  return request({
    url: basePath + '/user/login',
    method: 'post',
    data
  })
}

/**
 * 登出
 */
export async function userLogout (data) {
  return request({
    url: basePath + '/user/logout',
    method: 'post',
    data
  })
}

/**
 * 修改密码
 */
export async function userPasswd (data) {
  return request({
    url: basePath + '/user/passwd',
    method: 'post',
    data
  })
}

/**
 * 重置密码
 */
export async function userResetPass (data) {
  return request({
    url: basePath + '/user/resetpass',
    method: 'post',
    data
  })
}
