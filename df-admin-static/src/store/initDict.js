import store from './index'
import { listTreeNode } from '@/api/data/treeNode'
let initTreeNodePaths = [
  'system.dicts.divisionType',
  'system.dicts.resourceType',
  'system.dicts.userState'
]
initTreeNodePaths.forEach(item => {
  listTreeNode({
    treeNodePath: item,
    flag: 0
  }).then(data => {
    let payload = {}
    payload.dictType = item
    payload.dictList = data.result
    store.dispatch('SaveTreeNode', payload)
  })
})
