<template>
<div class="app-container">
  <base-table :columns="columns" :supportPage="supportPage" :listUrl="listUrl">
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
      supportPage: false,
      columns: [
        {text: '菜单名称', value: 'menuName'},
        {text: 'ID', value: 'id'},
        {text: '菜单代码', value: 'menuCode'},
        {text: '菜单节点类型', value: 'menuType'},
        {text: '菜单图标', value: 'menuIcon'},
        {text: '上级菜单', value: 'parentMenu'},
        {text: '跳转地址', value: 'jumpPath'},
        {text: '排序值', value: 'orderNum'},
        {text: '状态', value: 'flag'}
      ]
    }
  },
  methods: {
  },
  created () {
  }
}
</script>
