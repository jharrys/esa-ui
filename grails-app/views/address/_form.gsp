<%@ page import="org.ihc.esa.domain.Address" %>



<div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'addressLine1', 'error')} ">
	<label for="addressLine1">
		<g:message code="address.addressLine1.label" default="Address Line1" />
		
	</label>
	<g:textField name="addressLine1" value="${addressInstance?.addressLine1}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'addressLine2', 'error')} ">
	<label for="addressLine2">
		<g:message code="address.addressLine2.label" default="Address Line2" />
		
	</label>
	<g:textField name="addressLine2" value="${addressInstance?.addressLine2}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'addressLine3', 'error')} ">
	<label for="addressLine3">
		<g:message code="address.addressLine3.label" default="Address Line3" />
		
	</label>
	<g:textField name="addressLine3" value="${addressInstance?.addressLine3}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'addressLine4', 'error')} ">
	<label for="addressLine4">
		<g:message code="address.addressLine4.label" default="Address Line4" />
		
	</label>
	<g:textField name="addressLine4" value="${addressInstance?.addressLine4}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'city', 'error')} ">
	<label for="city">
		<g:message code="address.city.label" default="City" />
		
	</label>
	<g:textField name="city" value="${addressInstance?.city}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'state', 'error')} ">
	<label for="state">
		<g:message code="address.state.label" default="State" />
		
	</label>
	<g:textField name="state" value="${addressInstance?.state}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'zip', 'error')} ">
	<label for="zip">
		<g:message code="address.zip.label" default="Zip" />
		
	</label>
	<g:textField name="zip" value="${addressInstance?.zip}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'dateCreated', 'error')} required">
	<label for="dateCreated">
		<g:message code="address.dateCreated.label" default="Creation Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateCreated" precision="day"  value="${addressInstance?.dateCreated}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'createdBy', 'error')} ">
	<label for="createdBy">
		<g:message code="address.createdBy.label" default="Created By" />
		
	</label>
	<g:textField name="createdBy" value="${addressInstance?.createdBy}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'lastUpdated', 'error')} required">
	<label for="lastUpdated">
		<g:message code="address.lastUpdated.label" default="Update Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="lastUpdated" precision="day"  value="${addressInstance?.lastUpdated}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'updatedBy', 'error')} ">
	<label for="updatedBy">
		<g:message code="address.updatedBy.label" default="Updated By" />
		
	</label>
	<g:textField name="updatedBy" value="${addressInstance?.updatedBy}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'partyAddressAddress', 'error')} ">
	<label for="partyAddressAddress">
		<g:message code="address.partyAddressAddress.label" default="Party Address Address" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${addressInstance?.partyAddressAddress?}" var="p">
    <li><g:link controller="partyAddress" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="partyAddress" action="create" params="['address.id': addressInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'partyAddress.label', default: 'PartyAddress')])}</g:link>
</li>
</ul>

</div>

