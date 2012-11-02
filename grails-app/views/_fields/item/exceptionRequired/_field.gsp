<%@ page defaultCodec="html" %>
<div class="control-group ${invalid ? 'error' : ''}">
	<label class="control-label" for="${property}">${label}</label>
	<div class="controls">
	    <select name="${property } id="${property }">
	       <option value="Y">Yes</option>
	       <option value="N" selected="selected">No</option>
	       <option value="A">Alternate</option>
	    </select>
		<g:if test="${invalid}"><span class="help-inline">${errors.join('<br>')}</span></g:if>
	</div>
</div>