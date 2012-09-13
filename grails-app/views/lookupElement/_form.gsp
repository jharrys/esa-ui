<%@ page import="org.ihc.esa.LookupElement" %>



<div class="fieldcontain ${hasErrors(bean: lookupElementInstance, field: 'value', 'error')} ">
	<label for="value">
		<g:message code="lookupElement.value.label" default="Value" />
		
	</label>
	<g:textField name="value" value="${lookupElementInstance?.value}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: lookupElementInstance, field: 'display', 'error')} ">
	<label for="display">
		<g:message code="lookupElement.display.label" default="Display" />
		
	</label>
	<g:textField name="display" value="${lookupElementInstance?.display}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: lookupElementInstance, field: 'createdBy', 'error')} ">
	<label for="createdBy">
		<g:message code="lookupElement.createdBy.label" default="Created By" />
		
	</label>
	<g:textField name="createdBy" value="${lookupElementInstance?.createdBy}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: lookupElementInstance, field: 'updatedBy', 'error')} ">
	<label for="updatedBy">
		<g:message code="lookupElement.updatedBy.label" default="Updated By" />
		
	</label>
	<g:textField name="updatedBy" value="${lookupElementInstance?.updatedBy}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: lookupElementInstance, field: 'lookupList', 'error')} required">
	<label for="lookupList">
		<g:message code="lookupElement.lookupList.label" default="Lookup List" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="lookupList" name="lookupList.id" from="${org.ihc.esa.LookupList.list()}" optionKey="id" required="" value="${lookupElementInstance?.lookupList?.id}" class="many-to-one"/>
</div>

