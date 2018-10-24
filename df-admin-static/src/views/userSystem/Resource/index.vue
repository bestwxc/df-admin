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
  name: 'Resource',
  components: { BaseTable },
  data () {
    return {
      tableConfig: {
        tableType: 'baseTable',
        treeTableConfig: {
        },
        columns: [
          {text: 'ID', value: 'id', disableAdd: true, disableUpdate: true},
          {text: '菜单ID', value: 'menuId', hide: true, hideAdd: true, hideUpdate: true},
          {text: '资源名称', value: 'resourceName', filter: true},
          {text: '资源代码', value: 'resourceCode', filter: true},
          {text: '资源路径', value: 'resourcePath'},
          {text: '资源类型', value: 'resourceType', filter: true, type: 'select', displayValue: 'resourceTypeName', dictType: 'tree', childDictType: 'system.dicts.resourceType'},
          {text: '资源说明', value: 'description', type: 'json'},
          {text: '状态', value: 'flag', defaultValue: 0, hide: true, hideAdd: true, hideUpdate: true}
        ],
        list: {
          enabled: true,
          url: '/api/admin/resource/list',
          supportPage: false
        },
        add: {
          enabled: true,
          url: '/api/admin/resource/add'
        },
        update: {
          enabled: true,
          url: '/api/admin/resource/update'
        },
        del: {
          enabled: true,
          url: '/api/admin/resource/delete'
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
