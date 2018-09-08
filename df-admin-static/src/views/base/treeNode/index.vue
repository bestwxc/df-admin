<template>
  <div id="app-container">
    <tree-table :data="data" :expand-all="expandAll" :columns="columns" border />
  </div>
</template>
<script>
import TreeTable from '@/components/table/TreeTable'
import { listTreeNode } from '@/api/admin/treeNode'
export default {
  name: 'tree-node',
  components: { TreeTable },
  data () {
    return {
      columns: [
        {
          text: 'ID',
          value: 'id'
        },
        {
          text: '值',
          value: 'nodeValue'
        },
        {
          text: '名称',
          value: 'nodeName'
        },
        {
          text: '类型',
          value: 'treeNodePath'
        },
        {
          text: '状态',
          value: 'flag'
        },
        {
          text: '排序值',
          value: 'orderNum'
        }
      ],
      data: [],
      expandAll: false
    }
  },
  methods: {
    async getNode (parentId) {
      return listTreeNode({
        parentId
      })
    },
    async getRoot () {
      const result = await this.getNode()
      const rootNode = result[0]
      this.data = rootNode
      let children = await this.getNode(rootNode.id)
      rootNode.children = children
      this.data = Object.assign({}, rootNode)
    }
  },
  created () {
    this.getRoot()
  }
}
</script>
