<template>
<div class="app-container">
  <base-tree-table
    :relateKey="relateKey"
    :relateParentKey="relateParentKey"
    :rootKeyValue="rootKeyValue"
    :columns="columns"
    :listUrl="listUrl"
    :deleteUrl="deleteUrl"
    :addUrl="addUrl"
    :updateUrl="updateUrl">
  </base-tree-table>
</div>
</template>
<script>
import BaseTreeTable from '@/components/table/BaseTreeTable'
export default {
  name: 'TestBaseTreeTable',
  components: { BaseTreeTable },
  data () {
    return {
      relateKey: 'departmentCode',
      relateParentKey: 'parentDepartmentCode',
      rootKeyValue: 'root',
      listUrl: '/api/admin/department/list',
      addUrl: '/api/admin/department/add',
      deleteUrl: '/api/admin/department/delete',
      updateUrl: '/api/admin/department/update',
      columns: [
        {text: '部门名称', width: 240, value: 'departmentName', hide: false, hideAdd: false, hideUpdate: false, disableAdd: false, disableUpdate: false},
        {text: 'ID', value: 'id'},
        {text: '上级部门', value: 'parentDepartmentCode', filter: true, disableAdd: true, disableUpdate: true},
        {text: '部门代码', value: 'departmentCode'},
        {text: '部门说明', value: 'description'},
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
