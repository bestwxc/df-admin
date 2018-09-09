<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input placeholder="上级区域代码" v-model="queryParam.parentDivisionCode" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-button class="filter-btn" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
    </div>
    <div class="btn-container">
      <el-button class="filter-item" type="success" icon="el-icon-refresh" @click="handleRefresh">刷新节点</el-button>
      <!-- <el-button class="filter-item" type="primary" icon="el-icon-plus" @click="handleAdd">新增</el-button>
      <el-button class="filter-item" type="warning" icon="el-icon-edit" @click="handleUpdate">修改</el-button>
      <el-button class="filter-item" type="danger" icon="el-icon-delete" @click="handleDelete">删除</el-button> -->
    </div>
    <tree-table :data="data" :expand-all="expandAll" :columns="columns" :load-func="loadFunc" highlight-current-row :handle-current-change="handleCurrentChange" border>
    </tree-table>
  </div>
</template>
<script>
import Vue from 'vue'
import TreeTable from '@/components/table/TreeTable'
import { listDivision } from '@/api/admin/division'
import layer from '@/utils/layer'
export default {
  name: 'tree-node',
  components: { TreeTable },
  data () {
    return {
      queryParam: {
        parentDivisionCode: ''
      },
      currentRow: {},
      columns: [
        { text: 'ID', value: 'id', width: 240 },
        { text: '区域代码', value: 'divisionCode', width: 120 },
        { text: '区域名称', value: 'divisionName', width: 150 },
        { text: '上级ID', value: 'parentId', width: 120 },
        { text: '上级区域代码', value: 'parentDivisionCode', width: 150 },
        { text: '区域级别', value: 'divisionLevel', width: 40 },
        { text: '区域级别调整', value: 'levelAdjust', width: 40 },
        { text: '城乡区域类型', value: 'divisionType', width: 60 },
        { text: '状态', value: 'flag', width: 40 },
        { text: '排序值', value: 'orderNum', width: 40 }
      ],
      data: [],
      expandAll: false
    }
  },
  methods: {
    handleCurrentChange (currentRow, oldCurrentRow) {
      this.currentRow = currentRow
    },
    async loadFunc (record) {
      let deltaList = await listDivision({ flag: 0, parentId: record.id })
      Vue.set(record, '_loaded', true)
      return deltaList
    },
    async getRoot () {
      let param = {
        flag: 0
      }
      if (this.queryParam.parentDivisionCode && this.queryParam.parentDivisionCode.length > 0) {
        param.parentDivisionCode = this.queryParam.parentDivisionCode
      } else {
        param.parentDivisionCode = '86'
      }
      const result = await listDivision(param)
      this.data = result
    },
    async handleFilter () {
      this.getRoot()
    },
    judegNoChild () {
      let loaded = this.currentRow._loaded || false
      let hasChildRen = this.currentRow.children && this.currentRow.children.length > 0
      return loaded && !hasChildRen
    },
    judgeSelectOne (row) {
      if (row && row.id) {
        return true
      }
      return false
    },
    handleRefresh () {
      if (!this.judgeSelectOne(this.currentRow)) {
        layer.iMsg('请选择待操作的数据', 'error')
        return
      }
      listDivision({
        flag: 0,
        parentId: this.currentRow.id
      }).then(result => {
        Vue.set(this.currentRow, 'children', result)
        layer.iMsg('刷新成功', 'success')
      })
    }
  },
  created () {
    this.getRoot()
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.app-container {
  .btn-container {
    padding: 10px 0 2px 0;
  }
  .filter-container {
    .filter-item {
      width: 200px;
    }
  }
}
</style>
