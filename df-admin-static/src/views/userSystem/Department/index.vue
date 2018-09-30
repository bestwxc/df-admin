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
  name: 'Department',
  components: { BaseTable },
  data () {
    return {
      listUrl: '/api/admin/department/list',
      addUrl: '/api/admin/department/add',
      deleteUrl: '/api/admin/department/delete',
      updateUrl: '/api/admin/department/update',
      supportPage: false,
      columns: [
        {text: 'ID', value: 'id'},
        {text: '部门名称', value: 'departmentCode', hide: false, hideAdd: false, hideUpdate: false, disableAdd: false, disableUpdate: false},
        {text: '部门代码', value: 'departmentCode', filter: true},
        {text: '上级部门', value: 'parentDepartmentCode', filter: true},
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
