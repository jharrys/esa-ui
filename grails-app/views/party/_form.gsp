<%@ page import="org.ihc.esa.Party" %>



<div class="fieldcontain ${hasErrors(bean: partyInstance, field: 'externalId', 'error')} ">
	<label for="externalId">
		<g:message code="party.externalId.label" default="External Id" />
		
	</label>
	<g:textField name="externalId" value="${partyInstance?.externalId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: partyInstance, field: 'type', 'error')} ">
	<label for="type">
		<g:message code="party.type.label" default="Type" />
		
	</label>
	<g:textField name="type" value="${partyInstance?.type}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: partyInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="party.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${partyInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: partyInstance, field: 'emailAddress', 'error')} ">
	<label for="emailAddress">
		<g:message code="party.emailAddress.label" default="Email Address" />
		
	</label>
	<g:textField name="emailAddress" value="${partyInstance?.emailAddress}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: partyInstance, field: 'mobilePhoneNumber', 'error')} ">
	<label for="mobilePhoneNumber">
		<g:message code="party.mobilePhoneNumber.label" default="Mobile Phone Number" />
		
	</label>
	<g:textField name="mobilePhoneNumber" value="${partyInstance?.mobilePhoneNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: partyInstance, field: 'workPhoneNumber', 'error')} ">
	<label for="workPhoneNumber">
		<g:message code="party.workPhoneNumber.label" default="Work Phone Number" />
		
	</label>
	<g:textField name="workPhoneNumber" value="${partyInstance?.workPhoneNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: partyInstance, field: 'homePhoneNumber', 'error')} ">
	<label for="homePhoneNumber">
		<g:message code="party.homePhoneNumber.label" default="Home Phone Number" />
		
	</label>
	<g:textField name="homePhoneNumber" value="${partyInstance?.homePhoneNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: partyInstance, field: 'webSiteUrl', 'error')} ">
	<label for="webSiteUrl">
		<g:message code="party.webSiteUrl.label" default="Web Site Url" />
		
	</label>
	<g:textField name="webSiteUrl" value="${partyInstance?.webSiteUrl}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: partyInstance, field: 'createdBy', 'error')} ">
	<label for="createdBy">
		<g:message code="party.createdBy.label" default="Created By" />
		
	</label>
	<g:textField name="createdBy" value="${partyInstance?.createdBy}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: partyInstance, field: 'updatedBy', 'error')} ">
	<label for="updatedBy">
		<g:message code="party.updatedBy.label" default="Updated By" />
		
	</label>
	<g:textField name="updatedBy" value="${partyInstance?.updatedBy}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: partyInstance, field: 'partyAddressParty', 'error')} ">
	<label for="partyAddressParty">
		<g:message code="party.partyAddressParty.label" default="Party Address Party" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${partyInstance?.partyAddressParty?}" var="p">
    <li><g:link controller="partyAddress" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="partyAddress" action="create" params="['party.id': partyInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'partyAddress.label', default: 'PartyAddress')])}</g:link>
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

<div class="fieldcontain ${hasErrors(bean: partyInstance, field: 'vendorItem', 'error')} ">
	<label for="vendorItem">
		<g:message code="party.vendorItem.label" default="Vendor Item" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${partyInstance?.vendorItem?}" var="v">
    <li><g:link controller="item" action="show" id="${v.id}">${v?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="item" action="create" params="['party.id': partyInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'item.label', default: 'Item')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: partyInstance, field: 'vendorRepresentative', 'error')} ">
	<label for="vendorRepresentative">
		<g:message code="party.vendorRepresentative.label" default="Vendor Representative" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${partyInstance?.vendorRepresentative?}" var="v">
    <li><g:link controller="document" action="show" id="${v.id}">${v?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="document" action="create" params="['party.id': partyInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'document.label', default: 'Document')])}</g:link>
</li>
</ul>

</div>

