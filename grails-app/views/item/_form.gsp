<%@ page import="org.ihc.esa.Item" %>



<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'externalId', 'error')} ">
	<label for="externalId">
		<g:message code="item.externalId.label" default="External Id" />

	</label>
	<g:textArea name="externalId" cols="40" rows="5" maxlength="256" value="${itemInstance?.externalId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'sourceSystem', 'error')} ">
	<label for="sourceSystem">
		<g:message code="item.sourceSystem.label" default="Source System" />

	</label>
	<g:textArea name="sourceSystem" cols="40" rows="5" maxlength="256" value="${itemInstance?.sourceSystem}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'standard', 'error')} ">
	<label for="standard">
		<g:message code="item.standard.label" default="Standard" />

	</label>
	<g:select name="standard" from="${itemInstance.constraints.standard.inList}" value="${itemInstance?.standard}" valueMessagePrefix="item.standard" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'standardType', 'error')} ">
	<label for="standardType">
		<g:message code="item.standardType.label" default="Standard Type" />

	</label>
	<g:textField name="standardType" maxlength="40" value="${itemInstance?.standardType}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'exception', 'error')} ">
	<label for="exception">
		<g:message code="item.exception.label" default="Exception" />

	</label>
	<g:select name="exception" from="${itemInstance.constraints.exception.inList}" value="${itemInstance?.exception}" valueMessagePrefix="item.exception" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'deviation', 'error')} ">
	<label for="deviation">
		<g:message code="item.deviation.label" default="Deviation" />

	</label>
	<g:select name="deviation" from="${itemInstance.constraints.deviation.inList}" value="${itemInstance?.deviation}" valueMessagePrefix="item.deviation" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'inService', 'error')} ">
	<label for="inService">
		<g:message code="item.inService.label" default="In Service" />

	</label>
	<g:select name="inService" from="${itemInstance.constraints.inService.inList}" value="${itemInstance?.inService}" valueMessagePrefix="item.inService" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'exceptionRequired', 'error')} ">
	<label for="exceptionRequired">
		<g:message code="item.exceptionRequired.label" default="Exception Required" />

	</label>
	<g:select name="exceptionRequired" from="${itemInstance.constraints.exceptionRequired.inList}" value="${itemInstance?.exceptionRequired}" valueMessagePrefix="item.exceptionRequired" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'exceptionCriteria', 'error')} ">
	<label for="exceptionCriteria">
		<g:message code="item.exceptionCriteria.label" default="Exception Criteria" />

	</label>
	<g:textArea name="exceptionCriteria" cols="40" rows="5" maxlength="2048" value="${itemInstance?.exceptionCriteria}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'document', 'error')} ">
	<label for="document">
		<g:message code="item.document.label" default="Document" />

	</label>
	<g:select id="document" name="document.id" from="${org.ihc.esa.Document.list()}" optionKey="id" value="${itemInstance?.document?.id}" class="many-to-one" noSelection="['null': '']"/>
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
	<g:textField name="name" maxlength="128" value="${itemInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="item.description.label" default="Description" />

	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="512" value="${itemInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'generalLedgerCode', 'error')} ">
	<label for="generalLedgerCode">
		<g:message code="item.generalLedgerCode.label" default="General Ledger Code" />

	</label>
	<g:textArea name="generalLedgerCode" cols="40" rows="5" maxlength="256" value="${itemInstance?.generalLedgerCode}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'productGroup', 'error')} ">
	<label for="productGroup">
		<g:message code="item.productGroup.label" default="Product Group" />

	</label>
	<g:textField name="productGroup" maxlength="64" value="${itemInstance?.productGroup}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'technologyGroup', 'error')} ">
	<label for="technologyGroup">
		<g:message code="item.technologyGroup.label" default="Technology Group" />

	</label>
	<g:textField name="technologyGroup" maxlength="64" value="${itemInstance?.technologyGroup}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'contract', 'error')} ">
	<label for="contract">
		<g:message code="item.contract.label" default="Contract" />

	</label>
	<g:select id="contract" name="contract.id" from="${org.ihc.esa.Contract.list()}" optionKey="id" value="${itemInstance?.contract?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'availableDate', 'error')} ">
	<label for="availableDate">
		<g:message code="item.availableDate.label" default="Available Date" />

	</label>
	<g:datePicker name="availableDate" format="yyyy-MM-dd" precision="day"  value="${itemInstance?.availableDate}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'ihcActualDecomissioned', 'error')} ">
	<label for="ihcActualDecomissioned">
		<g:message code="item.ihcActualDecomissioned.label" default="Ihc Actual Decomissioned" />

	</label>
	<g:datePicker name="ihcActualDecomissioned" format="yyyy-MM-dd" precision="day"  value="${itemInstance?.ihcActualDecomissioned}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'ihcProposedDecomissioned', 'error')} ">
	<label for="ihcProposedDecomissioned">
		<g:message code="item.ihcProposedDecomissioned.label" default="Ihc Proposed Decomissioned" />

	</label>
	<g:datePicker name="ihcProposedDecomissioned" format="yyyy-MM-dd" precision="day"  value="${itemInstance?.ihcProposedDecomissioned}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'vendorDecomissioned', 'error')} ">
	<label for="vendorDecomissioned">
		<g:message code="item.vendorDecomissioned.label" default="Vendor Decomissioned" />

	</label>
	<g:datePicker name="vendorDecomissioned" format="yyyy-MM-dd" precision="day"  value="${itemInstance?.vendorDecomissioned}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'vendorCatalogNumber', 'error')} ">
	<label for="vendorCatalogNumber">
		<g:message code="item.vendorCatalogNumber.label" default="Vendor Catalog Number" />

	</label>
	<g:textField name="vendorCatalogNumber" maxlength="20" value="${itemInstance?.vendorCatalogNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'manufacturerParty', 'error')} ">
	<label for="manufacturerParty">
		<g:message code="item.manufacturerParty.label" default="Manufacturer Party" />

	</label>
	<g:field name="manufacturerParty" value="${fieldValue(bean: itemInstance, field: 'manufacturerParty')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'manufacturerCatalogNumber', 'error')} ">
	<label for="manufacturerCatalogNumber">
		<g:message code="item.manufacturerCatalogNumber.label" default="Manufacturer Catalog Number" />

	</label>
	<g:textArea name="manufacturerCatalogNumber" cols="40" rows="5" maxlength="256" value="${itemInstance?.manufacturerCatalogNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'purchasingUnitOfMeasure', 'error')} ">
	<label for="purchasingUnitOfMeasure">
		<g:message code="item.purchasingUnitOfMeasure.label" default="Purchasing Unit Of Measure" />

	</label>
	<g:textField name="purchasingUnitOfMeasure" maxlength="64" value="${itemInstance?.purchasingUnitOfMeasure}"/>
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
	<g:textField name="unspscNumber" maxlength="20" value="${itemInstance?.unspscNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'usefulLife', 'error')} ">
	<label for="usefulLife">
		<g:message code="item.usefulLife.label" default="Useful Life" />

	</label>
	<g:textField name="usefulLife" maxlength="128" value="${itemInstance?.usefulLife}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'comments', 'error')} ">
	<label for="comments">
		<g:message code="item.comments.label" default="Comments" />

	</label>
	<g:textArea name="comments" cols="40" rows="5" maxlength="4000" value="${itemInstance?.comments}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'party', 'error')} ">
	<label for="party">
		<g:message code="item.party.label" default="Party" />

	</label>
	<g:select id="party" name="party.id" from="${org.ihc.esa.Party.list()}" optionKey="id" value="${itemInstance?.party?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'createdBy', 'error')} ">
	<label for="createdBy">
		<g:message code="item.createdBy.label" default="Created By" />

	</label>
	<g:textField name="createdBy" maxlength="40" value="${itemInstance?.createdBy}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'updatedBy', 'error')} ">
	<label for="updatedBy">
		<g:message code="item.updatedBy.label" default="Updated By" />

	</label>
	<g:textField name="updatedBy" maxlength="40" value="${itemInstance?.updatedBy}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'catalogs', 'error')} ">
	<label for="catalogs">
		<g:message code="item.catalogs.label" default="Catalogs" />

	</label>

</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'categories', 'error')} ">
	<label for="categories">
		<g:message code="item.categories.label" default="Categories" />

	</label>

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

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'notes', 'error')} ">
	<label for="notes">
		<g:message code="item.notes.label" default="Notes" />

	</label>

<ul class="one-to-many">
<g:each in="${itemInstance?.notes?}" var="n">
    <li><g:link controller="note" action="show" id="${n.id}">${n?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="note" action="create" params="['item.id': itemInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'note.label', default: 'Note')])}</g:link>
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

