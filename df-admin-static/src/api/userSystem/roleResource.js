import request from '@/utils/request'

let basePath = '/api/admin'
/**
 * 查询RoleResource
 * @param {参数}} data
 */
export async function listRoleResource (data) {
  return request({
    url: basePath + '/role/resource/list',
    method: 'post',
    data
  })
}

export async function addRoleResource (data) {
  return request({
    url: basePath + '/role/resource/add',
    method: 'post',
    data
  })
}

export async function updateRoleResource (data) {
  return request({
    url: basePath + '/role/resource/update',
    method: 'post',
    data
  })
}

export async function deleteRoleResource (data) {
  return request({
    url: basePath + '/role/resource/delete',
    method: 'post',
    data
  })
}

export async function syncRoleResource (data) {
  return request({
    url: basePath + '/role/resource/sync',
    method: 'post',
    data
  })
}
