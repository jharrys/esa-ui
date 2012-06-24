<%@ page import="org.ihc.esa.domain.Address" %>



<div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'address_line1', 'error')} ">
	<label for="address_line1">
		<g:message code="address.address_line1.label" default="Addressline1" />
		
	</label>
	<g:textField name="address_line1" value="${addressInstance?.address_line1}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'address_line2', 'error')} ">
	<label for="address_line2">
		<g:message code="address.address_line2.label" default="Addressline2" />
		
	</label>
	<g:textField name="address_line2" value="${addressInstance?.address_line2}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'address_line3', 'error')} ">
	<label for="address_line3">
		<g:message code="address.address_line3.label" default="Addressline3" />
		
	</label>
	<g:textField name="address_line3" value="${addressInstance?.address_line3}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'address_line4', 'error')} ">
	<label for="address_line4">
		<g:message code="address.address_line4.label" default="Addressline4" />
		
	</label>
	<g:textField name="address_line4" value="${addressInstance?.address_line4}"/>
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

<div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'created_by', 'error')} ">
	<label for="created_by">
		<g:message code="address.created_by.label" default="Createdby" />
		
	</label>
	<g:textField name="created_by" value="${addressInstance?.created_by}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'parties', 'error')} ">
	<label for="parties">
		<g:message code="address.parties.label" default="Parties" />
		
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'updated_by', 'error')} ">
	<label for="updated_by">
		<g:message code="address.updated_by.label" default="Updatedby" />
		
	</label>
	<g:textField name="updated_by" value="${addressInstance?.updated_by}"/>
</div>

