<%@ page defaultCodec="html"%>
<div class="control-group ${invalid ? 'error' : ''}">
	<label class="control-label" for="${property}">
		${label} <span class="required-indicator">*</span>
	</label>
	<div class="controls">
	   <ul>
	       <g:each in="${bean?.items }" var="item" >
	           <li><g:createLink controller="item" action="show" id="${item.id }" />${item.name }</li>
	       </g:each>
	   </ul>
	   <g:link controller="item" action="create" params="[document: bean]">Add Item</g:link>
	</div>
</div>