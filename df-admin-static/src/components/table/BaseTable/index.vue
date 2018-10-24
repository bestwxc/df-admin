<template>
  <div class="app-container">
    <!-- 筛选区域 -->
    <div class="filter-container">
      <span v-for="(column) in columns" :key="column.value">
        <!-- 下拉选择 -->
        <span  v-if="column.filter && column.type == 'select'">
          <span v-if="column.range">
            <el-select v-model="queryParam[fromKey(column.value)]" clearable :placeholder="'from:' + column.text" @keyup.enter.native="handleFilter">
              <el-option v-for="item in tree[column.childDictType]" :key="item.nodeValue" :label="item.nodeName" :value="item.nodeValue">
              </el-option>
            </el-select>
            <el-select v-model="queryParam[toKey(column.value)]" clearable :placeholder="'to' + column.text" @keyup.enter.native="handleFilter">
              <el-option v-for="item in tree[column.childDictType]" :key="item.nodeValue" :label="item.nodeName" :value="item.nodeValue">
              </el-option>
            </el-select>
          </span>
          <span v-else>
            <el-select v-model="queryParam[column.value]" clearable :placeholder="column.text" @keyup.enter.native="handleFilter">
              <el-option v-for="item in tree[column.childDictType]" :key="item.nodeValue" :label="item.nodeName" :value="item.nodeValue">
              </el-option>
            </el-select>
          </span>
        </span>
        <!-- 日期选择 -->
        <span  v-else-if="column.filter && column.type == 'datetime'">
          <span v-if="column.range">
            <el-date-picker
              v-model="queryParam[fromKey(column.value + 'Value')]"
              :type="column.dateOptions.editFormate"
              :placeholder="'from:' + column.text"
              align="right"
              :picker-options="column.dateOptions.editFormate.pickerOptions">
            </el-date-picker>
            <el-date-picker
              v-model="queryParam[toKey(column.value + 'Value')]"
              :type="column.dateOptions.editFormate"
              :placeholder="'to:' + column.text"
              align="right"
              :picker-options="column.dateOptions.editFormate.pickerOptions">
            </el-date-picker>
          </span>
          <span v-else>
            <el-date-picker
              v-model="queryParam[column.value + 'Value']"
              :type="column.dateOptions.editFormate"
              :placeholder="column.text"
              align="right"
              :picker-options="column.dateOptions.editFormate.pickerOptions">
            </el-date-picker>
          </span>
        </span>
        <!-- textarea输入框，不允许筛选 -->
        <span v-else-if="column.filter && column.type == 'textarea'">
        </span>
        <!-- 文件上传，同样不允许预览 -->
        <span v-else-if="column.filter && column.type == 'upload'">
        </span>
        <!-- slider -->
        <span v-else-if="column.filter && column.type == 'slider'">
        </span>
        <!-- json -->
        <span v-else-if="column.filter && column.type == 'json'">
        </span>
        <!-- markdown -->
        <span v-else-if="column.filter && column.type == 'markdown'">
        </span>
        <!-- tinymce -->
        <span v-else-if="column.filter && column.type == 'tinymce'">
        </span>
        <!-- 普通input框 -->
        <span v-else-if="column.filter">
          <span v-if="column.range">
            <el-input
              :placeholder="'from:' + column.text"
              v-model="queryParam[fromKey(column.value)]"
              class="filter-item" clearable
              @keyup.enter.native="handleFilter">
            </el-input>
            <el-input
              :placeholder="'to:' + column.text"
              v-model="queryParam[toKey(column.value)]"
              class="filter-item" clearable
              @keyup.enter.native="handleFilter">
            </el-input>
          </span>
          <span v-else>
            <el-input
              :placeholder="column.text"
              v-model="queryParam[column.value]"
              class="filter-item" clearable
              @keyup.enter.native="handleFilter">
            </el-input>
          </span>
        </span>
      </span>
      <el-button class="filter-btn" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
    </div>
    <!-- 按钮区域 -->
    <div class="btn-container">
      <el-button v-if="isTreeTable()" class="filter-item" type="success" icon="el-icon-refresh" @click="handleRefresh">刷新节点</el-button>
      <el-button v-if="add.enabled" class="filter-item" type="primary" icon="el-icon-plus" @click="handleAdd">新增</el-button>
      <el-button v-if="update.enabled" class="filter-item" type="warning" icon="el-icon-edit" @click="handleUpdate">修改</el-button>
      <el-button v-if="del.enabled" class="filter-item" type="danger" icon="el-icon-delete" @click="handleDelete">删除</el-button>
      <el-button v-for="(extBtn) in extBtns" v-bind:key="extBtn.text" class="filter-item" :type="extBtn.type" :icon="extBtn.icon" @click="emitExtBtnEvent(extBtn)">{{extBtn.text}}</el-button>
    </div>
    <!-- 表格区域 -->
    <tree-table v-if="isTreeTable()" :data="data" :expand-all="treeTableConfig.expandAll" :columns="columns" :load-func="loadFunc" highlight-current-row :handle-current-change="handleCurrentChange" border>
    </tree-table>
    <el-table v-if="!isTreeTable()"
      :data="data"
      border
      highlight-current-row
      @current-change="handleCurrentChange"
      @sort-change="handleSortChange"
      style="width: 100%">
      <el-table-column v-for="(column) in columns" v-if="!column.hide"
        :key="column.value"
        :prop="column.value"
        :label="column.text"
        :width="column.width"
        :sortable="column.sort?'custom':false"
        >
        <template slot-scope="scope">
          {{ scope.row[column.displayValue] || scope.row[column.value] }}
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination v-if="!isTreeTable()"
      @size-change="handlePageSizeChange"
      @current-change="handlePageNoChange"
      :current-page="pageParam.pageNum"
      :page-sizes="[20, 40, 100, 200, 400, 1000]"
      :page-size="pageParam.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="pageParam.total">
    </el-pagination>
    <!-- 弹出框 -->
    <el-dialog :title="editFormProp[editType].title" :visible.sync="showEditForm">
      <el-form ref="baseTableDataForm" :rules="rules" :model="formData" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
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
            <el-date-picker v-else-if="column.type == 'datetime'"
              v-model="queryParam[column.value + 'Value']"
              :type="column.dateOptions.editFormate"
              :placeholder="column.text"
              align="right"
              :disabled="judgeDisabled(column, editType)"
              :picker-options="column.dateOptions.editFormate.pickerOptions">
            </el-date-picker>
            <el-input v-else-if="column.type == 'textarea'" type="textarea" v-model="formData[column.value]" :disabled="judgeDisabled(column, editType)" clearable></el-input>
            <el-slider v-else-if="column.type == 'slider'"
              v-model="value9"
              b-bind = column.sliderOptions>
            </el-slider>
            <df-editor :ref="'richTextEditor-' + column.value" v-else-if="column.type == 'json'" type="json" :editorOptions="column.editorOptions" :value="formData[column.value]"/>
            <df-editor :ref="'richTextEditor-' + column.value" v-else-if="column.type == 'markdown'" type="markdown" :editorOptions="column.editorOptions" :value="formData[column.value]"/>
            <df-editor :ref="'richTextEditor-' + column.value" v-else-if="column.type == 'tinymce'" type="tinymce" :editorOptions="column.editorOptions" :value="formData[column.value]"/>
            <el-input v-else type="text" v-model="formData[column.value]" :disabled="judgeDisabled(column, editType)" clearable></el-input>
          </el-form-item>
        </span>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="showEditForm = false">取消</el-button>
        <el-button v-if="editType=='add'" type="primary" @click="addData">新增</el-button>
        <el-button v-else type="primary" @click="updateData">修改</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import Vue from 'vue'
