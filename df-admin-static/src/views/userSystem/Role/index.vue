<template>
<div class="app-container">
  <base-table
    :columns="columns"
    :supportPage="supportPage"
    :listUrl="listUrl"
    :deleteUrl="deleteUrl"
    :addUrl="addUrl"
    :updateUrl="updateUrl"
    :extBtns="extBtns"
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
import layer from '@/utils/layer'
import { listResource } from '@/api/userSystem/resource'
import { listRoleResource, syncRoleResource } from '@/api/userSystem/roleResource'
export default {
  name: 'Role',
  components: { BaseTable },
  data () {
    return {
      listUrl: '/api/admin/role/list',
      addUrl: '/api/admin/role/add',
      deleteUrl: '/api/admin/role/delete',
      updateUrl: '/api/admin/role/update',
      supportPage: false,
      columns: [
        {text: 'ID', value: 'id'},
        {text: '角色名称', value: 'roleName', hide: false, hideAdd: false, hideUpdate: false, disableAdd: false, disableUpdate: false},
        {text: '角色代码', value: 'roleCode', filter: true},
        {text: '角色说明', value: 'description'},
        {text: '状态', value: 'flag', defaultValue: 0, hide: true, hideAdd: true, hideUpdate: true}
      ],
      extBtns: [
        {type: 'success', text: '设置权限', icon: 'el-icon-plus', event: 'addResources'}
      ],
      showEditForm: false,
      resources: [],
      currentRow: {},
      roleResources: [],
      selectResources: []
    }
  },
  methods: {
    async addResources (currentRow) {
      if (currentRow && currentRow.id) {
        this.currentRow = currentRow
        await this.getAllResource()
        await this.getAllRoleResource(currentRow.id)
        this.selectResources = this.roleResources.map(item => item.resourceId)
        this.showEditForm = true
      } else {
        layer.iMsg('请选择待操作的数据', 'error')
      }
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
