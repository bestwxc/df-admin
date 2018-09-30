<template>
<div>
  <div class="filter-container">
    <!--
    <el-input placeholder="上级区域代码" v-model="queryParam.parentDivisionCode" class="filter-item" @keyup.enter.native="handleFilter" />
    -->
    <v-for v-for="(column) in columns" :key="column.value">
      <el-input v-if="column.filter" :placeholder="column.text" v-model="queryParam[column.value]" class="filter-item" @keyup.enter.native="handleFilter" clearable></el-input>
    </v-for>
    <el-button class="filter-btn" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
  </div>
  <div class="btn-container">
    <!--<el-button class="filter-item" type="success" icon="el-icon-refresh" @click="handleRefresh">刷新节点</el-button>-->
    <el-button v-if="enableAdd" class="filter-item" type="primary" icon="el-icon-plus" @click="handleAdd">新增</el-button>
    <el-button v-if="enableUpdate" class="filter-item" type="warning" icon="el-icon-edit" @click="handleUpdate">修改</el-button>
    <el-button v-if="enableDelete" class="filter-item" type="danger" icon="el-icon-delete" @click="handleDelete">删除</el-button>
  </div>
  <el-table
    :data="tableData"
    border
    highlight-current-row
    @current-change="handleCurrentChange"
    style="width: 100%">
    <el-table-column v-for="(column) in columns" v-if="!column.hide"
      :key="column.value"
      :prop="column.value"
      :label="column.text"
      :width="column.width"
      >
    </el-table-column>
  </el-table>
  <el-pagination
    v-if="supportPage"
    @size-change="handlePageSizeChange"
    @current-change="handlePageNoChange"
    :current-page="pageNo"
    :page-sizes="[20, 40, 100, 200, 200, 400]"
    :page-size="20"
    layout="total, sizes, prev, pager, next, jumper"
    :total="total">
  </el-pagination>
  <el-dialog :title="editFormProp[editType].title" :visible.sync="showEditForm">
      <el-form ref="dataForm" :rules="rules" :model="formData" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <v-for v-for="(column) in columns" :key="column.value">
          <el-form-item v-if="judgeHide(column, editType)" :label="column.text" :prop="column.value">
            <el-input v-model="formData[column.value]" :disabled="judgeDisabled(column, editType)" clearable></el-input>
          </el-form-item>
        </v-for>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="showEditForm = false">取消</el-button>
        <el-button v-if="editType=='add'" type="primary" @click="addData">新增</el-button>
        <el-button v-else type="primary" @click="updataData">修改</el-button>
      </div>
    </el-dialog>
</div>
</template>
<script>
import request from '@/utils/request'
import layer from '@/utils/layer'
export default {
  name: 'BaseTable',
  props: {
    listUrl: {
      type: String,
      required: true
    },
    addUrl: {
      type: String
    },
    updateUrl: {
      type: String
    },
    deleteUrl: {
      type: String
    },
    columns: {
      type: Array,
      required: true
    },
    supportPage: {
      type: Boolean,
      default: false
    },
    filterField: {
      type: Array,
      default: () => []
    },
    enableAdd: {
      type: Boolean,
      default: true
    },
    enableDelete: {
      type: Boolean,
      default: true
    },
    enableUpdate: {
      type: Boolean,
      default: true
    }
  },
  data () {
    return {
      queryParam: {
      },
      tableData: [],
      total: 0,
      pageNo: 1,
      pageSize: 20,
      currentRow: {},
      showEditForm: false,
      editType: 'add',
      formData: {},
      editFormProp: {
        add: {
          title: '新增'
        },
        update: {
          title: '修改'
        }
      },
      rules: {}
    }
  },
  computed: {
  },
  methods: {
    handleCurrentChange (currentRow, oldCurrentRow) {
      this.currentRow = currentRow
    },
    async handleFilter () {
      this.getList()
    },
    async getList () {
      this.queryParam.flag = 0
      if (this.supportPage) {
        this.queryParam.pageNo = this.pageNo
        this.queryParam.pageSize = this.pageSize
      }
      console.info(this.queryParam)
      request({
        url: this.listUrl,
        method: 'post',
        data: this.queryParam
      }).then(data => {
        let result = data.result
        if (this.supportPage) {
          let tempTotal = 0
          if (data.resultType === 'list') {
            if (data.page) {
              tempTotal = data.total
            } else {
              tempTotal = result.length || 0
            }
          }
          this.total = tempTotal
        }
        this.tableData = result
      })
    },
    judgeHide (column, editType) {
      if (editType === 'add') {
        if (column.value === 'id') {
          return false
        }
        if (column.hideAdd) {
          return false
        }
      } else if (editType === 'update') {
        if (column.hideUpdate) {
          return false
        }
      }
      return true
    },
    judgeDisabled (column, editType) {
      if (editType === 'add') {
        return column.disableAdd
      } else if (editType === 'update') {
        return column.disableUpdate
      }
      return false
    },
    defaultEditForm () {
      let form = {}
      this.columns.forEach(column => {
        const value = column.value
        const defaultValue = column.defaultValue
        form[value] = defaultValue
      })
      return form
    },
    judgeSelectOne (row) {
      if (row && row.id) {
        return true
      }
      return false
    },
    handleAdd () {
      const temp = Object.assign({}, this.defaultEditForm())
      this.formData = temp
      this.editType = 'add'
      this.showEditForm = true
    },
    handleUpdate () {
      if (!this.judgeSelectOne(this.currentRow)) {
        layer.iMsg('请选择待操作的数据', 'error')
        return
      }
      this.formData = Object.assign({}, this.defaultEditForm())
      this.columns.forEach(column => {
        this.formData[column.value] = this.currentRow[column.value]
      })
      this.editType = 'update'
      this.showEditForm = true
    },
    handleDelete () {
      if (!this.judgeSelectOne(this.currentRow)) {
        layer.iMsg('请选择待操作的数据', 'error')
        return
      }
      request({
        url: this.deleteUrl,
        method: 'post',
        data: { id: this.currentRow.id }
      }).then(result => {
        layer.iMsg('删除成功', 'success', 5000)
        this.getList()
      })
    },
    addData () {
      request({
        url: this.addUrl,
        method: 'post',
        data: this.formData
      }).then(result => {
        this.getList()
        layer.iMsg('添加成功', 'success', 5000)
        this.showEditForm = false
      })
    },
    updataData () {
      request({
        url: this.updateUrl,
        method: 'post',
        data: this.formData
      }).then(result => {
        this.getList()
        layer.iMsg('修改成功', 'success', 5000)
        this.showEditForm = false
      })
    },
    handlePageSizeChange (pageSize) {
      if (this.pageSize === pageSize) {
        return
      }
      this.pageSize = pageSize
      this.getList()
    },
    handlePageNoChange (pageNo) {
      if (this.pageNo === pageNo) {
        return
      }
      this.pageNo = pageNo
      this.getList()
    }
  },
  created () {
    this.getList()
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
