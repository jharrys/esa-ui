<%@ page import="org.ihc.esa.LookupList" %>



<div class="fieldcontain ${hasErrors(bean: lookupListInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="lookupList.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${lookupListInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: lookupListInstance, field: 'prompt', 'error')} ">
	<label for="prompt">
		<g:message code="lookupList.prompt.label" default="Prompt" />
		
	</label>
	<g:textField name="prompt" value="${lookupListInstance?.prompt}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: lookupListInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="lookupList.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${lookupListInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: lookupListInstance, field: 'listType', 'error')} ">
	<label for="listType">
		<g:message code="lookupList.listType.label" default="List Type" />
		
	</label>
	<g:textField name="listType" value="${lookupListInstance?.listType}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: lookupListInstance, field: 'sqlStatement', 'error')} ">
	<label for="sqlStatement">
		<g:message code="lookupList.sqlStatement.label" default="Sql Statement" />
		
	</label>
	<g:textField name="sqlStatement" value="${lookupListInstance?.sqlStatement}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: lookupListInstance, field: 'createdBy', 'error')} ">
	<label for="createdBy">
		<g:message code="lookupList.createdBy.label" default="Created By" />
		
	</label>
	<g:textField name="createdBy" value="${lookupListInstance?.createdBy}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: lookupListInstance, field: 'updatedBy', 'error')} ">
	<label for="updatedBy">
		<g:message code="lookupList.updatedBy.label" default="Updated By" />
		
	</label>
	<g:textField name="updatedBy" value="${lookupListInstance?.updatedBy}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: lookupListInstance, field: 'elements', 'error')} ">
	<label for="elements">
		<g:message code="lookupList.elements.label" default="Elements" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${lookupListInstance?.elements?}" var="e">
    <li><g:link controller="lookupElement" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="lookupElement" action="create" params="['lookupList.id': lookupListInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'lookupElement.label', default: 'LookupElement')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: lookupListInstance, field: 'fieldLookup', 'error')} ">
	<label for="fieldLookup">
		<g:message code="lookupList.fieldLookup.label" default="Field Lookup" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${lookupListInstance?.fieldLookup?}" var="f">
    <li><g:link controller="formField" action="show" id="${f.id}">${f?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="formField" action="create" params="['lookupList.id': lookupListInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'formField.label', default: 'FormField')])}</g:link>
</li>
</ul>

</div>

