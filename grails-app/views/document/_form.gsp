<%@ page import="org.ihc.esa.domain.Document" %>



<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'form', 'error')} required">
	<label for="form">
		<g:message code="document.form.label" default="Form" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="form" name="form.id" from="${org.ihc.esa.domain.Form.list()}" optionKey="id" required="" value="${documentInstance?.form?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'sirpId', 'error')} ">
	<label for="sirpId">
		<g:message code="document.sirpId.label" default="Sirp Id" />
		
	</label>
	<g:textField name="sirpId" value="${documentInstance?.sirpId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'requestor', 'error')} ">
	<label for="requestor">
		<g:message code="document.requestor.label" default="Requestor" />
		
	</label>
	<g:textField name="requestor" value="${documentInstance?.requestor}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'requestorEmail', 'error')} ">
	<label for="requestorEmail">
		<g:message code="document.requestorEmail.label" default="Requestor Email" />
		
	</label>
	<g:textField name="requestorEmail" value="${documentInstance?.requestorEmail}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'owner', 'error')} ">
	<label for="owner">
		<g:message code="document.owner.label" default="Owner" />
		
	</label>
	<g:textField name="owner" value="${documentInstance?.owner}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'ownerEmail', 'error')} ">
	<label for="ownerEmail">
		<g:message code="document.ownerEmail.label" default="Owner Email" />
		
	</label>
	<g:textField name="ownerEmail" value="${documentInstance?.ownerEmail}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'justification', 'error')} ">
	<label for="justification">
		<g:message code="document.justification.label" default="Justification" />
		
	</label>
	<g:textField name="justification" value="${documentInstance?.justification}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'vendorRepresentativeParty', 'error')} ">
	<label for="vendorRepresentativeParty">
		<g:message code="document.vendorRepresentativeParty.label" default="Vendor Representative Party" />
		
	</label>
	<g:select id="vendorRepresentativeParty" name="vendorRepresentativeParty.id" from="${org.ihc.esa.domain.Party.list()}" optionKey="id" value="${documentInstance?.vendorRepresentativeParty?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'createdBy', 'error')} ">
	<label for="createdBy">
		<g:message code="document.createdBy.label" default="Created By" />
		
	</label>
	<g:textField name="createdBy" value="${documentInstance?.createdBy}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'updatedBy', 'error')} ">
	<label for="updatedBy">
		<g:message code="document.updatedBy.label" default="Updated By" />
		
	</label>
	<g:textField name="updatedBy" value="${documentInstance?.updatedBy}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'items', 'error')} ">
	<label for="items">
		<g:message code="document.items.label" default="Items" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${documentInstance?.items?}" var="i">
    <li><g:link controller="item" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="item" action="create" params="['document.id': documentInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'item.label', default: 'Item')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'responses', 'error')} ">
	<label for="responses">
		<g:message code="document.responses.label" default="Responses" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${documentInstance?.responses?}" var="r">
    <li><g:link controller="questionResponse" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="questionResponse" action="create" params="['document.id': documentInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'questionResponse.label', default: 'QuestionResponse')])}</g:link>
</li>
</ul>

</div>

