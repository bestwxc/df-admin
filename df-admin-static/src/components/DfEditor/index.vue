<template>
<div>
  <div class="df-editor-container">
    <json-editor v-if="type === 'json'" />
    <markdown-editor v-if="type === 'markdown'" />
    <tinymce v-if="type === 'tinymce'" />
  </div>
</div>
</template>
<script>
// import JsonEditor from './JsonEditorJ'
import MarkdownEditor from './MarkdownEditor'
import Tinymce from './Tinymce'
export default {
  name: 'DfEditor',
  components: {
    // JsonEditor,
    MarkdownEditor,
    Tinymce
  },
  props: {
    type: {
      type: String,
      required: true
    },
    editorOptions: {
      type: Object,
      default: () => {}
    }
  }

}
</script>
<style lang="scss">
df-editor-container {
  display:inline
}
</style>
