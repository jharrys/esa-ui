<%@ page import="org.ihc.esa.Document"%>
<%@ page import="org.ihc.esa.Form"%>
<%@ page import="org.ihc.esa.FormField"%>
<%@ page import="org.ihc.esa.LookupList"%>
<%@ page import="org.ihc.esa.LookupElement"%>

<g:logMsg level="debug">===========================================================================</g:logMsg>
<g:logMsg level="debug">Still rendering ... but from template _form for exception saveSection.gsp</g:logMsg>
<g:logMsg level="debug">===========================================================================</g:logMsg>

<g:logMsg level="debug">Begin looping through formFields: "${formFields }"</g:logMsg>
<g:each in="${formFields }" var="field">

	<g:set var="internal_use_only" value="${field.internalOnly == 'Y' ? true:false }" />
	<g:logMsg level="debug">Field: ${field }
	</g:logMsg>
    
    <!-- skip if this question is to be answered only by EISA -->
    <g:if test="${!internal_use_only }">
	    <g:logMsg level="debug">internal_use_only is false, rendering the this field
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
		<g:set var="answer_required" value="${field.required == 'Y' ? true:false }" />
		<g:set var="answer_default" value="${field.defaultValue }" />
		<%
		  answer_default_index = null
		  if(field.lookupList != null) {
			  field.lookupList.elements.find {
				  it.value ==~ /$answer_default/
			  }.each {
			     answer_default_index = it.id 
			  }
		  }
		 %>

        <g:logMsg level="debug">answer_default: ==${answer_default }==</g:logMsg>
        <g:logMsg level="debug">answer_default_index: ==${answer_default_index }==</g:logMsg>
		 
		<g:set var="field_error_exists" value="${false }" />

    
		<!-- if the qr datatype is null it means the field is for display only (no question) -->
		<!-- note that this appropriately does not display the qr with data type "Title" -->
		<g:if test="${!(question_datatype_exists) }">
			<g:logMsg level="debug">question_datatype_exists is false - setting new row with value ${question_id_value }</g:logMsg>
			${question_id_value }
		</g:if>
	
		<g:elseif test="${(!question_datatype_value.equals("Title") && !question_datatype_value.equals("SectionHeader")) }">
			<g:logMsg level="debug">question_datatype_exists is true - creating a question row with question: "${question_id_value }"</g:logMsg>
	
			<div class="control-group">
				<label class="control-label" for="qr${question_id }.value"> ${question_id_value }</label>
				<g:hiddenField name="qr${question_id }-type" value="${question_datatype_value }"/>
	
	            <div class="controls">
				    <g:if test="${(question_can_select_multiple) && (question_lookuplist_exists) }">
						<g:logMsg level="debug">question is multiple select and lookuplist does exist.</g:logMsg>
						<g:select class="${question_cssclass_value }" id="qr${question_id }-value" name="qr${question_id }-value"
							from="${question_lookuplist_elements }" value="${answer_default_index }" multiple="multiple" optionKey="id" optionValue="display" noSelection="['':'-Select One-']"
						/>
					</g:if>
					 
					<g:elseif test="${question_lookuplist_exists }">
						<g:logMsg level="debug">question is not multiple select possible and lookuplist does exist.</g:logMsg>
						<g:logMsg level="debug">lookuplist elements: "${question_lookuplist_elements }"</g:logMsg>
						<g:select class="${question_cssclass_value }" id="qr${question_id }-value" name="qr${question_id }-value"
							from="${question_lookuplist_elements }" value="${answer_default_index }" optionKey="id" optionValue="display" noSelection="['':'-Select One-']" />
					</g:elseif> 
					
					<g:else>
						<g:logMsg level="debug">question is not a select or lookup data type</g:logMsg>
						<g:if test="${question_cssclass_value == 'textArea' }">
							<g:logMsg level="debug">question is ${question_cssclass_value }
							</g:logMsg>
							<g:textArea class="span9" id="qr${question_id }-value" name="qr${question_id }-value" rows="5" placeholder="${answer_default ?: 'type here' }" />
						</g:if>
					
						<g:elseif test="${question_datatype_value.equalsIgnoreCase('DATE_VALUE') }">
							<g:logMsg level="debug">question is ${question_cssclass_value }
							</g:logMsg>
							<input id="qr${question_id }-value" name="qr${question_id }-value" size="16" type="text" value="${dateFormatter.format(new Date()) }">
							<script>
                                $(function() {
                                    $('#qr${question_id }-value').datepicker({format: 'mm/dd/yyyy'});
                                    });
                             </script>
						</g:elseif>
					
						<g:elseif test="${question_datatype_value.equalsIgnoreCase('FLOAT_VALUE') }">
							<g:logMsg level="debug">question is ${question_cssclass_value }
							</g:logMsg>
							<span class="add-on">$</span>
							<input id="qr${question_id }-value" name="qr${question_id }-value" />
							<span class="add-on">.00</span>
						</g:elseif>
					
						<g:elseif test="${question_datatype_value.equalsIgnoreCase('STRING_VALUE') }">
							<g:logMsg level="debug">question is ${question_cssclass_value }
							</g:logMsg>
							<g:field class="${question_cssclass_value}" id="qr${question_id }-value" name="qr${question_id }-value" type="string" placeholder="${answer_default ?: 'type here' }" />
						</g:elseif>
					
						<g:else>
							<g:logMsg level="debug">could not find type of data</g:logMsg>
							${field_error_exists = true }
						</g:else>
					</g:else>
			    </div>
					
					<g:if test="${field_error_exists }">
						<g:logMsg level="error">LookupList number ${question_lookuplist_value } (${question_lookuplist_value }) does not exist in the database.</g:logMsg>
						<p class="error">
							LookupList # (${question_lookuplist_value }): [${question_lookuplist_value }] is not yet seeded in the database
						</p>
					</g:if> 
				
			</div>
		</g:elseif>
		
		<g:else>
			<g:logMsg level="debug">Ignore datatype's with value "Title"</g:logMsg>
		</g:else>
	
	</g:if>
	
</g:each>
