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
  name: 'Resource',
  components: { BaseTable },
  data () {
    return {
      listUrl: '/api/admin/resource/list',
      addUrl: '/api/admin/resource/add',
      deleteUrl: '/api/admin/resource/delete',
      updateUrl: '/api/admin/resource/update',
      supportPage: false,
      columns: [
        {text: 'ID', value: 'id'},
        {text: '资源名称', value: 'resourceName', filter: true},
        {text: '资源代码', value: 'resourceCode', filter: true},
        {text: '资源路径', value: 'resourcePath'},
        {text: '资源类型', value: 'resourceType', filter: true, type: 'select', displayValue: 'resourceTypeName', dictType: 'tree', childDictType: 'system.dicts.resourceType'},
        {text: '资源说明', value: 'description'},
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
