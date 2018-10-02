import request from '@/utils/request'

let basePath = '/api/admin'
/**
 * 查询Resource
 * @param {参数}} data
 */
export async function listResource (data) {
  return request({
    url: basePath + '/resource/list',
    method: 'post',
    data
  })
}

export async function addResource (data) {
  return request({
    url: basePath + '/resource/add',
    method: 'post',
    data
  })
}

export async function updateResource (data) {
  return request({
    url: basePath + '/resource/update',
    method: 'post',
    data
  })
}

export async function deleteResource (data) {
  return request({
    url: basePath + '/resource/delete',
    method: 'post',
    data
  })
}
