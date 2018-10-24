<template>
<div class="app-container">
  <base-table
    v-bind="tableConfig">
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
      tableConfig: {
        tableType: 'treeTable',
        treeTableConfig: {
          expandAll: false,
          relateKey: 'departmentCode',
          relateParentKey: 'parentDepartmentCode',
          rootKeyValue: 'root'
        },
        columns: [
          {text: '部门名称', width: 240, value: 'departmentName', hide: false, hideAdd: false, hideUpdate: false, disableAdd: false, disableUpdate: false},
          {text: 'ID', value: 'id', disableAdd: true, disableUpdate: true},
          {text: '上级部门', value: 'parentDepartmentCode', filter: true, disableAdd: true, disableUpdate: true},
          {text: '部门代码', value: 'departmentCode', disableUpdate: true},
          {text: '部门说明', value: 'description'},
          {text: '状态', value: 'flag', defaultValue: 0, hide: true, hideAdd: true, hideUpdate: true}
        ],
        list: {
          enabled: true,
          url: '/api/admin/department/list'
        },
        add: {
          enabled: true,
          url: '/api/admin/department/add'
        },
        update: {
          enabled: true,
          url: '/api/admin/department/update'
        },
        del: {
          enabled: true,
          url: '/api/admin/department/delete'
        },
        extBtns: [
        ]
      }
    }
  },
  methods: {
  },
  created () {
  }
}
</script>
