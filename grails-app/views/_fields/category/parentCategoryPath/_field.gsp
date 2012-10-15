<%@ page defaultCodec="html" %>
<div class="control-group ${invalid ? 'error' : ''}">
	<label class="control-label" for="${property}">${label}</label>
	<div class="controls">
		<g:field type="text" id="parentCategoryPath" name="parentCategoryPath.id" required="true" disabled="true" value="${bean?.parentCategoryPath}" class="one-to-one" />
	</div>
</div>