<template>
<div>
  <div class="df-editor-container">
    <json-editor ref="editorImpl" v-if="type === 'json'" v-bind:value="value" v-bind="editorOptions"/>
    <markdown-editor ref="editorImpl" v-if="type === 'markdown'" v-bind:value="value" v-bind="editorOptions"/>
    <tinymce ref="editorImpl" v-if="type === 'tinymce'" v-bind:value="value" v-bind="editorOptions"/>
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
    value: {
      type: String,
      default: () => ''
    },
    type: {
      type: String,
      required: true
    },
    editorOptions: {
      type: Object,
      default: () => {}
    }
  },
  methods: {
    getContent () {
      return this.$ref.editorImpl.getContent()
    }
  }
}
</script>
<style lang="scss">
df-editor-container {
  display:inline
}
</style>
