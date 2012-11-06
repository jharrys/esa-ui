<%@ page defaultCodec="html" %>
<div class="control-group ${invalid ? 'error' : ''}">
	<label class="control-label" for="${property}">${label}</label>
	<div class="controls">
		<g:select name="${property }" from="${['':'-None Selected-','Y':'Yes','N':'No','A':'Alternate'] }" value="${value }" optionKey="${{it.key } }" optionValue="${{it.value } }" />
		<g:if test="${invalid}"><span class="help-inline">${errors.join('<br>')}</span></g:if>
	</div>
</div>