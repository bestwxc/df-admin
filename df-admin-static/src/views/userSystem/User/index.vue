<template>
<div class="app-container">
  <base-table
    :columns="columns"
    :supportPage="supportPage"
    :listUrl="listUrl"
    :deleteUrl="deleteUrl"
    :addUrl="addUrl"
    :updateUrl="updateUrl">
  </base-table>
</div>
</template>
<script>
import BaseTable from '@/components/table/BaseTable'
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
      ]
    }
  },
  methods: {
  },
  created () {
  }
}
</script>
