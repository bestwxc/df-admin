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
  name: 'SystemMenu',
  components: { BaseTable },
  data () {
    return {
      listUrl: '/api/admin/menu/list',
      addUrl: '/api/admin/menu/add',
      deleteUrl: '/api/admin/menu/delete',
      updateUrl: '/api/admin/menu/update',
      supportPage: false,
      columns: [
        {text: 'ID', value: 'id'},
        {text: '菜单名称', value: 'menuName', hide: false, hideAdd: false, hideUpdate: false, disableAdd: false, disableUpdate: false},
        {text: '菜单代码', value: 'menuCode', filter: true},
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
