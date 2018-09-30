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
    :updateUrl="updateUrl">
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
      relateKey: 'menuCode',
      relateParentKey: 'parentMenu',
      rootKeyValue: 'root',
      listUrl: '/api/admin/menu/list',
      addUrl: '/api/admin/menu/add',
      deleteUrl: '/api/admin/menu/delete',
      updateUrl: '/api/admin/menu/update',
      supportPage: false,
      columns: [
        {text: '菜单名称', value: 'menuName', width: 240, hide: false, hideAdd: false, hideUpdate: false, disableAdd: false, disableUpdate: false},
        {text: 'ID', value: 'id'},
        {text: '菜单代码', value: 'menuCode'},
        {text: '菜单图标', value: 'menuIcon'},
        {text: '上级菜单', value: 'parentMenu', filter: true},
        {text: '跳转地址', value: 'jumpPath'},
        {text: '排序值', value: 'orderNum', defaultValue: 0},
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
