import request from '@/utils/request'

let basePath = '/api/admin'

/**
 * 查询Division
 * @param {参数}} data
 */
export async function listDivision (data) {
  return request({
    url: basePath + '/division/list',
    method: 'post',
    data
  })
}
