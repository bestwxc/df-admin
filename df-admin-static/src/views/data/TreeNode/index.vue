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
  name: 'SystemMenu',
  components: { BaseTable },
  data () {
    return {
      tableConfig: {
        tableType: 'treeTable',
        treeTableConfig: {
          expandAll: false,
          relateKey: 'id',
          relateParentKey: 'parentId',
          rootKeyValue: '0'
        },
        columns: [
          { text: '值', value: 'nodeValue', width: 240 },
          { text: 'ID', value: 'id', width: 100, hide: false, hideAdd: true, hideUpdate: false, disableAdd: true, disableUpdate: true },
          { text: '名称', value: 'nodeName', width: 100 },
          { text: '上级ID', value: 'parentId', width: 100, disableAdd: true, disableUpdate: true },
          { text: '类型', value: 'treeNodePath', disableAdd: true, disableUpdate: true },
          { text: '排序值', value: 'orderNum', width: 40, defaultValue: 0 },
          { text: '状态', value: 'flag', width: 40, defaultValue: 0, hide: true, hideAdd: true, hideUpdate: true, disableAdd: true, disableUpdate: true }
        ],
        list: {
          enabled: true,
          url: '/api/admin/tree/list'
        },
        add: {
          enabled: true,
          url: '/api/admin/tree/add',
          preShowFormFunc: this.addFunc
        },
        update: {
          enabled: true,
          url: '/api/admin/tree/update'
        },
        del: {
          enabled: true,
          url: '/api/admin/tree/delete'
        },
        extBtns: [
          {type: 'success', text: '关联资源', icon: 'el-icon-edit', event: 'showRelateResourcePage', judgeSelectOne: true, judegNoChild: true}
        ]
      }
    }
  },
  methods: {
    addFunc (temp, currentRow) {
      temp.parentId = currentRow.id
      let t = currentRow.nodeValue
      if (t !== 'root') {
        t = currentRow.treeNodePath + '.' + currentRow.nodeValue
      }
      if (t.indexOf('root.') === 0) {
        t = t.substring(5)
      }
      temp.treeNodePath = t
    }
  },
  created () {
  }
}
</script>
