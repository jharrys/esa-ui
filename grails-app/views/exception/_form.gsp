<%@ page import="org.ihc.esa.Document"%>
<%@ page import="org.ihc.esa.Form"%>
<%@ page import="org.ihc.esa.FormField"%>
<%@ page import="org.ihc.esa.LookupList"%>
<%@ page import="org.ihc.esa.LookupElement"%>

<g:logMsg level="debug">Loading _form template for exception save_section.gsp</g:logMsg>

<g:logMsg level="debug">Begin looping through formFields: "${formFields }"</g:logMsg>
<g:each in="${formFields }" var="field">

	<g:logMsg level="debug">Field: ${field }
	</g:logMsg>

	<%-- variables --%>
	<g:set var="question_id" value="${field.id }" />
	<g:set var="question_id_value" value="${field.question }" />
	<g:set var="question_can_select_multiple" value="${field.multiSelect == 'Y' ? true:false }" />
	<g:set var="question_cssclass_exists" value="${field.cssClass != null ? true:false }" />
	<g:set var="question_cssclass_value" value="${field.cssClass ?: '' }" />
	<g:set var="question_datatype_exists" value="${field.dataType != null ? true:false }" />
	<g:set var="question_datatype_value" value="${field.dataType ?: '' }" />
	<g:set var="question_lookuplist_exists" value="${field.lookupList != null ? true:false }" />
	<g:set var="question_lookuplist_value" value="${field.lookupList ?: 'Pick List Not Setup' }" />
	<g:set var="question_lookuplist_elements" value="${field.lookupList?.elements }" />
	<g:set var="field_error_exists" value="${false }" />

	<!-- if the qr datatype is null it means the field is for display only (no question) -->
	<!-- note that this appropriately does not display the qr with data type "Title" -->
	<g:if test="${!(question_datatype_exists) }">
		<g:logMsg level="debug">question_datatype_exists is false - setting new row with value ${question_id_value }
		</g:logMsg>
		<tr>
			<g:logMsg level="debug">Row definition for statement begins</g:logMsg>
			<td colspan="2"><g:logMsg level="debug">Column definition for statement begins</g:logMsg> ${question_id_value } <g:logMsg level="debug">Column definition for statement ends</g:logMsg>
			</td>
			<g:logMsg level="debug">Row definition for statement ends</g:logMsg>
		</tr>
	</g:if>

	<g:elseif test="${(!question_datatype_value.equals("Title") && !question_datatype_value.equals("SectionHeader")) }">
		<g:logMsg level="debug">question_datatype_exists is true - creating a question row with question: "${question_id_value }"</g:logMsg>
		<tr>
			<g:logMsg level="debug">Row definition for question/response starts</g:logMsg>

			<td><g:logMsg level="debug">Column definition for question starts</g:logMsg> <label for="questionResponse.id.${question_id }"> ${question_id_value }</label>
				<g:logMsg level="debug">Column definition for question ends</g:logMsg></td>

			<td><g:logMsg level="debug">Column definition for input starts</g:logMsg> <g:if
					test="${(question_can_select_multiple) && (question_lookuplist_exists) }"
				>
					<g:logMsg level="debug">question is multiple select and lookuplist does exist.</g:logMsg>
					<%-- this question has a pick list and can have multiple answers --%>
					<g:select class="${question_cssclass_value }" id="questionResponse.id.${question_id }" name="questionResponse.id.${question_id }"
						from="${question_lookuplist_elements }" multiple="true" optionKey="id" optionValue="display" noSelection="['':'-Select One-']"
					/>
				</g:if> <g:elseif test="${question_lookuplist_exists }">
					<g:logMsg level="debug">question is not multiple select possible and lookuplist does exist.</g:logMsg>
					<g:logMsg level="debug">lookuplist elements: "${question_lookuplist_elements }"</g:logMsg>
					<g:set var="defaultKey" value="" />
					<g:set var="defaultValue" value="-Select One-" />
					<g:if test="${question_lookuplist_value.name.equals('YesNo') }">
						<% defaultKey=1; defaultValue="Yes" %>
					</g:if>
					<g:select class="${question_cssclass_value }" id="questionResponse.id.${question_id }" name="questionResponse.id.${question_id }"
						from="${question_lookuplist_elements }" optionKey="id" optionValue="display" noSelection="["${defaultKey }":"${defaultValue }"]" />
				</g:elseif> <g:else>
					<g:logMsg level="debug">question is not a select or lookup data type</g:logMsg>
					<g:if test="${question_cssclass_value == 'textArea' }">
						<g:logMsg level="debug">question is ${question_cssclass_value }
						</g:logMsg>
						<g:textArea name="${question_id_value }">type here</g:textArea>
					</g:if>
					<g:elseif test="${question_datatype_value == 'Date' }">
						<g:logMsg level="debug">question is ${question_cssclass_value }
						</g:logMsg>
						<g:datePicker class="${question_cssclass_value }" id="questionResponse.id.${question_id }" name="questionResponse.id.${question_id }"
							value="${new Date()}" precision="day" noSelection="['':'-Choose-']"
						/>
					</g:elseif>
					<g:elseif test="${question_datatype_value == 'Number' }">
						<g:logMsg level="debug">question is ${question_cssclass_value }
						</g:logMsg>
						<g:field class="${question_cssclass_value }" id="questionResponse.id.${question_id }" name="questionResponse.id.${question_id }" type="number" />
					</g:elseif>
					<g:elseif test="${question_datatype_value == 'String' }">
						<g:logMsg level="debug">question is ${question_cssclass_value }
						</g:logMsg>
						<g:if test="${question_cssclass_value.equals("textArea") }">
							<g:logMsg level="debug">cssclass value is "textArea"</g:logMsg>
							<g:textArea id="questionResponse.id.${question_id }" name="questionResponse.id.${question_id }"></g:textArea>
						</g:if>
						<g:else>
							<g:logMsg level="debug">creating simple field input type</g:logMsg>
							<g:field class="${question_cssclass_value}" id="questionResponse.id.${question_id }" name="questionResponse.id.${question_id }" type="string" />
						</g:else>
					</g:elseif>
					<g:else>
						<g:logMsg level="debug">could not find type of data</g:logMsg>
						${field_error_exists = true }
					</g:else>
				</g:else> <g:if test="${field_error_exists }">
					<g:logMsg level="error">LookupList number ${question_lookuplist_value } (${question_lookuplist_value }) does not exist in the database.</g:logMsg>
					<p class="error">
						LookupList # (${question_lookuplist_value }): [${question_lookuplist_value }] is not yet seeded in the database
					</p>
				</g:if> <g:logMsg level="debug">Column definition for input ends</g:logMsg></td>
			<g:logMsg level="debug">Row definition for question/answer ends.</g:logMsg>
		</tr>
	</g:elseif>
	<g:else>
		<g:logMsg level="debug">Ignore datatype's with value "Title"</g:logMsg>
	</g:else>
</g:each>
