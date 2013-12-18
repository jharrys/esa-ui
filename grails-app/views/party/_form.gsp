<%@ page import="org.ihc.esa.Party" %>



<div class="fieldcontain ${hasErrors(bean: partyInstance, field: 'externalId', 'error')} ">
	<label for="externalId">
		<g:message code="party.externalId.label" default="External Id" />
		
	</label>
	<g:textField name="externalId" maxlength="128" value="${partyInstance?.externalId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: partyInstance, field: 'type', 'error')} required">
	<label for="type">
		<g:message code="party.type.label" default="Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="type" maxlength="40" required="" value="${partyInstance?.type}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: partyInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="party.name.label" default="Name" />
		
	</label>
	<g:textField name="name" maxlength="128" value="${partyInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: partyInstance, field: 'emailAddress', 'error')} ">
	<label for="emailAddress">
		<g:message code="party.emailAddress.label" default="Email Address" />
		
	</label>
	<g:field type="email" name="emailAddress" value="${partyInstance?.emailAddress}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: partyInstance, field: 'mobilePhoneNumber', 'error')} ">
	<label for="mobilePhoneNumber">
		<g:message code="party.mobilePhoneNumber.label" default="Mobile Phone Number" />
		
	</label>
	<g:textField name="mobilePhoneNumber" maxlength="15" value="${partyInstance?.mobilePhoneNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: partyInstance, field: 'workPhoneNumber', 'error')} ">
	<label for="workPhoneNumber">
		<g:message code="party.workPhoneNumber.label" default="Work Phone Number" />
		
	</label>
	<g:textField name="workPhoneNumber" maxlength="15" value="${partyInstance?.workPhoneNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: partyInstance, field: 'homePhoneNumber', 'error')} ">
	<label for="homePhoneNumber">
		<g:message code="party.homePhoneNumber.label" default="Home Phone Number" />
		
	</label>
	<g:textField name="homePhoneNumber" maxlength="15" value="${partyInstance?.homePhoneNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: partyInstance, field: 'webSiteUrl', 'error')} ">
	<label for="webSiteUrl">
		<g:message code="party.webSiteUrl.label" default="Web Site Url" />
		
	</label>
	<g:textArea name="webSiteUrl" cols="40" rows="5" maxlength="4000" value="${partyInstance?.webSiteUrl}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: partyInstance, field: 'createdBy', 'error')} ">
	<label for="createdBy">
		<g:message code="party.createdBy.label" default="Created By" />
		
	</label>
	<g:textField name="createdBy" maxlength="40" value="${partyInstance?.createdBy}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: partyInstance, field: 'updatedBy', 'error')} ">
	<label for="updatedBy">
		<g:message code="party.updatedBy.label" default="Updated By" />
		
	</label>
	<g:textField name="updatedBy" maxlength="40" value="${partyInstance?.updatedBy}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: partyInstance, field: 'addresses', 'error')} ">
	<label for="addresses">
		<g:message code="party.addresses.label" default="Addresses" />
		
	</label>
	<g:select name="addresses" from="${org.ihc.esa.Address.list()}" multiple="multiple" optionKey="id" size="5" value="${partyInstance?.addresses*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: partyInstance, field: 'esaUsers', 'error')} ">
	<label for="esaUsers">
		<g:message code="party.esaUsers.label" default="Esa Users" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${partyInstance?.esaUsers?}" var="e">
    <li><g:link controller="esaUser" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="esaUser" action="create" params="['party.id': partyInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'esaUser.label', default: 'EsaUser')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: partyInstance, field: 'items', 'error')} ">
	<label for="items">
		<g:message code="party.items.label" default="Items" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${partyInstance?.items?}" var="i">
    <li><g:link controller="item" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="item" action="create" params="['party.id': partyInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'item.label', default: 'Item')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: partyInstance, field: 'partyRelationshipParty', 'error')} ">
	<label for="partyRelationshipParty">
		<g:message code="party.partyRelationshipParty.label" default="Party Relationship Party" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${partyInstance?.partyRelationshipParty?}" var="p">
    <li><g:link controller="partyRelationship" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="partyRelationship" action="create" params="['party.id': partyInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'partyRelationship.label', default: 'PartyRelationship')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: partyInstance, field: 'partyRelationshipParty1', 'error')} ">
	<label for="partyRelationshipParty1">
		<g:message code="party.partyRelationshipParty1.label" default="Party Relationship Party1" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${partyInstance?.partyRelationshipParty1?}" var="p">
    <li><g:link controller="partyRelationship" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="partyRelationship" action="create" params="['party.id': partyInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'partyRelationship.label', default: 'PartyRelationship')])}</g:link>
</li>
</ul>

</div>

