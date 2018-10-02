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
    v-on:addRoles="addRoles">
  </base-table>
  <el-dialog title="设置角色" :visible.sync="showEditForm">
    <el-transfer v-model="selectRoles" :data="roles"
      :titles="['所有角色', '已选择']"
      :props="{key:'id',label:'roleName'}">
    </el-transfer>
    <div slot="footer" class="dialog-footer">
      <el-button @click="showEditForm = false">取消</el-button>
      <el-button type="primary" @click="syncUserRole">确认</el-button>
    </div>
  </el-dialog>
</div>
</template>
<script>
import BaseTable from '@/components/table/BaseTable'
import layer from '@/utils/layer'
import { listRole } from '@/api/userSystem/role'
import { listUserRole, syncUserRole } from '@/api/userSystem/userRole'
export default {
  name: 'User',
  components: { BaseTable },
  data () {
    return {
      listUrl: '/api/admin/user/list',
      addUrl: '/api/admin/user/add',
      deleteUrl: '/api/admin/user/delete',
      updateUrl: '/api/admin/user/update',
      supportPage: false,
      columns: [
        {text: 'ID', value: 'id'},
        {text: '用户名', value: 'userName', filter: true, hide: false, hideAdd: false, hideUpdate: false, disableAdd: false, disableUpdate: false},
        {text: '昵称', value: 'nickName', filter: true},
        {text: '手机号码', value: 'mobileNo', filter: true},
        {text: '用户状态', value: 'userState', filter: true},
        {text: '用户密码', value: 'userPass', hide: true},
        {text: 'salt', value: 'salt', hide: true, hideAdd: true, hideUpdate: true},
        {text: '部门代码', value: 'departmentCode', filter: true},
        {text: '排序值', value: 'orderNum', defaultValue: 0},
        {text: '状态', value: 'flag', defaultValue: 0, hide: true, hideAdd: true, hideUpdate: true}
      ],
      extBtns: [
        {type: 'success', text: '设置角色', icon: 'el-icon-plus', event: 'addRoles'}
      ],
      showEditForm: false,
      roles: [],
      currentRow: {},
      userRoles: [],
      selectRoles: []
    }
  },
  methods: {
    async addRoles (currentRow) {
      if (currentRow && currentRow.id) {
        this.currentRow = currentRow
        await this.getAllRole()
        await this.getAllUserRole(currentRow.id)
        this.selectRoles = this.userRoles.map(item => item.roleId)
        this.showEditForm = true
      } else {
        layer.iMsg('请选择待操作的数据', 'error')
      }
    },
    async getAllRole () {
      let data = await listRole({
        flag: 0
      })
      this.roles = data.result
    },
    async getAllUserRole (userId) {
      let data = await listUserRole({
        userId,
        flag: 0
      })
      this.userRoles = data.result
    },
    async syncUserRole () {
      const beforeSelectRoleIds = this.userRoles.map(item => item.roleId)
      const addRoleIds = this.selectRoles.filter(item => beforeSelectRoleIds.indexOf(item) < 0)
      const removeUserRoleIds = this.userRoles
        .filter(item => this.selectRoles.indexOf(item.roleId) < 0)
        .map(item => item.id)
      await syncUserRole({
        userId: this.currentRow.id,
        addRoleIds,
        removeUserRoleIds
      })
      this.showEditForm = false
    }
  },
  created () {
  }
}
</script>
