<template>
  <el-table :data="formatData" :row-style="showRow" v-bind="$attrs" @current-change="handleCurrentChange">
    <el-table-column v-if="columns.length===0" width="150">
      <template slot-scope="scope">
        <span v-for="space in scope.row._level" :key="space" class="ms-tree-space" />
        <span v-if="iconShow(0,scope.row)" class="tree-ctrl" @click="toggleExpanded(scope.$index)">
          <i v-if="!scope.row._expanded" class="el-icon-plus" />
          <i v-else class="el-icon-minus" />
        </span>
        {{ scope.$index }}
      </template>
    </el-table-column>
    <el-table-column v-for="(column, index) in columns" v-if="!column.hide" :key="column.value" :label="column.text" :width="column.width" :prop="column.value">
      <template slot-scope="scope">
        <!-- Todo -->
        <!-- eslint-disable-next-line vue/no-confusing-v-for-v-if -->
        <span v-for="space in scope.row._level" v-if="index === 0" :key="space" class="ms-tree-space" />
        <span v-if="iconShow(index,scope.row)" class="tree-ctrl" @click="toggleExpanded(scope.$index)">
          <i v-if="!scope.row._expanded" class="el-icon-plus" />
          <i v-else class="el-icon-minus" />
        </span>
        {{ scope.row[column.displayValue] || scope.row[column.value] }}
      </template>
    </el-table-column>
    <slot/>
  </el-table>
</template>

<script>
/**
  Auth: Lei.j1ang
  Created: 2018/1/19-13:59
*/
import Vue from 'vue'
import treeToArray from './eval'
export default {
  name: 'TreeTable',
  props: {
    /* eslint-disable */
    data: {
      type: [Array, Object],
      required: true
    },
    columns: {
      type: Array,
      default: () => []
    },
    evalFunc: Function,
    evalArgs: Array,
    loadFunc: Function,
    expandAll: {
      type: Boolean,
      default: false
    },
    handleCurrentChange: Function
  },
  computed: {
    // 格式化数据源
    formatData: function () {
      return this.formatList(this.data)
    }
  },
  methods: {
    formatList: function (data) {
      let tmp
      if (!Array.isArray(data)) {
        tmp = [data]
      } else {
        tmp = data
      }
      const func = this.evalFunc || treeToArray
      const args = this.evalArgs ? Array.concat([tmp, this.expandAll], this.evalArgs) : [tmp, this.expandAll]
      return func.apply(null, args)
    },
    showRow: function (row) {
      const show = (row.row.parent ? (row.row.parent._expanded && row.row.parent._show) : true)
      row.row._show = show
      return show ? 'animation:treeTableShow 1s;-webkit-animation:treeTableShow 1s;' : 'display:none;'
    },
    // 切换下级是否展开
    toggleExpanded: function (trIndex) {
      const record = this.formatData[trIndex]
      const func = this.loadFunc || function (record) {
        Vue.set(record, '_loaded', true)
        return data
      }
      if (!record.loaded) {
        func.apply(this, [record]).then(result => {
          Vue.set(record, 'children', result)
        })
      }
      record._expanded = !record._expanded
    },
    // 图标显示
    iconShow (index, record) {
      let flag = index === 0 && (!record._loaded || (record._loaded && record.children && record.children.length > 0))
      return flag
    },
    columnFormatter (row, column, cellValue, index) {
      console.log('columnFormatter')
      const prop = column.property
      let columnConfig = this.columns.filter(item => item.value === prop)[0]
      if (columnConfig.type === 'dict' && columnConfig.dictType && cellValue) {
        return this.commonFormatter('dict', columnConfig.dictType, row, column, cellValue, index)
      }
      return cellValue
    }
  }
}
</script>
<style rel="stylesheet/css">
@keyframes treeTableShow {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
@-webkit-keyframes treeTableShow {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
</style>

<style lang="scss" rel="stylesheet/scss" scoped>
$color-blue: #2196f3;
$space-width: 18px;
.ms-tree-space {
  position: relative;
  top: 1px;
  display: inline-block;
  font-style: normal;
  font-weight: 400;
  line-height: 1;
  width: $space-width;
  height: 14px;
  &::before {
    content: "";
  }
}
.processContainer {
  width: 100%;
  height: 100%;
}
table td {
  line-height: 26px;
}

.tree-ctrl {
  position: relative;
  cursor: pointer;
  color: $color-blue;
  margin-left: -$space-width;
}
</style>
