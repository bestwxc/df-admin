<template>
<div class="app-container">
  <base-tree-table
    :relateKey="relateKey"
    :relateParentKey="relateParentKey"
    :rootKeyValue="rootKeyValue"
    :columns="columns"
    :supportPage="supportPage"
    :listUrl="listUrl"
    :deleteUrl="deleteUrl"
    :addUrl="addUrl"
    :updateUrl="updateUrl"
    :addFunc="addFunc">
  </base-tree-table>
</div>
</template>
<script>
import BaseTreeTable from '@/components/table/BaseTreeTable'
export default {
  name: 'SystemMenu',
  components: { BaseTreeTable },
  data () {
    return {
      relateKey: 'id',
      relateParentKey: 'parentId',
      rootKeyValue: '0',
      listUrl: '/api/admin/tree/list',
      addUrl: '/api/admin/tree/add',
      deleteUrl: '/api/admin/tree/delete',
      updateUrl: '/api/admin/tree/update',
      supportPage: false,
      columns: [
        { text: '值', value: 'nodeValue', width: 240 },
        { text: 'ID', value: 'id', width: 100, hide: false, hideAdd: true, hideUpdate: false, disableAdd: true, disableUpdate: true },
        { text: '名称', value: 'nodeName', width: 100 },
        { text: '上级ID', value: 'parentId', width: 100, disableAdd: true, disableUpdate: true },
        { text: '类型', value: 'treeNodePath', disableAdd: true, disableUpdate: true },
        { text: '排序值', value: 'orderNum', width: 40, defaultValue: 0 },
        { text: '状态', value: 'flag', width: 40, defaultValue: 0, hide: true, hideAdd: true, hideUpdate: true, disableAdd: true, disableUpdate: true }
      ]
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
