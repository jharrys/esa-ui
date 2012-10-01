<%@ page defaultCodec="html"%>
<div class="control-group ${invalid ? 'error' : ''}">
	<label class="control-label" for="${property}">
		${label} <span class="required-indicator">*</span>
	</label>
	<div class="controls">
		<ul>
           <g:each in="${bean?.responses }" var="r" >
               <li><a href="${createLink(controller: 'questionResponse', action: 'show', id: r.id) }">${r.value }</a></li>
           </g:each>
       </ul>
       <g:link controller="questionResponse" action="create" params="[document: bean]">Add QR</g:link>
	</div>
</div>