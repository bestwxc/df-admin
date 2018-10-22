<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
import initDicts from '@/store/initDict'
export default {
  name: 'App',
  methods: {

  },
  created () {
    initDicts.init()
  }
}
</script>
