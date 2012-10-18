<%--<%@ page defaultCodec="html"%>--%>
<%
def answers=bean?.responses.sort { a,b ->
	a.id <=> b.id
}
 %>
<g:each in="${answers }" var="reply">
    <% def required=(reply.formField.required.equals('Y') ? true:null) %>
	<div class="control-group ${invalid ? 'error' : ''}">
		<label class="control-label span3" for="${reply.id}">
			${reply.formField.question} ${required ? '<span class="required-indicator">*</span>' : '' }
		</label>
		<div class="controls">
		    <g:if test="${reply.formField.lookupList }">
		        
		    </g:if>
		    <g:elseif test="${reply.formField.cssClass?.equalsIgnoreCase('textArea') }">
		        <g:textArea class="span5" name="${reply.id }" value="${reply.value }" rows="10"></g:textArea>
		    </g:elseif>
		    <g:elseif test="${reply.formField.cssClass?.equalsIgnoreCase('textField') }">
                <g:textField name="${reply.id }" value="${reply.value }" required="${required ? 'true':'false' }"/>
            </g:elseif>
            <g:else>
                <g:textField name="${reply.id }" value="${reply.value }" required="${required ? 'true':'false' }"/>
            </g:else>
		</div>
	</div>
</g:each>
<g:link controller="questionResponse" action="create" params="[document: bean]">Add QR</g:link>