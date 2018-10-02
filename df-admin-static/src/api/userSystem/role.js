import request from '@/utils/request'

let basePath = '/api/admin'
/**
 * 查询Role
 * @param {参数}} data
 */
export async function listRole (data) {
  return request({
    url: basePath + '/role/list',
    method: 'post',
    data
  })
}

export async function addRole (data) {
  return request({
    url: basePath + '/role/add',
    method: 'post',
    data
  })
}

export async function updateRole (data) {
  return request({
    url: basePath + '/role/update',
    method: 'post',
    data
  })
}

export async function deleteRole (data) {
  return request({
    url: basePath + '/role/delete',
    method: 'post',
    data
  })
}
