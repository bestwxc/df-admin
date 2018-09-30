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
