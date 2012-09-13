<%@ page import="org.ihc.esa.ItemVersion" %>



<div class="fieldcontain ${hasErrors(bean: itemVersionInstance, field: 'item', 'error')} required">
	<label for="item">
		<g:message code="itemVersion.item.label" default="Item" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="item" name="item.id" from="${org.ihc.esa.Item.list()}" optionKey="id" required="" value="${itemVersionInstance?.item?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemVersionInstance, field: 'versionNumber', 'error')} ">
	<label for="versionNumber">
		<g:message code="itemVersion.versionNumber.label" default="Version Number" />
		
	</label>
	<g:textField name="versionNumber" value="${itemVersionInstance?.versionNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemVersionInstance, field: 'ihcActualDecomission', 'error')} ">
	<label for="ihcActualDecomission">
		<g:message code="itemVersion.ihcActualDecomission.label" default="Ihc Actual Decomission" />
		
	</label>
	<g:datePicker name="ihcActualDecomission" precision="day"  value="${itemVersionInstance?.ihcActualDecomission}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: itemVersionInstance, field: 'ihcProposedDecomissioned', 'error')} ">
	<label for="ihcProposedDecomissioned">
		<g:message code="itemVersion.ihcProposedDecomissioned.label" default="Ihc Proposed Decomissioned" />
		
	</label>
	<g:datePicker name="ihcProposedDecomissioned" precision="day"  value="${itemVersionInstance?.ihcProposedDecomissioned}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: itemVersionInstance, field: 'vendorDecomission', 'error')} ">
	<label for="vendorDecomission">
		<g:message code="itemVersion.vendorDecomission.label" default="Vendor Decomission" />
		
	</label>
	<g:datePicker name="vendorDecomission" precision="day"  value="${itemVersionInstance?.vendorDecomission}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: itemVersionInstance, field: 'createdBy', 'error')} ">
	<label for="createdBy">
		<g:message code="itemVersion.createdBy.label" default="Created By" />
		
	</label>
	<g:textField name="createdBy" value="${itemVersionInstance?.createdBy}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemVersionInstance, field: 'updatedBy', 'error')} ">
	<label for="updatedBy">
		<g:message code="itemVersion.updatedBy.label" default="Updated By" />
		
	</label>
	<g:textField name="updatedBy" value="${itemVersionInstance?.updatedBy}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemVersionInstance, field: 'configurationVersionIs', 'error')} ">
	<label for="configurationVersionIs">
		<g:message code="itemVersion.configurationVersionIs.label" default="Configuration Version Is" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${itemVersionInstance?.configurationVersionIs?}" var="c">
    <li><g:link controller="configurationCatalog" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="configurationCatalog" action="create" params="['itemVersion.id': itemVersionInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'configurationCatalog.label', default: 'ConfigurationCatalog')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: itemVersionInstance, field: 'elementVersionIs', 'error')} ">
	<label for="elementVersionIs">
		<g:message code="itemVersion.elementVersionIs.label" default="Element Version Is" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${itemVersionInstance?.elementVersionIs?}" var="e">
    <li><g:link controller="configurationCatalog" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="configurationCatalog" action="create" params="['itemVersion.id': itemVersionInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'configurationCatalog.label', default: 'ConfigurationCatalog')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: itemVersionInstance, field: 'usingVersion', 'error')} ">
	<label for="usingVersion">
		<g:message code="itemVersion.usingVersion.label" default="Using Version" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${itemVersionInstance?.usingVersion?}" var="u">
    <li><g:link controller="catalogItem" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="catalogItem" action="create" params="['itemVersion.id': itemVersionInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'catalogItem.label', default: 'CatalogItem')])}</g:link>
</li>
</ul>

</div>

