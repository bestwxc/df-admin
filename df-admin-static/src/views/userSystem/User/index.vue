<template>
<div class="app-container">
  <df-table
    v-on:addRoles="addRoles"
    v-on:showResetPass="showResetPass"
    v-bind="tableConfig">
  </df-table>
  <!-- 设置角色区域 -->
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
  <!-- 重置密码区域 -->
  <el-dialog title="重置密码" :visible.sync="showResetPassForm">
    <el-form ref="dataForm" :rules="rules" :model="resetPassFormData" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
      <el-form-item label="新密码" prop="newPassword">
        <el-input :type="pwdType" v-model="resetPassFormData.newPassword" minlength="6" placeholder="请输入新密码" clearable></el-input>
      </el-form-item>
      <el-form-item label="密码校验" prop="newPasswordCheck">
        <el-input :type="pwdType" v-model="resetPassFormData.newPasswordCheck" minlength="6" placeholder="请再输入一次密码" clearable></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="showResetPassForm = false">取消</el-button>
      <el-button type="primary" @click="resetPass">重置</el-button>
    </div>
  </el-dialog>
</div>
</template>
<script>
import DfTable from '@/components/table/DfTable'
import layer from '@/utils/layer'
import { listRole } from '@/api/userSystem/role'
import { listUserRole, syncUserRole } from '@/api/userSystem/userRole'
import { userResetPass } from '@/api/userSystem/user'
export default {
  name: 'User',
  components: { DfTable },
  data () {
    return {
      tableConfig: {
        tableType: 'baseTable',
        treeTableConfig: {
        },
        columns: [
          {text: 'ID', value: 'id', hideAdd: true, disableAdd: true, disableUpdate: true},
          {text: '用户名', value: 'userName', filter: true, hide: false, hideAdd: false, hideUpdate: false, disableAdd: false, disableUpdate: false},
          {text: '昵称', value: 'nickName', filter: true},
          {text: '手机号码', value: 'mobileNo', filter: true},
          {text: '用户状态', value: 'userState', filter: true, type: 'select', displayValue: 'userStateName', dictType: 'tree', childDictType: 'system.dicts.userState'},
          {text: '用户密码', value: 'userPass', hide: true, hideUpdate: true},
          {text: 'salt', value: 'salt', hide: true, hideAdd: true, hideUpdate: true},
          {text: '部门代码', value: 'departmentCode', filter: true},
          {text: '状态', value: 'flag', defaultValue: 0, hide: true, hideAdd: true, hideUpdate: true}
        ],
        list: {
          enabled: true,
          url: '/api/admin/user/list',
          supportPage: false
        },
        add: {
          enabled: true,
          url: '/api/admin/user/add'
        },
        update: {
          enabled: true,
          url: '/api/admin/user/update'
        },
        del: {
          enabled: true,
          url: '/api/admin/user/delete'
        },
        extBtns: [
          {type: 'success', text: '设置角色', icon: 'el-icon-plus', event: 'addRoles', judgeSelectOne: true},
          {type: 'primary', text: '重置密码', icon: 'el-icon-edit', event: 'showResetPass', judgeSelectOne: true}
        ]
      },
      showEditForm: false,
      roles: [],
      currentRow: {},
      userRoles: [],
      selectRoles: [],
      showResetPassForm: false,
      resetPassFormData: {
        newPassword: '',
        newPasswordCheck: ''
      },
      pwdType: 'password',
      rules: {}
    }
  },
  methods: {
    async addRoles (currentRow) {
      this.currentRow = currentRow
      await this.getAllRole()
      await this.getAllUserRole(currentRow.id)
      this.selectRoles = this.userRoles.map(item => item.roleId)
      this.showEditForm = true
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
    },
    async showResetPass (currentRow) {
      this.resetPassFormData.id = currentRow.id
      this.resetPassFormData.newPassword = ''
      this.resetPassFormData.newPasswordCheck = ''
      this.showResetPassForm = true
    },
    async resetPass () {
      userResetPass(this.resetPassFormData).then(res => {
        layer.iMsg('重置成功', 'success', 3000)
        this.showResetPassForm = false
      })
    }
  },
  created () {
  }
}
</script>
