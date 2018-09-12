<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input placeholder="字典类型" v-model="queryParam.treeNodePath" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-button class="filter-btn" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
    </div>
    <div class="btn-container">
      <el-button class="filter-item" type="success" icon="el-icon-refresh" @click="handleRefresh">刷新节点</el-button>
      <el-button class="filter-item" type="primary" icon="el-icon-plus" @click="handleAdd">新增</el-button>
      <el-button class="filter-item" type="warning" icon="el-icon-edit" @click="handleUpdate">修改</el-button>
      <el-button class="filter-item" type="danger" icon="el-icon-delete" @click="handleDelete">删除</el-button>
    </div>
    <tree-table :data="data" :expand-all="expandAll" :columns="columns" :load-func="loadFunc" highlight-current-row :handle-current-change="handleCurrentChange" border>
    </tree-table>
    <el-dialog :title="editFormProp[editType].title" :visible.sync="showEditForm">
      <el-form ref="dataForm" :rules="rules" :model="formData" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item v-if="editType=='update'" label="主键" prop="id">
          <el-input v-model="formData.id" disabled/>
        </el-form-item>
        <el-form-item label="字典值" prop="nodeValue">
          <el-input v-model="formData.nodeValue" :disabled="!canUpdateValue" />
        </el-form-item>
        <el-form-item label="字典名称" prop="nodeName">
          <el-input v-model="formData.nodeName" />
        </el-form-item>
        <el-form-item label="上级ID" prop="parentId">
          <el-input v-model="formData.parentId" disabled/>
        </el-form-item>
        <el-form-item label="类型" prop="treeNodePath">
          <el-input v-model="formData.treeNodePath" disabled/>
        </el-form-item>
        <el-form-item label="排序值" prop="orderNum">
          <el-input v-model="formData.orderNum" />
        </el-form-item>
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
import { listTreeNode, addTreeNode, deleteTreeNode, updateTreeNode } from '@/api/admin/treeNode'
import layer from '@/utils/layer'
const defaultEditForm = {
  id: '',
  nodeValue: '',
  nodeName: '',
  parentId: '',
  treeNodePath: '',
  flag: 0,
  orderNum: 0
}
export default {
  name: 'tree-node',
  components: { TreeTable },
  data () {
    return {
      queryParam: {
        treeNodePath: ''
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
      columns: [
        { text: '值', value: 'nodeValue', width: 240 },
        { text: 'ID', value: 'id', width: 100 },
        { text: '名称', value: 'nodeName', width: 100 },
        { text: '上级ID', value: 'parentId', width: 100 },
        { text: '类型', value: 'treeNodePath' },
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
      let deltaList = await listTreeNode({ flag: 0, parentId: record.id })
      Vue.set(record, '_loaded', true)
      return deltaList
    },
    async getRoot () {
      let param = {
        flag: 0
      }
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
      const temp = Object.assign({}, defaultEditForm)
      temp.parentId = this.currentRow.id
      let t = this.currentRow.nodeValue
      if (t !== 'root') {
        t = this.currentRow.treeNodePath + '.' + this.currentRow.nodeValue
      }
      if (t.indexOf('root.') === 0) {
        t = t.substring(5)
      }
      temp.treeNodePath = t
      temp.orderNum = 0
      this.formData = temp
      this.editType = 'add'
      this.showEditForm = true
    },
    handleUpdate () {
      if (!this.judgeSelectOne(this.currentRow)) {
        layer.iMsg('请选择待操作的数据', 'error')
        return
      }
      if (this.currentRow.nodeValue === 'root') {
        layer.iMsg('不能修改根节点', 'error')
        return
      }
      this.judgeCanUpdateValue()
      this.formData = Object.assign({}, this.defaultEditForm)
      this.formData.id = this.currentRow.id
      this.formData.nodeValue = this.currentRow.nodeValue
      this.formData.nodeName = this.currentRow.nodeName
      this.formData.parentId = this.currentRow.parentId
      this.formData.treeNodePath = this.currentRow.treeNodePath
      this.formData.orderNum = this.currentRow.orderNum
      this.formData.flag = this.currentRow.flag
      this.editType = 'update'
      this.showEditForm = true
    },
    handleRefresh () {
      if (!this.judgeSelectOne(this.currentRow)) {
        layer.iMsg('请选择待操作的数据', 'error')
        return
      }
      listTreeNode({
        flag: 0,
        parentId: this.currentRow.id
      }).then(result => {
        Vue.set(this.currentRow, 'children', result)
        layer.iMsg('刷新成功', 'success')
      })
    },
    handleDelete () {
      if (!this.judgeSelectOne(this.currentRow)) {
        layer.iMsg('请选择待操作的数据', 'error')
        return
      }
      if (this.currentRow.nodeValue === 'root') {
        layer.iMsg('不能删除根节点', 'error')
        return
      }
      if (!this.judegNoChild()) {
        layer.iMsg('节点未展开或节点存在下级元素，不能删除！', 'error')
        return
      }
      deleteTreeNode({ id: this.currentRow.id }).then(result => {
        let temp = this.currentRow.parent.children
        for (let i = 0; i < temp.length; i++) {
          let item = temp[i]
          if (item.id === this.currentRow.id) {
            Vue.set(this.currentRow.parent, 'children', Array.concat(temp.slice(0, i), temp.slice(i + 1, temp.length)))
            break
          }
        }
        console.log(temp)
        layer.iMsg('删除成功', 'success', 5000)
      })
    },
    addData () {
      addTreeNode(this.formData).then(result => {
        this.currentRow.children.push(result)
        layer.iMsg('添加成功', 'success', 5000)
        this.showEditForm = false
      })
    },
    updataData () {
      updateTreeNode(this.formData).then(result => {
        this.currentRow.nodeName = result.nodeName
        this.currentRow.nodeValue = result.nodeValue
        this.currentRow.orderNum = result.orderNum
        layer.iMsg('修改成功', 'success', 5000)
        this.showEditForm = false
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
