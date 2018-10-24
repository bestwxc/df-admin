<template>
<div>
  <div class="df-editor-container">
    <json-editor v-if="type === 'json'" :editorOptions="editorOptions"/>
    <markdown-editor v-if="type === 'markdown'" :editorOptions="editorOptions" />
    <tinymce v-if="type === 'tinymce'" :editorOptions="editorOptions"/>
  </div>
</div>
</template>
<script>
import JsonEditor from './JsonEditor'
import MarkdownEditor from './MarkdownEditor'
import Tinymce from './Tinymce'
export default {
  name: 'DfEditor',
  components: {
    JsonEditor,
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
