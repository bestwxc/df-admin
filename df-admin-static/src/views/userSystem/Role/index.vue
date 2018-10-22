<template>
<div class="app-container">
  <base-table
    v-bind="tableConfig"
    v-on:addResources="addResources">
  </base-table>
  <el-dialog title="设置权限" :visible.sync="showEditForm">
    <el-transfer v-model="selectResources" :data="resources"
      :titles="['所有资源', '已选择']"
      :props="{key:'id',label:'resourceCode'}">
    </el-transfer>
    <div slot="footer" class="dialog-footer">
      <el-button @click="showEditForm = false">取消</el-button>
      <el-button type="primary" @click="syncRoleResource">确认</el-button>
    </div>
  </el-dialog>
</div>
</template>
<script>
import BaseTable from '@/components/table/BaseTable'
import { listResource } from '@/api/userSystem/resource'
import { listRoleResource, syncRoleResource } from '@/api/userSystem/roleResource'
export default {
  name: 'Role',
  components: { BaseTable },
  data () {
    return {
      tableConfig: {
        tableType: 'baseTable',
        treeTableConfig: {
        },
        columns: [
          {text: 'ID', value: 'id'},
          {text: '角色名称', value: 'roleName', hide: false, hideAdd: false, hideUpdate: false, disableAdd: false, disableUpdate: false},
          {text: '角色代码', value: 'roleCode', filter: true},
          {text: '角色说明', value: 'description'},
          {text: '排序值', value: 'orderNum', hide: true, defaultValue: 0},
          {text: '状态', value: 'flag', defaultValue: 0, hide: true, hideAdd: true, hideUpdate: true}
        ],
        list: {
          enabled: true,
          url: '/api/admin/role/list',
          supportPage: false
        },
        add: {
          enabled: true,
          url: '/api/admin/role/add'
        },
        update: {
          enabled: true,
          url: '/api/admin/role/update'
        },
        del: {
          enabled: true,
          url: '/api/admin/role/delete'
        },
        extBtns: [
          {type: 'success', text: '设置权限', icon: 'el-icon-plus', event: 'addResources', judgeSelectOne: true}
        ]
      },
      showEditForm: false,
      resources: [],
      currentRow: {},
      roleResources: [],
      selectResources: []
    }
  },
  methods: {
    async addResources (currentRow) {
      this.currentRow = currentRow
      await this.getAllResource()
      await this.getAllRoleResource(currentRow.id)
      this.selectResources = this.roleResources.map(item => item.resourceId)
      this.showEditForm = true
    },
    async getAllResource () {
      let data = await listResource({
        flag: 0
      })
      this.resources = data.result
    },
    async getAllRoleResource (roleId) {
      let data = await listRoleResource({
        roleId,
        flag: 0
      })
      this.roleResources = data.result
    },
    async syncRoleResource () {
      const beforeSelectResourceIds = this.roleResources.map(item => item.resourceId)
      const addResourceIds = this.selectResources.filter(item => beforeSelectResourceIds.indexOf(item) < 0)
      const removeRoleResourceIds = this.roleResources
        .filter(item => this.selectResources.indexOf(item.resourceId) < 0)
        .map(item => item.id)
      await syncRoleResource({
        roleId: this.currentRow.id,
        addResourceIds,
        removeRoleResourceIds
      })
      this.showEditForm = false
    }
  },
  created () {
  }
}
</script>
