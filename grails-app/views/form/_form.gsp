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
		<g:set var="lookupList" value="${field.lookupList?.id }" />
		<g:set var="dataType" value="${field.dataType }" />
		<g:set var="multiSelect" value="${field.multiSelect }" />

		${field.question }
		<g:if test="${lookupList != null }" >
			<g:if test="${multiSelect != 'N' }" >
				MultiSelect is ON
				<!-- 
				g:select name="NAME"
						 from="LOOKUPLIST"
						 value="LOOKUPLIST.VALUE"
						 optionKey="id"
						 multiple="true"
				 -->
			</g:if>
			<g:else>
				MultiSelect is OFF
				<!-- 
				g:select name="NAME"
						 from="LOOKUPLIST"
						 value="LOOKUPLIST.VALUE"
						 optionKey="id"
						 multiple="false"
				 -->			
			</g:else>
		</g:if>
		<g:else>
			No list to lookup
		</g:else>
	</div>

</g:each>
