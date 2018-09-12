<template>
<div>
  <div class="filter-container">
    <!--
    <el-input placeholder="上级区域代码" v-model="queryParam.parentDivisionCode" class="filter-item" @keyup.enter.native="handleFilter" />
    -->
    <el-button class="filter-btn" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
  </div>
  <div class="btn-container">
    <!--<el-button class="filter-item" type="success" icon="el-icon-refresh" @click="handleRefresh">刷新节点</el-button>-->
    <el-button class="filter-item" type="primary" icon="el-icon-plus" @click="handleAdd">新增</el-button>
    <el-button class="filter-item" type="warning" icon="el-icon-edit" @click="handleUpdate">修改</el-button>
    <el-button class="filter-item" type="danger" icon="el-icon-delete" @click="handleDelete">删除</el-button>
  </div>
  <el-table
    :data="tableData"
    border
    style="width: 100%">
    <el-table-column v-for="(column) in columns" :key="column.value" :label="column.text" :width="column.width">
    </el-table-column>
  </el-table>
  <el-pagination v-if="supportPage" background layout="prev, pager, next" :total="total">
  </el-pagination>
</div>
</template>
<script>
import request from '@/utils/request'
export default {
  name: 'BaseTable',
  props: {
    listUrl: {
      type: String,
      default: ''
    },
    filterField: {
      type: Array,
      default: () => []
    },
    buttons: {
      type: Array,
      default: () => []
    },
    columns: {
      type: Array,
      default: () => []
    },
    supportPage: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      queryParam: {
      },
      tableData: [],
      total: 0,
      pageNo: 1,
      pageSize: 100
    }
  },
  computed: {
  },
  methods: {
    async handleFilter () {
      this.getList()
    },
    async getList () {
      console.log(this.listUrl)
      request({
        url: this.listUrl,
        method: 'post',
        data: this.queryParam
      }).then(result => result)
    }
  },
  created () {
    this.getList()
  }
}
</script>
