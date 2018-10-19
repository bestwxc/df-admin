<template>
<div>
  <el-menu class="navbar" mode="horizontal">
    <hamburger :toggle-click="toggleSideBar" :is-active="sidebar.opened" class="hamburger-container"/>
    <breadcrumb />
    <el-dropdown class="avatar-container" trigger="click">
      <div class="avatar-wrapper">
        <img :src="avatar+'?imageView2/1/w/80/h/80'" class="user-avatar">
        <i class="el-icon-caret-bottom"/>
      </div>
      <el-dropdown-menu slot="dropdown" class="user-dropdown">
        <router-link class="inlineBlock" to="/">
          <el-dropdown-item>
            首页
          </el-dropdown-item>
        </router-link>
        <el-dropdown-item divided>
          <span style="display:block;" @click="showPasswdTab">修改密码</span>
        </el-dropdown-item>
        <el-dropdown-item divided>
          <span style="display:block;" @click="logout">退出</span>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </el-menu>
  <el-dialog title="修改密码" :visible.sync="showEditForm">
    <el-form ref="dataForm" :rules="rules" :model="formData" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
      <el-form-item label="原密码" prop="password">
        <el-input :type="pwdType" v-model="formData.password" minlength="6" placeholder="请输入原密码" clearable></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="newPassword">
        <el-input :type="pwdType" v-model="formData.newPassword" minlength="6" placeholder="请输入新密码" clearable></el-input>
      </el-form-item>
      <el-form-item label="密码校验" prop="newPasswordCheck">
        <el-input :type="pwdType" v-model="formData.newPasswordCheck" minlength="6" placeholder="请再输入一次密码" clearable></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="showEditForm = false">取消</el-button>
      <el-button type="primary" @click="password">修改</el-button>
    </div>
  </el-dialog>
</div>
</template>

<script>
import { mapGetters } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'
import { userPasswd } from '@/api/userSystem/user'
import layer from '@/utils/layer'

export default {
  components: {
    Breadcrumb,
    Hamburger
  },
  data () {
    return {
      showEditForm: false,
      formData: {
        'password': '',
        'newPassword': '',
        'newPasswordCheck': ''
      },
      rules: {},
      pwdType: 'password'
    }
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'avatar',
      'user'
    ])
  },
  methods: {
    toggleSideBar () {
      this.$store.dispatch('ToggleSideBar')
    },
    logout () {
      this.$store.dispatch('Logout', {}).then(() => {
        location.reload() // 为了重新实例化vue-router对象 避免bug
      })
    },
    showPasswdTab () {
      this.formData.password = ''
      this.formData.newPassword = ''
      this.formData.newPasswordCheck = ''
      this.showEditForm = true
    },
    password () {
      this.formData.id = this.user.userId
      userPasswd(this.formData).then(result => {
        layer.iMsg('修改成功', 'success', 3000)
        this.showEditForm = false
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.navbar {
  height: 50px;
  line-height: 50px;
  border-radius: 0px !important;
  .hamburger-container {
    line-height: 58px;
    height: 50px;
    float: left;
    padding: 0 10px;
  }
  .screenfull {
    position: absolute;
    right: 90px;
    top: 16px;
    color: red;
  }
  .avatar-container {
    height: 50px;
    display: inline-block;
    position: absolute;
    right: 35px;
    .avatar-wrapper {
      cursor: pointer;
      margin-top: 5px;
      position: relative;
      .user-avatar {
        width: 40px;
        height: 40px;
        border-radius: 10px;
      }
      .el-icon-caret-bottom {
        position: absolute;
        right: -20px;
        top: 25px;
        font-size: 12px;
      }
    }
  }
}
</style>
