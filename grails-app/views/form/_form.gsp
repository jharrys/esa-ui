<%@ page import="org.ihc.esa.domain.Document"%>
<%@ page import="org.ihc.esa.domain.Form"%>
<%@ page import="org.ihc.esa.domain.FormField"%>
<%@ page import="org.ihc.esa.domain.LookupList"%>
<%@ page import="org.ihc.esa.domain.LookupElement"%>

<g:each in="${formFields }" var="field">

	<%-- code clean up starts here --%>
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

	<%-- 
        is this field really title, header, caption or statement
        a null dataType signifies this field is really text to be displayed and not a question 
    --%>
	<g:if test="${!(question_datatype_exists) }">
		<div class="${question_cssclass_value }">
			<p class="${question_cssclass_value }">
				${question_id_value }
			</p>
		</div>
	</g:if>
	<g:else>
		<%-- format & display question --%>
		<div class="${question_cssclass_value }">
			<p class="${question_cssclass_value }">
				${question_id_value }

				<g:if test="${(question_can_select_multiple) && (question_lookuplist_exists) }">

					<%-- this question has a pick list and can have multiple answers --%>
					<g:select class="${question_cssclass_value }" name="question_${question_id }"
						from="${question_lookuplist_elements }" multiple="true" optionKey="id" optionValue="display"
						noSelection="['':'-Select One-']"
					/>
				</g:if>
				<g:elseif test="${question_lookuplist_exists }">

					<%-- this question has a pick list, but can only have one answer --%>
					<g:select class="${question_cssclass_value }" name="question_${question_id }"
						from="${question_lookuplist_elements }" optionKey="id" optionValue="display"
						noSelection="['':'-Select One-']"
					/>
				</g:elseif>
				<g:else>
					<g:if test="${question_datatype_value == 'Date' }">
						<g:datePicker class="${question_cssclass_value }" name="question_${question_id }"
							value="${new Date()}" precision="day" noSelection="['':'-Choose-']"
						/>
					</g:if>
					<g:elseif test="${question_datatype_value == 'Number' }">
						<g:field class="${question_cssclass_value }" name="question_${question_id }" type="number" />
					</g:elseif>
					<g:elseif test="${question_datatype_value == 'String' }">
						<g:field class="${question_cssclass_value}" name="question_${question_id }" type="string" />
					</g:elseif>
					<g:else>
						${field_error_exists = true }
					</g:else>
				</g:else>
				<g:if test="${field_error_exists }">
					<g:logMsg level="error">LookupList number ${question_lookuplist_value } (${question_lookuplist_value.name }) does not exist in the database.</g:logMsg>
					<i>LookupList # <b> ${question_lookuplist_value.id }
					</b>, called: <b> ${question_lookuplist_value.name }
					</b> is not yet seeded in the database
					</i>
				</g:if>
			</p>
		</div>
	</g:else>
</g:each>
