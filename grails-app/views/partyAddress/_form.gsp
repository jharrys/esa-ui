<%@ page import="org.ihc.esa.PartyAddress" %>



<div class="fieldcontain ${hasErrors(bean: partyAddressInstance, field: 'partyId', 'error')} required">
	<label for="partyId">
		<g:message code="partyAddress.partyId.label" default="Party Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="partyId" value="${fieldValue(bean: partyAddressInstance, field: 'partyId')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: partyAddressInstance, field: 'addressId', 'error')} required">
	<label for="addressId">
		<g:message code="partyAddress.addressId.label" default="Address Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="addressId" value="${fieldValue(bean: partyAddressInstance, field: 'addressId')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: partyAddressInstance, field: 'createdBy', 'error')} ">
	<label for="createdBy">
		<g:message code="partyAddress.createdBy.label" default="Created By" />
		
	</label>
	<g:textField name="createdBy" value="${partyAddressInstance?.createdBy}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: partyAddressInstance, field: 'updatedBy', 'error')} ">
	<label for="updatedBy">
		<g:message code="partyAddress.updatedBy.label" default="Updated By" />
		
	</label>
	<g:textField name="updatedBy" value="${partyAddressInstance?.updatedBy}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: partyAddressInstance, field: 'address', 'error')} required">
	<label for="address">
		<g:message code="partyAddress.address.label" default="Address" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="address" name="address.id" from="${org.ihc.esa.Address.list()}" optionKey="id" required="" value="${partyAddressInstance?.address?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: partyAddressInstance, field: 'party', 'error')} required">
	<label for="party">
		<g:message code="partyAddress.party.label" default="Party" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="party" name="party.id" from="${org.ihc.esa.Party.list()}" optionKey="id" required="" value="${partyAddressInstance?.party?.id}" class="many-to-one"/>
</div>