import { mapGetters } from 'vuex'
import layer from '@/utils/layer'
import dateUtils from '@/utils/dateUtils'
import stringUtils from '@/utils/stringUtils'
import request from '@/utils/request'
import TreeTable from '@/components/table/TreeTable'
import DfEditor from '@/components/DfEditor'
export default {
  name: 'BaseTable',
  components: {
    TreeTable,
    DfEditor
  },
  props: {
    tableType: {
      type: String,
      default: 'baseTable'
    },
    treeTableConfig: {
      type: Object,
      default: () => { }
    },
    columns: {
      type: Array,
      required: true
    },
    list: {
      type: Object,
      default: () => { }
    },
    add: {
      type: Object,
      default: () => { }
    },
    update: {
      type: Object,
      default: () => { }
    },
    del: {
      type: Object,
      default: () => { }
    },
    extBtns: {
      type: Array,
      default: () => []
    }
  },
  data () {
    return {
      queryParam: {
      },
      pageParam: {
        pageNum: 1,
        pageSize: 20,
        total: 0
      },
      data: [],
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
    ...mapGetters({
      tree: 'tree'
    })
  },
  methods: {
    fromKey (String) {
      return 'from' + stringUtils.capFirst(String)
    },
    toKey (String) {
      return 'to' + stringUtils.capFirst(String)
    },
    isTreeTable () {
      return this.tableType === 'treeTable'
    },
    handleCurrentChange (currentRow, oldCurrentRow) {
      this.currentRow = currentRow
    },
    handleSortChange (param) {
      if (param) {
        this.pageParam.sort = param.prop.replace(/([A-Z])/g, '_$1').toLowerCase() + (param.order === 'descending' ? ' desc' : ' asc')
      } else {
        this.pageParam.sort = ''
      }
      this.getAndSetData()
    },
    handlePageSizeChange (pageSize) {
      if (this.pageParam.pageSize === pageSize) {
        return
      }
      this.pageParam.pageSize = pageSize
      this.getAndSetData()
    },
    handlePageNoChange (pageNum) {
      if (this.pageParam.pageNum === pageNum) {
        return
      }
      this.pageParam.pageNum = pageNum
      this.getAndSetData()
    },
    async listData (param) {
      this.handlerDateTime(param, true)
      let data = await request({
        url: this.list.url,
        method: 'post',
        data: param
      })
      let result = data.result
      this.pageParam.total = data.total || (result ? data.result.length : 0)
      if (result && result.length && result.length > 0) {
        result.forEach(item => this.columnFormatter(item))
      }
      return result
    },
    handleFilter () {
      this.getAndSetData()
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
    handleAdd () {
      // 属性表必须选择一个元素作为父节点
      if (this.isTreeTable() && !this.judgeSelectOne()) {
        return
      }
      const temp = Object.assign({}, this.defaultEditForm())
      // 自动填充关联的上级属性
      if (this.isTreeTable()) {
        temp[this.treeTableConfig.relateParentKey] = this.currentRow[this.treeTableConfig.relateKey]
      }
      // 用于修改数据提供回调
      const defaultFunc = function (temp, currentRow) {}
      const func = this.add.preShowFormFunc || defaultFunc
      const args = [temp, this.currentRow]
      func.apply(null, args)
      this.formData = temp
      this.editType = 'add'
      this.showEditForm = true
    },
    handleRefresh () {
      if (!this.judgeSelectOne()) {
        return
      }
      let param = {}
      param[this.treeTableConfig.relateParentKey] = this.currentRow[this.treeTableConfig.relateKey]
      param.flag = 0
      this.listData(param).then(result => {
        Vue.set(this.currentRow, 'children', result)
        layer.iMsg('刷新成功', 'success')
      })
    },
    handleUpdate () {
      if (!this.judgeSelectOne()) {
        return
      }
      if (this.isTreeTable()) {
        if (this.currentRow[this.treeTableConfig.relateParentKey] === this.treeTableConfig.rootKeyValue) {
          layer.iMsg('不能修改根节点', 'error')
          return
        }
      }
      this.formData = Object.assign({}, this.defaultEditForm())
      this.columns.forEach(column => {
        this.formData[column.value] = this.currentRow[column.value]
      })
      this.editType = 'update'
      this.showEditForm = true
    },
    handleDelete () {
      if (!this.judgeSelectOne()) {
        return
      }
      if (this.isTreeTable()) {
        if (this.currentRow[this.treeTableConfig.relateParentKey] === this.treeTableConfig.rootKeyValue) {
          layer.iMsg('不能删除根节点', 'error')
          return
        }
        if (!this.judegNoChild()) {
          return
        }
      }
      request({
        url: this.del.url,
        method: 'post',
        data: { id: this.currentRow.id }
      }).then(data => {
        if (this.isTreeTable()) {
          let temp = this.currentRow.parent.children
          for (let i = 0; i < temp.length; i++) {
            let item = temp[i]
            if (item.id === this.currentRow.id) {
              Vue.set(this.currentRow.parent, 'children', Array.concat(temp.slice(0, i), temp.slice(i + 1, temp.length)))
              break
            }
          }
        } else {
          this.getAndSetData()
        }
        layer.iMsg('删除成功', 'success', 3000)
      })
    },
    addData () {
      this.handlerDateTime(this.formData, false)
      request({
        url: this.add.url,
        method: 'post',
        data: this.formData
      }).then(data => {
        if (this.isTreeTable()) {
          let result = data.result || {}
          let child = this.columnFormatter(result)
          if (!this.currentRow.children) {
            Vue.set(this.currentRow, 'children', [])
          }
          this.currentRow.children.push(child)
        } else {
          this.getAndSetData()
        }
        layer.iMsg('添加成功', 'success', 3000)
        this.showEditForm = false
      })
    },
    updateData () {
      this.handlerDateTime(this.formData, false)
      request({
        url: this.update.url,
        method: 'post',
        data: this.formData
      }).then(data => {
        if (this.isTreeTable()) {
          let result = this.columnFormatter(data.result)
          this.columns.forEach(column => {
            this.currentRow[column.value] = result[column.value]
          })
        } else {
          this.getAndSetData()
        }
        layer.iMsg('修改成功', 'success', 3000)
        this.showEditForm = false
      })
    },
    judgeSelectOne () {
      let isSelectOne = this.currentRow && this.currentRow.id
      if (!isSelectOne) {
        layer.iMsg('请选择待操作的数据', 'error')
        return false
      }
      return true
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
    judegNoChild () {
      let loaded = this.currentRow._loaded || false
      let hasChildRen = this.currentRow.children && this.currentRow.children.length > 0
      let isNoChild = loaded && !hasChildRen
      if (!isNoChild) {
        layer.iMsg('节点未展开或节点存在下级元素，不能进行操作！', 'error')
        return false
      }
      return true
    },
    columnFormatter (row) {
      this.columns.forEach(column => {
        if (column.type === 'select') {
          if (column.dictType === 'tree') {
            let dictList = this.$store.getters.tree[column.childDictType] || []
            let displayName = dictList.filter(dict => row[column.value] + '' === dict.nodeValue + '').map(item => item.nodeName)[0]
            row[column.displayValue] = displayName
          }
        }
      })
      return row
    },
    handlerDateTime (param, enableRange) {
      this.columns.forEach(column => {
        if (column.type === 'datetime') {
          let dealKey = []
          if (enableRange && column.range) {
            dealKey.push('from' + stringUtils.capFirst(column.value))
            dealKey.push('to' + stringUtils.capFirst(column.value))
          } else {
            dealKey.push(column.value)
          }
          dealKey.forEach(key => {
            let value = param[key + 'Value']
            if (value) {
              param[key] = dateUtils.format(value, dateUtils.datetime)
            }
          })
        }
      })
    },
    emitExtBtnEvent (extBtn) {
      const event = extBtn.event
      const judgeSelectOne = extBtn.judgeSelectOne || false
      if (judgeSelectOne) {
        if (!this.judgeSelectOne(this.currentRow)) {
          layer.iMsg('请选择待操作的数据', 'error')
          return
        }
      }
      const judegNoChild = extBtn.judegNoChild || false
      if (judegNoChild) {
        if (!this.judegNoChild()) {
          return
        }
      }
      this.$emit(event, this.currentRow)
    },
    async loadFunc (record) {
      let param = {}
      param[this.treeTableConfig.relateParentKey] = record[this.treeTableConfig.relateKey]
      param.flag = 0
      let deltaList = await this.listData(param)
      Vue.set(record, '_loaded', true)
      return deltaList
    },
    async getAndSetData (resetSort) {
      if (this.isTreeTable() && !this.queryParam[this.treeTableConfig.relateParentKey]) {
        this.queryParam[this.treeTableConfig.relateParentKey] = this.treeTableConfig.rootKeyValue
      }
      if (!this.isTreeTable()) {
        this.queryParam.pageNum = this.pageParam.pageNum
        this.queryParam.pageSize = this.pageParam.pageSize
        this.queryParam.sort = this.pageParam.sort
      }
      this.queryParam.flag = 0
      const result = await this.listData(this.queryParam)
      this.data = result
    }
  },
  mounted () {
  },
  created () {
    this.getAndSetData()
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
