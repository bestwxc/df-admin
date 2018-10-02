import request from '@/utils/request'

let basePath = '/api/admin'
/**
 * 查询treeNode
 * @param {参数}} data
 */
export async function listTreeNode (data) {
  return request({
    url: basePath + '/tree/list',
    method: 'post',
    data
  })
}

export async function addTreeNode (data) {
  return request({
    url: basePath + '/tree/add',
    method: 'post',
    data
  })
}

export async function updateTreeNode (data) {
  return request({
    url: basePath + '/tree/update',
    method: 'post',
    data
  })
}

export async function deleteTreeNode (data) {
  return request({
    url: basePath + '/tree/delete',
    method: 'post',
    data
  })
}
