<%@ page defaultCodec="html" %>
<g:if test="${bean.items }" >
	<div class="control-group ${invalid ? 'error' : ''}">
		<label class="control-label" for="${property}">${label}</label>
		<div class="controls">
			<g:select id="items" name="items" from="${bean?.items}" disabled="true" multiple="true" optionKey="id" optionValue="name" required="true"
	            class="many-to-one" />
		</div>
	</div>
</g:if>