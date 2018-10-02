import request from '@/utils/request'

let basePath = '/api/admin'
/**
 * 查询UserRole
 * @param {参数}} data
 */
export async function listUserRole (data) {
  return request({
    url: basePath + '/user/role/list',
    method: 'post',
    data
  })
}

export async function addUserRole (data) {
  return request({
    url: basePath + '/user/role/add',
    method: 'post',
    data
  })
}

export async function updateUserRole (data) {
  return request({
    url: basePath + '/user/role/update',
    method: 'post',
    data
  })
}

export async function deleteUserRole (data) {
  return request({
    url: basePath + '/user/role/delete',
    method: 'post',
    data
  })
}

export async function syncUserRole (data) {
  return request({
    url: basePath + '/user/role/sync',
    method: 'post',
    data
  })
}
