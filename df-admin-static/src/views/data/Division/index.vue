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
    :enableAdd="enableAdd"
    :enableUpdate="enableUpdate"
    :enableDelete="enableDelete">
  </base-tree-table>
</div>
</template>
<script>
import BaseTreeTable from '@/components/table/BaseTreeTable'
export default {
  name: 'Division',
  components: { BaseTreeTable },
  data () {
    return {
      relateKey: 'divisionCode',
      relateParentKey: 'parentDivisionCode',
      rootKeyValue: '86',
      listUrl: '/api/admin/division/list',
      addUrl: '/api/admin/division/add',
      deleteUrl: '/api/admin/division/delete',
      updateUrl: '/api/admin/division/update',
      enableAdd: false,
      enableUpdate: false,
      enableDelete: false,
      supportPage: false,
      columns: [
        { text: '区域名称', value: 'divisionName', width: 270 },
        { text: 'ID', value: 'id', width: 120 },
        { text: '区域代码', value: 'divisionCode', width: 120, filter: true },
        { text: '上级ID', value: 'parentId', width: 120 },
        { text: '上级区域代码', value: 'parentDivisionCode', width: 150, filter: true },
        { text: '区域级别', value: 'divisionLevel', width: 40 },
        { text: '区域级别调整', value: 'levelAdjust', width: 40 },
        { text: '城乡区域类型', value: 'divisionType', width: 60 },
        {text: '状态', value: 'flag', defaultValue: 0, hide: true, hideAdd: true, hideUpdate: true},
        { text: '排序值', value: 'orderNum', width: 40 }
      ]
    }
  },
  methods: {
  },
  created () {
  }
}
</script>
