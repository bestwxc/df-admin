<template>
<div>
  <df-editor v-bind:type="type"/>
</div>
</template>
<script>
import DfEditor from '@/components/DfEditor'
export default {
  name: 'TestPage',
  components: {
    DfEditor
  },
  data () {
    return {
      type: 'tinymce',
      editorOptions: {
      }
    }
  }
}
</script>
