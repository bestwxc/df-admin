import Vue from 'vue'
const dicts = {
  state: {
    tree: {}
  },
  mutations: {
    SAVE_TREE_NODE: (state, payload) => {
      if (!payload || !payload.dictType || !payload.dictList || payload.dictList.length === 0) {
        return
      }
      let dictType = payload.dictType
      let dictList = payload.dictList.map(item => {
        let temp = {}
        temp.nodeValue = item.nodeValue
        temp.nodeName = item.nodeName
        return temp
      })
      Vue.set(state.tree, dictType, dictList)
      sessionStorage.setItem('tree-' + dictType, dictList)
    }
  },
  actions: {
    SaveTreeNode: ({ commit }, payload) => {
      commit('SAVE_TREE_NODE', payload)
    }
  }
}
export default dicts
