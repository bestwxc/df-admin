<template>
  <div id="app-container">
    <div class="filter-container">
      <el-input placeholder="字典类型" v-model="queryParam.treeNodePath" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
    </div>
    <tree-table :data="data" :expand-all="expandAll" :columns="columns" :load-func="loadFunc" highlight-current-row :handle-current-change="handleCurrentChange" border>
    </tree-table>
  </div>
</template>
<script>
import Vue from 'vue'
import TreeTable from '@/components/table/TreeTable'
import { listTreeNode } from '@/api/admin/treeNode'
export default {
  name: 'tree-node',
  components: { TreeTable },
  data () {
    return {
      queryParam: {
        treeNodePath: ''
      },
      currentRow: {},
      columns: [
        {
          text: 'ID',
          value: 'id',
          width: 240
        },
        {
          text: '值',
          value: 'nodeValue',
          width: 100
        },
        {
          text: '名称',
          value: 'nodeName',
          width: 100
        },
        {
          text: '类型',
          value: 'treeNodePath'
        },
        {
          text: '状态',
          value: 'flag',
          width: 40
        },
        {
          text: '排序值',
          value: 'orderNum',
          width: 40
        }
      ],
      data: [],
      expandAll: false
    }
  },
  methods: {
    handleCurrentChange (currentRow, oldCurrentRow) {
      console.log(currentRow, oldCurrentRow)
      this.currentRow = currentRow
    },
    async loadFunc (record) {
      let deltaList = await listTreeNode({ parentId: record.id })
      Vue.set(record, '_loaded', true)
      return deltaList
    },
    async getRoot () {
      let param = {}
      if (this.queryParam.treeNodePath && this.queryParam.treeNodePath.length > 0) {
        param.treeNodePath = this.queryParam.treeNodePath
      } else {
        param.parentId = 0
      }
      const result = await listTreeNode(param)
      this.data = result
    },
    async handleFilter () {
      this.getRoot()
    }
  },
  created () {
    this.getRoot()
  }
}
</script>
