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
  name: 'Division',
  components: { BaseTable },
  data () {
    return {
      tableConfig: {
        tableType: 'treeTable',
        treeTableConfig: {
          expandAll: false,
          relateKey: 'divisionCode',
          relateParentKey: 'parentDivisionCode',
          rootKeyValue: '86'
        },
        columns: [
          {text: '区域名称', value: 'divisionName'},
          {text: 'ID', value: 'id', width: 120, hideAdd: true, disableUpdate: true},
          {text: '区域代码', value: 'divisionCode', width: 120, filter: true, disableUpdate: true},
          {text: '上级ID', value: 'parentId', width: 120},
          {text: '上级区域代码', value: 'parentDivisionCode', width: 150, filter: true, disableAdd: true, disableUpdate: true},
          {text: '区域级别', value: 'divisionLevel', width: 60},
          {text: '区域级别调整', value: 'levelAdjust', width: 60, hide: true, hideAdd: true, hideUpdate: true},
          {text: '城乡区域类型', value: 'divisionType', displayValue: 'divisionTypeName', width: 60, type: 'select', dictType: 'tree', childDictType: 'system.dicts.divisionType'},
          {text: '状态', value: 'flag', defaultValue: 0, hide: true, hideAdd: true, hideUpdate: true},
          {text: '排序值', value: 'orderNum', width: 60}
        ],
        list: {
          enabled: true,
          url: '/api/admin/division/list',
          supportPage: false
        },
        add: {
          enabled: false,
          url: '/api/admin/division/add'
        },
        update: {
          enabled: false,
          url: '/api/admin/division/update'
        },
        del: {
          enabled: false,
          url: '/api/admin/division/delete'
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
