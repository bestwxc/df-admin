<template>
  <div id="app-container">
    <tree-table :data="data" :expand-all="expandAll" :columns="columns" :load-func="loadFunc" border />
  </div>
</template>
<script>
import Vue from 'vue'
import TreeTable from '@/components/table/TreeTable'
import { listTreeNode } from '@/api/admin/treeNode'
// import treeToArray from '@/components/table/TreeTable/eval'
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
    loadFunc (data, trIndex) {
      let result = data
      /**
      let record = data[trIndex]
      let deltaList = await this.getNode(record.parentId)
      if (deltaList && deltaList.length > 0) {
        let tempList = treeToArray(deltaList)
        result = Array.concat(data.slice(0, trIndex), tempList, data.slice(trIndex, trIndex))
      }
      */
      Vue.set(data[trIndex], '_loaded', true)
      return result
    },
    async getNode (parentId) {
      return listTreeNode({
        parentId
      })
    },
    async getRoot () {
      const result = await this.getNode()
      const rootNode = result[0]
      this.data = rootNode
    }
  },
  created () {
    this.getRoot()
  }
}
</script>
