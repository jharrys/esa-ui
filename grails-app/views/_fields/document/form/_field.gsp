<%@ page defaultCodec="html"%>
<div class="control-group ${invalid ? 'error' : ''}">
	<label class="control-label" for="${property}">
		${label} <span class="required-indicator">*</span>
	</label>
	<div class="controls">
		<g:select id="form" name="form.id" from="${org.ihc.esa.Form.list()}" optionKey="id" optionValue="name" required="" value="${bean?.form?.id}"
			class="many-to-one"
		/>
	</div>
</div>