<template>
<div class="app-container">
  <df-table
    v-on:showRelateResourcePage="showRelateResourcePage"
    v-bind="tableConfig">
  </df-table>
</div>
</template>
<script>
import DfTable from '@/components/table/DfTable'
export default {
  name: 'TestTreeTable',
  components: { DfTable },
  data () {
    return {
      tableConfig: {
        tableType: 'treeTable',
        treeTableConfig: {
          expandAll: false,
          relateKey: 'menuCode',
          relateParentKey: 'parentMenu',
          rootKeyValue: 'root'
        },
        columns: [
          {text: '菜单名称', value: 'menuName', width: 240, hide: false, hideAdd: false, hideUpdate: false, disableAdd: false, disableUpdate: false},
          {text: 'ID', value: 'id', disableAdd: true, disableUpdate: true},
          {text: '菜单代码', value: 'menuCode', disableUpdate: true},
          {text: '菜单图标', value: 'menuIcon'},
          {text: '上级菜单', value: 'parentMenu', filter: true, disableAdd: true, disableUpdate: true},
          {text: '跳转地址', value: 'jumpPath'},
          {text: '排序值', value: 'orderNum', defaultValue: 0},
          {text: '状态', value: 'flag', defaultValue: 0, hide: true, hideAdd: true, hideUpdate: true}
        ],
        list: {
          enabled: true,
          url: '/api/admin/menu/list',
          supportPage: false
        },
        add: {
          enabled: true,
          url: '/api/admin/menu/add'
        },
        update: {
          enabled: true,
          url: '/api/admin/menu/update'
        },
        del: {
          enabled: true,
          url: '/api/admin/menu/delete'
        },
        extBtns: [
          {type: 'success', text: '关联资源', icon: 'el-icon-edit', event: 'showRelateResourcePage', judgeSelectOne: true, judegNoChild: true}
        ]
      }
    }
  },
  methods: {
    showRelateResourcePage () {
      console.log('showRelateResourcePage')
    }
  },
  created () {
  }
}
</script>
