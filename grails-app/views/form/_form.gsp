<%@ page import="org.ihc.esa.domain.Document"%>
<%@ page import="org.ihc.esa.domain.Form"%>
<%@ page import="org.ihc.esa.domain.FormField"%>
<%@ page import="org.ihc.esa.domain.LookupList"%>
<%@ page import="org.ihc.esa.domain.LookupElement"%>

<g:each in="${formFields }" var="field">
	<g:set var="fid" value="${field.id }" />
	<div class="question ${fid} ">
		<g:set var="multipleSelect"
			value="${field.multiSelect == 'Y' ? true:false }" />
		<g:if test="${field.dataType != null }" >
        <%-- TODO this is ugly, fix --%>
		  <div class="question"><b> ${field.question }</b><br></div>
		</g:if>
		<g:if test="${field.lookupList != null }">
			<g:if
				test="${multipleSelect && field.lookupList.elements.size() > 0 }">
				<g:select class="select ${fid }" name="select_${fid }"
					from="${field.lookupList.elements }" multiple="${multipleSelect }"
					optionKey="id" optionValue="display"
					noSelection="['':'-Select One-']" />
			</g:if>
			<g:else>
				<g:if test="${field.lookupList.elements.size() > 0 }">
					<g:select class="select ${fid }" name="select_${fid }"
						from="${field.lookupList.elements }" optionKey="id"
						optionValue="display" noSelection="['':'-Select One-']" />
				</g:if>
				<g:else>
					<g:logMsg level="error">LookupList number ${field.lookupList.id } (${field.lookupList.name }) does not exist in the database.</g:logMsg>
					<i>LookupList # <b> ${field.lookupList.id }
					</b>, called: <b> ${field.lookupList.name }
					</b> is not yet seeded in the database
					</i>
				</g:else>
			</g:else>
		</g:if>
		<g:else>
            <g:if test="${field.dataType == null }">
                <div class="statement"> <p><b>${field.question }</b></p><br> </div>
            </g:if>
			<g:elseif test="${field.dataType == 'Date' }">
				<g:datePicker class="date ${fid }" name="date_${fid }"
					value="${new Date()}" precision="day" noSelection="['':'-Choose-']" />
			</g:elseif>
			<g:elseif test="${field.dataType == 'Number' }">
				<g:field class="number ${fid }" name="number_${fid }" type="number" />
			</g:elseif>
			<g:elseif test="${field.dataType == 'String' }">
				<g:field class="string ${fid }" name="string_${fid }" type="string" />
			</g:elseif>
			<g:else>
				<g:logMsg level="error">FormField of type [${field.dataType }] found in question number [${fid }] does not have a corresponding match in this /form/_form template.</g:logMsg>
				FormField of type [${field.dataType }] found in question number [${fid }] has not been setup. Please ask site admin to fix.
			</g:else>
		</g:else>
	</div>

</g:each>
