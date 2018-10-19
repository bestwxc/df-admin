<template>
<div class="app-container">
    <div class="filter-container">
        <el-button class="filter-btn" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
    </div>
    <div class="btn-container">
    </div>
</div>
</template>
<script>
import { mapGetters } from 'vuex'
export default {
  name: 'DfTable',
  props: {
    tableType: {
      type: String,
      default: 'baseTable'
    },
    treeTableConfig: {
      type: Object,
      default: () => {}
    },
    columns: {
      type: Array,
      required: true
    },
    list: {
      type: Object,
      default: () => {}
    },
    add: {
      type: Object,
      default: () => {}
    },
    update: {
      type: Object,
      default: () => {}
    },
    delete: {
      type: Object,
      default: () => {}
    },
    extBtns: {
      type: Array,
      default: () => []
    }
  },
  data () {
    return {
    }
  },
  computed: {
    ...mapGetters({
      tree: 'tree'
    })
  },
  methods: {
  },
  mounted () {
  },
  created () {
  }
}
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
.app-container {
  .btn-container {
    padding: 10px 0 2px 0;
  }
  .filter-container {
    .filter-item {
      width: 200px;
    }
  }
}
</style>
