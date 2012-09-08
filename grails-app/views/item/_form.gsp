<%@ page import="org.ihc.esa.domain.Item" %>



<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'externalId', 'error')} ">
	<label for="externalId">
		<g:message code="item.externalId.label" default="External Id" />
		
	</label>
	<g:textField name="externalId" value="${itemInstance?.externalId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'sourceSystem', 'error')} ">
	<label for="sourceSystem">
		<g:message code="item.sourceSystem.label" default="Source System" />
		
	</label>
	<g:textField name="sourceSystem" value="${itemInstance?.sourceSystem}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'standard', 'error')} ">
	<label for="standard">
		<g:message code="item.standard.label" default="Standard" />
		
	</label>
	<g:select name="standard" from="${itemInstance.constraints.standard.inList}" value="${itemInstance?.standard}" valueMessagePrefix="item.standard" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'document', 'error')} ">
	<label for="document">
		<g:message code="item.document.label" default="Document" />
		
	</label>
	<g:select id="document" name="document.id" from="${org.ihc.esa.domain.Document.list()}" optionKey="id" value="${itemInstance?.document?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'intermountainItemNumber', 'error')} ">
	<label for="intermountainItemNumber">
		<g:message code="item.intermountainItemNumber.label" default="Intermountain Item Number" />
		
	</label>
	<g:field name="intermountainItemNumber" value="${fieldValue(bean: itemInstance, field: 'intermountainItemNumber')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="item.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${itemInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="item.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${itemInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'generalLedgerCode', 'error')} ">
	<label for="generalLedgerCode">
		<g:message code="item.generalLedgerCode.label" default="General Ledger Code" />
		
	</label>
	<g:textField name="generalLedgerCode" value="${itemInstance?.generalLedgerCode}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'productGroup', 'error')} ">
	<label for="productGroup">
		<g:message code="item.productGroup.label" default="Product Group" />
		
	</label>
	<g:textField name="productGroup" value="${itemInstance?.productGroup}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'technologyGroup', 'error')} ">
	<label for="technologyGroup">
		<g:message code="item.technologyGroup.label" default="Technology Group" />
		
	</label>
	<g:textField name="technologyGroup" value="${itemInstance?.technologyGroup}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'contract', 'error')} ">
	<label for="contract">
		<g:message code="item.contract.label" default="Contract" />
		
	</label>
	<g:select id="contract" name="contract.id" from="${org.ihc.esa.domain.Contract.list()}" optionKey="id" value="${itemInstance?.contract?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'availableDate', 'error')} ">
	<label for="availableDate">
		<g:message code="item.availableDate.label" default="Available Date" />
		
	</label>
	<g:datePicker name="availableDate" precision="day"  value="${itemInstance?.availableDate}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'ihcActualDecomissioned', 'error')} ">
	<label for="ihcActualDecomissioned">
		<g:message code="item.ihcActualDecomissioned.label" default="Ihc Actual Decomissioned" />
		
	</label>
	<g:datePicker name="ihcActualDecomissioned" precision="day"  value="${itemInstance?.ihcActualDecomissioned}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'ihcProposedDecomissioned', 'error')} ">
	<label for="ihcProposedDecomissioned">
		<g:message code="item.ihcProposedDecomissioned.label" default="Ihc Proposed Decomissioned" />
		
	</label>
	<g:datePicker name="ihcProposedDecomissioned" precision="day"  value="${itemInstance?.ihcProposedDecomissioned}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'vendorDecomissioned', 'error')} ">
	<label for="vendorDecomissioned">
		<g:message code="item.vendorDecomissioned.label" default="Vendor Decomissioned" />
		
	</label>
	<g:datePicker name="vendorDecomissioned" precision="day"  value="${itemInstance?.vendorDecomissioned}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'vendorCatalogNumber', 'error')} ">
	<label for="vendorCatalogNumber">
		<g:message code="item.vendorCatalogNumber.label" default="Vendor Catalog Number" />
		
	</label>
	<g:textField name="vendorCatalogNumber" value="${itemInstance?.vendorCatalogNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'manufacturerPartId', 'error')} ">
	<label for="manufacturerPartId">
		<g:message code="item.manufacturerPartId.label" default="Manufacturer Part Id" />
		
	</label>
	<g:field name="manufacturerPartId" value="${fieldValue(bean: itemInstance, field: 'manufacturerPartId')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'manufacturerCatalogNumber', 'error')} ">
	<label for="manufacturerCatalogNumber">
		<g:message code="item.manufacturerCatalogNumber.label" default="Manufacturer Catalog Number" />
		
	</label>
	<g:textField name="manufacturerCatalogNumber" value="${itemInstance?.manufacturerCatalogNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'purchasingUnitOfMeasure', 'error')} ">
	<label for="purchasingUnitOfMeasure">
		<g:message code="item.purchasingUnitOfMeasure.label" default="Purchasing Unit Of Measure" />
		
	</label>
	<g:textField name="purchasingUnitOfMeasure" value="${itemInstance?.purchasingUnitOfMeasure}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'purchasingUnitPrice', 'error')} ">
	<label for="purchasingUnitPrice">
		<g:message code="item.purchasingUnitPrice.label" default="Purchasing Unit Price" />
		
	</label>
	<g:field name="purchasingUnitPrice" value="${fieldValue(bean: itemInstance, field: 'purchasingUnitPrice')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'unspscNumber', 'error')} ">
	<label for="unspscNumber">
		<g:message code="item.unspscNumber.label" default="Unspsc Number" />
		
	</label>
	<g:textField name="unspscNumber" value="${itemInstance?.unspscNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'createdBy', 'error')} ">
	<label for="createdBy">
		<g:message code="item.createdBy.label" default="Created By" />
		
	</label>
	<g:textField name="createdBy" value="${itemInstance?.createdBy}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'updatedBy', 'error')} ">
	<label for="updatedBy">
		<g:message code="item.updatedBy.label" default="Updated By" />
		
	</label>
	<g:textField name="updatedBy" value="${itemInstance?.updatedBy}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'vendorParty', 'error')} ">
	<label for="vendorParty">
		<g:message code="item.vendorParty.label" default="Vendor Party" />
		
	</label>
	<g:select id="vendorParty" name="vendorParty.id" from="${org.ihc.esa.domain.Party.list()}" optionKey="id" value="${itemInstance?.vendorParty?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'belongsToCatalogs', 'error')} ">
	<label for="belongsToCatalogs">
		<g:message code="item.belongsToCatalogs.label" default="Belongs To Catalogs" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${itemInstance?.belongsToCatalogs?}" var="b">
    <li><g:link controller="catalogItem" action="show" id="${b.id}">${b?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="catalogItem" action="create" params="['item.id': itemInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'catalogItem.label', default: 'CatalogItem')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'configurationElements', 'error')} ">
	<label for="configurationElements">
		<g:message code="item.configurationElements.label" default="Configuration Elements" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${itemInstance?.configurationElements?}" var="c">
    <li><g:link controller="configurationCatalog" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="configurationCatalog" action="create" params="['item.id': itemInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'configurationCatalog.label', default: 'ConfigurationCatalog')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'itemConversions', 'error')} ">
	<label for="itemConversions">
		<g:message code="item.itemConversions.label" default="Item Conversions" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${itemInstance?.itemConversions?}" var="i">
    <li><g:link controller="itemUnitsConversion" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="itemUnitsConversion" action="create" params="['item.id': itemInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'itemUnitsConversion.label', default: 'ItemUnitsConversion')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'itemVersions', 'error')} ">
	<label for="itemVersions">
		<g:message code="item.itemVersions.label" default="Item Versions" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${itemInstance?.itemVersions?}" var="i">
    <li><g:link controller="itemVersion" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="itemVersion" action="create" params="['item.id': itemInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'itemVersion.label', default: 'ItemVersion')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'partOfConfigurations', 'error')} ">
	<label for="partOfConfigurations">
		<g:message code="item.partOfConfigurations.label" default="Part Of Configurations" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${itemInstance?.partOfConfigurations?}" var="p">
    <li><g:link controller="configurationCatalog" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="configurationCatalog" action="create" params="['item.id': itemInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'configurationCatalog.label', default: 'ConfigurationCatalog')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'replacementForItems', 'error')} ">
	<label for="replacementForItems">
		<g:message code="item.replacementForItems.label" default="Replacement For Items" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${itemInstance?.replacementForItems?}" var="r">
    <li><g:link controller="replacementItem" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="replacementItem" action="create" params="['item.id': itemInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'replacementItem.label', default: 'ReplacementItem')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'replacementItems', 'error')} ">
	<label for="replacementItems">
		<g:message code="item.replacementItems.label" default="Replacement Items" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${itemInstance?.replacementItems?}" var="r">
    <li><g:link controller="replacementItem" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="replacementItem" action="create" params="['item.id': itemInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'replacementItem.label', default: 'ReplacementItem')])}</g:link>
</li>
</ul>

</div>

