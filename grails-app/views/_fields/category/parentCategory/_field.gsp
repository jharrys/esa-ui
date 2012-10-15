<%@ page defaultCodec="html" %>
<div class="control-group ${invalid ? 'error' : ''}">
	<label class="control-label" for="${property}">${label}</label>
	<div class="controls">
	    <g:field type="text" disabled="true" id="parentCategory" name="parentCategory" value="${(bean?.parentCategory?.id == bean?.id) ? '/' : bean?.parentCategory?.name}" />
	</div>
</div>