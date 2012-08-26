<%@ page import="org.ihc.esa.domain.Document" %>
<%@ page import="org.ihc.esa.domain.Form" %>
<%@ page import="org.ihc.esa.domain.FormField" %>
<%@ page import="org.ihc.esa.domain.LookupList" %>
<%@ page import="org.ihc.esa.domain.LookupElement" %>

<!-- 
	1. form = Form.get(1).id
	2. formFields = FormField.findByForm(form)
	3. need max section_number for form
	4. need docId (will need to initialize one and pass it in)
	field.dataType = not nullable
	field.multiSelect = not nullable ('N' or 'Y')
	field.lookupList = is nullable
 -->

<g:each in="${formFields }" var="field">

	<div class="${field.id} ">
		<g:set var="lookupList" value="${field.lookupList }" />
		<g:set var="dataType" value="${field.dataType }" />
		<g:set var="multiSelect" value="${field.multiSelect }" />
		<b>${field.question }</b><br>
		<g:if test="${lookupList != null }" >
			<g:select name="lookupList.name" from="${LookupElement.findAllByLookupList(lookupList) }" optionKey="id" optionValue="display" noSelection="['':'-Select One-']" />
		</g:if>
		<g:else>
			<g:if test="${field.dataType == 'Date' }" >
				<g:field name="field_${field.id }" type="date" /> 
			</g:if>
			<g:elseif test="${field.dataType == 'Number' }">
				<g:field name="field_${field.id }" type="number" />
			</g:elseif>
			<g:elseif test="${field.dataType == 'String' }">
				<g:field name="field_${field.id }" type="string" />
			</g:elseif>
			<g:else>
				${field.dataType } not setup yet.
			</g:else>
		</g:else>
	</div>

</g:each>
