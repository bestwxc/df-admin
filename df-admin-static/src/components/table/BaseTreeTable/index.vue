<template>
  <div class="app-container">
    <div class="filter-container">
      <span v-for="(column) in columns" :key="column.value">
        <el-select v-if="column.filter && column.type == 'select'" v-model="queryParam[column.value]" clearable placeholder="请选择" @keyup.enter.native="handleFilter">
          <el-option
            v-for="item in tree[column.childDictType]"
            :key="item.nodeValue"
            :label="item.nodeName"
            :value="item.nodeValue">
          </el-option>
        </el-select>
        <el-input v-else-if="column.filter" :placeholder="column.text" v-model="queryParam[column.value]" class="filter-item" @keyup.enter.native="handleFilter" clearable></el-input>
      </span>
      <el-button class="filter-btn" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
    </div>
    <div class="btn-container">
      <el-button class="filter-item" type="success" icon="el-icon-refresh" @click="handleRefresh">刷新节点</el-button>
      <el-button v-if="enableAdd" class="filter-item" type="primary" icon="el-icon-plus" @click="handleAdd">新增</el-button>
      <el-button v-if="enableUpdate" class="filter-item" type="warning" icon="el-icon-edit" @click="handleUpdate">修改</el-button>
      <el-button v-if="enableDelete" class="filter-item" type="danger" icon="el-icon-delete" @click="handleDelete">删除</el-button>
    </div>
    <tree-table :data="data" :expand-all="expandAll" :columns="columns" :load-func="loadFunc" highlight-current-row :handle-current-change="handleCurrentChange" border>
    </tree-table>
    <el-dialog :title="editFormProp[editType].title" :visible.sync="showEditForm">
      <el-form ref="dataForm" :rules="rules" :model="formData" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <span v-for="(column) in columns" :key="column.value">
          <el-form-item v-if="judgeHide(column, editType)" :label="column.text" :prop="column.value">
            <el-select v-if="column.type == 'select'" v-model="formData[column.value]" clearable placeholder="请选择">
              <el-option
                v-for="item in tree[column.childDictType]"
                :key="item.nodeValue"
                :label="item.nodeName"
                :value="item.nodeValue">
              </el-option>
            </el-select>
            <el-input v-else v-model="formData[column.value]" :disabled="judgeDisabled(column, editType)" clearable></el-input>
          </el-form-item>
        </span>
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
import Vue from 'vue'
import TreeTable from '@/components/table/TreeTable'
import layer from '@/utils/layer'
import request from '@/utils/request'
import { mapGetters } from 'vuex'
export default {
  name: 'BaseTreeTable',
  components: { TreeTable },
  props: {
    relateKey: {
      type: String,
      required: true
    },
    relateParentKey: {
      type: String,
      required: true
    },
    rootKeyValue: {
      type: String,
      required: true
    },
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
    },
    addFunc: Function
  },
  data () {
    return {
      queryParam: {
      },
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
      canUpdateValue: true,
      rules: {},
      data: [],
      expandAll: false
    }
  },
  computed: {
    ...mapGetters({
      tree: 'tree'
    })
  },
  methods: {
    handleCurrentChange (currentRow, oldCurrentRow) {
      this.currentRow = currentRow
    },
    async loadFunc (record) {
      let param = {}
      param[this.relateParentKey] = record[this.relateKey]
      param.flag = 0
      let deltaList = await this.getList(param)
      Vue.set(record, '_loaded', true)
      return deltaList
    },
    async getRoot () {
      if (!this.queryParam[this.relateParentKey]) {
        this.queryParam[this.relateParentKey] = this.rootKeyValue
      }
      this.queryParam.flag = 0
      const result = await this.getList(this.queryParam)
      this.data = result
    },
    async handleFilter () {
      this.getRoot()
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
    judgeCanUpdateValue () {
      let flag = this.editType === 'update' && this.judegNoChild()
      this.canUpdateValue = flag
    },
    handleAdd () {
      if (!this.judgeSelectOne(this.currentRow)) {
        layer.iMsg('请选择待操作的数据', 'error')
        return
      }
      const temp = Object.assign({}, this.defaultEditForm())
      temp[this.relateParentKey] = this.currentRow[this.relateKey]
      const defaultFunc = function (temp, currentRow) {}
      const func = this.addFunc || defaultFunc
      const args = [temp, this.currentRow]
      func.apply(null, args)
      this.formData = temp
      this.editType = 'add'
      this.showEditForm = true
    },
    handleUpdate () {
      if (!this.judgeSelectOne(this.currentRow)) {
        layer.iMsg('请选择待操作的数据', 'error')
        return
      }
      if (this.currentRow[this.relateParentKey] === this.rootKeyValue) {
        layer.iMsg('不能修改根节点', 'error')
        return
      }
      this.judgeCanUpdateValue()
      this.formData = Object.assign({}, this.defaultEditForm())
      this.columns.forEach(column => {
        this.formData[column.value] = this.currentRow[column.value]
      })
      this.editType = 'update'
      this.showEditForm = true
    },
    handleRefresh () {
      if (!this.judgeSelectOne(this.currentRow)) {
        layer.iMsg('请选择待操作的数据', 'error')
        return
      }
      let param = {}
      param[this.relateParentKey] = this.currentRow[this.relateKey]
      param.flag = 0
      this.getList(param).then(result => {
        Vue.set(this.currentRow, 'children', result)
        layer.iMsg('刷新成功', 'success')
      })
    },
    handleDelete () {
      if (!this.judgeSelectOne(this.currentRow)) {
        layer.iMsg('请选择待操作的数据', 'error')
        return
      }
      if (this.currentRow[this.relateParentKey] === this.rootKeyValue) {
        layer.iMsg('不能删除根节点', 'error')
        return
      }
      if (!this.judegNoChild()) {
        layer.iMsg('节点未展开或节点存在下级元素，不能删除！', 'error')
        return
      }
      request({
        url: this.deleteUrl,
        method: 'post',
        data: { id: this.currentRow.id }
      }).then(result => {
        let temp = this.currentRow.parent.children
        for (let i = 0; i < temp.length; i++) {
          let item = temp[i]
          if (item.id === this.currentRow.id) {
            Vue.set(this.currentRow.parent, 'children', Array.concat(temp.slice(0, i), temp.slice(i + 1, temp.length)))
            break
          }
        }
        console.log(temp)
        layer.iMsg('删除成功', 'success', 3000)
      })
    },
    addData () {
      request({
        url: this.addUrl,
        method: 'post',
        data: this.formData
      }).then(data => {
        let result = this.columnFormatter(data.result)
        if (!this.currentRow.children) {
          Vue.set(this.currentRow, 'children', [])
        }
        this.currentRow.children.push(result)
        layer.iMsg('添加成功', 'success', 3000)
        this.showEditForm = false
      })
    },
    updataData () {
      request({
        url: this.updateUrl,
        method: 'post',
        data: this.formData
      }).then(data => {
        let result = this.columnFormatter(data.result)
        this.columns.forEach(column => {
          this.currentRow[column.value] = result[column.value]
        })
        layer.iMsg('修改成功', 'success', 3000)
        this.showEditForm = false
      })
    },
    async getList (param) {
      let data = await request({
        url: this.listUrl,
        method: 'post',
        data: param
      })
      let result = data.result
      if (result && result.length && result.length > 0) {
        result.forEach(item => this.columnFormatter(item))
      }
      return result
    },
    columnFormatter (row) {
      this.columns.forEach(column => {
        if (column.type === 'select') {
          if (column.dictType === 'tree') {
            let dictList = this.$store.getters.tree[column.childDictType]
            let displayName = dictList.filter(dict => row[column.value] + '' === dict.nodeValue + '').map(item => item.nodeName)[0]
            row[column.displayValue] = displayName
          }
        }
      })
      return row
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
