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
