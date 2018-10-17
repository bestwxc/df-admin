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
