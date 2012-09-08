
<%@ page import="org.ihc.esa.domain.Item" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'item.label', default: 'Item')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-item" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-item" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list item">
			
				<g:if test="${itemInstance?.externalId}">
				<li class="fieldcontain">
					<span id="externalId-label" class="property-label"><g:message code="item.externalId.label" default="External Id" /></span>
					
						<span class="property-value" aria-labelledby="externalId-label"><g:fieldValue bean="${itemInstance}" field="externalId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.sourceSystem}">
				<li class="fieldcontain">
					<span id="sourceSystem-label" class="property-label"><g:message code="item.sourceSystem.label" default="Source System" /></span>
					
						<span class="property-value" aria-labelledby="sourceSystem-label"><g:fieldValue bean="${itemInstance}" field="sourceSystem"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.standard}">
				<li class="fieldcontain">
					<span id="standard-label" class="property-label"><g:message code="item.standard.label" default="Standard" /></span>
					
						<span class="property-value" aria-labelledby="standard-label"><g:fieldValue bean="${itemInstance}" field="standard"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.document}">
				<li class="fieldcontain">
					<span id="document-label" class="property-label"><g:message code="item.document.label" default="Document" /></span>
					
						<span class="property-value" aria-labelledby="document-label"><g:link controller="document" action="show" id="${itemInstance?.document?.id}">${itemInstance?.document?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.intermountainItemNumber}">
				<li class="fieldcontain">
					<span id="intermountainItemNumber-label" class="property-label"><g:message code="item.intermountainItemNumber.label" default="Intermountain Item Number" /></span>
					
						<span class="property-value" aria-labelledby="intermountainItemNumber-label"><g:fieldValue bean="${itemInstance}" field="intermountainItemNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="item.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${itemInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="item.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${itemInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.generalLedgerCode}">
				<li class="fieldcontain">
					<span id="generalLedgerCode-label" class="property-label"><g:message code="item.generalLedgerCode.label" default="General Ledger Code" /></span>
					
						<span class="property-value" aria-labelledby="generalLedgerCode-label"><g:fieldValue bean="${itemInstance}" field="generalLedgerCode"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.productGroup}">
				<li class="fieldcontain">
					<span id="productGroup-label" class="property-label"><g:message code="item.productGroup.label" default="Product Group" /></span>
					
						<span class="property-value" aria-labelledby="productGroup-label"><g:fieldValue bean="${itemInstance}" field="productGroup"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.technologyGroup}">
				<li class="fieldcontain">
					<span id="technologyGroup-label" class="property-label"><g:message code="item.technologyGroup.label" default="Technology Group" /></span>
					
						<span class="property-value" aria-labelledby="technologyGroup-label"><g:fieldValue bean="${itemInstance}" field="technologyGroup"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.contract}">
				<li class="fieldcontain">
					<span id="contract-label" class="property-label"><g:message code="item.contract.label" default="Contract" /></span>
					
						<span class="property-value" aria-labelledby="contract-label"><g:link controller="contract" action="show" id="${itemInstance?.contract?.id}">${itemInstance?.contract?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.availableDate}">
				<li class="fieldcontain">
					<span id="availableDate-label" class="property-label"><g:message code="item.availableDate.label" default="Available Date" /></span>
					
						<span class="property-value" aria-labelledby="availableDate-label"><g:formatDate date="${itemInstance?.availableDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.ihcActualDecomissioned}">
				<li class="fieldcontain">
					<span id="ihcActualDecomissioned-label" class="property-label"><g:message code="item.ihcActualDecomissioned.label" default="Ihc Actual Decomissioned" /></span>
					
						<span class="property-value" aria-labelledby="ihcActualDecomissioned-label"><g:formatDate date="${itemInstance?.ihcActualDecomissioned}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.ihcProposedDecomissioned}">
				<li class="fieldcontain">
					<span id="ihcProposedDecomissioned-label" class="property-label"><g:message code="item.ihcProposedDecomissioned.label" default="Ihc Proposed Decomissioned" /></span>
					
						<span class="property-value" aria-labelledby="ihcProposedDecomissioned-label"><g:formatDate date="${itemInstance?.ihcProposedDecomissioned}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.vendorDecomissioned}">
				<li class="fieldcontain">
					<span id="vendorDecomissioned-label" class="property-label"><g:message code="item.vendorDecomissioned.label" default="Vendor Decomissioned" /></span>
					
						<span class="property-value" aria-labelledby="vendorDecomissioned-label"><g:formatDate date="${itemInstance?.vendorDecomissioned}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.vendorCatalogNumber}">
				<li class="fieldcontain">
					<span id="vendorCatalogNumber-label" class="property-label"><g:message code="item.vendorCatalogNumber.label" default="Vendor Catalog Number" /></span>
					
						<span class="property-value" aria-labelledby="vendorCatalogNumber-label"><g:fieldValue bean="${itemInstance}" field="vendorCatalogNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.manufacturerPartId}">
				<li class="fieldcontain">
					<span id="manufacturerPartId-label" class="property-label"><g:message code="item.manufacturerPartId.label" default="Manufacturer Part Id" /></span>
					
						<span class="property-value" aria-labelledby="manufacturerPartId-label"><g:fieldValue bean="${itemInstance}" field="manufacturerPartId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.manufacturerCatalogNumber}">
				<li class="fieldcontain">
					<span id="manufacturerCatalogNumber-label" class="property-label"><g:message code="item.manufacturerCatalogNumber.label" default="Manufacturer Catalog Number" /></span>
					
						<span class="property-value" aria-labelledby="manufacturerCatalogNumber-label"><g:fieldValue bean="${itemInstance}" field="manufacturerCatalogNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.purchasingUnitOfMeasure}">
				<li class="fieldcontain">
					<span id="purchasingUnitOfMeasure-label" class="property-label"><g:message code="item.purchasingUnitOfMeasure.label" default="Purchasing Unit Of Measure" /></span>
					
						<span class="property-value" aria-labelledby="purchasingUnitOfMeasure-label"><g:fieldValue bean="${itemInstance}" field="purchasingUnitOfMeasure"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.purchasingUnitPrice}">
				<li class="fieldcontain">
					<span id="purchasingUnitPrice-label" class="property-label"><g:message code="item.purchasingUnitPrice.label" default="Purchasing Unit Price" /></span>
					
						<span class="property-value" aria-labelledby="purchasingUnitPrice-label"><g:fieldValue bean="${itemInstance}" field="purchasingUnitPrice"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.unspscNumber}">
				<li class="fieldcontain">
					<span id="unspscNumber-label" class="property-label"><g:message code="item.unspscNumber.label" default="Unspsc Number" /></span>
					
						<span class="property-value" aria-labelledby="unspscNumber-label"><g:fieldValue bean="${itemInstance}" field="unspscNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.createdBy}">
				<li class="fieldcontain">
					<span id="createdBy-label" class="property-label"><g:message code="item.createdBy.label" default="Created By" /></span>
					
						<span class="property-value" aria-labelledby="createdBy-label"><g:fieldValue bean="${itemInstance}" field="createdBy"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.updatedBy}">
				<li class="fieldcontain">
					<span id="updatedBy-label" class="property-label"><g:message code="item.updatedBy.label" default="Updated By" /></span>
					
						<span class="property-value" aria-labelledby="updatedBy-label"><g:fieldValue bean="${itemInstance}" field="updatedBy"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.vendorParty}">
				<li class="fieldcontain">
					<span id="vendorParty-label" class="property-label"><g:message code="item.vendorParty.label" default="Vendor Party" /></span>
					
						<span class="property-value" aria-labelledby="vendorParty-label"><g:link controller="party" action="show" id="${itemInstance?.vendorParty?.id}">${itemInstance?.vendorParty?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.belongsToCatalogs}">
				<li class="fieldcontain">
					<span id="belongsToCatalogs-label" class="property-label"><g:message code="item.belongsToCatalogs.label" default="Belongs To Catalogs" /></span>
					
						<g:each in="${itemInstance.belongsToCatalogs}" var="b">
						<span class="property-value" aria-labelledby="belongsToCatalogs-label"><g:link controller="catalogItem" action="show" id="${b.id}">${b?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.configurationElements}">
				<li class="fieldcontain">
					<span id="configurationElements-label" class="property-label"><g:message code="item.configurationElements.label" default="Configuration Elements" /></span>
					
						<g:each in="${itemInstance.configurationElements}" var="c">
						<span class="property-value" aria-labelledby="configurationElements-label"><g:link controller="configurationCatalog" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="item.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${itemInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.itemConversions}">
				<li class="fieldcontain">
					<span id="itemConversions-label" class="property-label"><g:message code="item.itemConversions.label" default="Item Conversions" /></span>
					
						<g:each in="${itemInstance.itemConversions}" var="i">
						<span class="property-value" aria-labelledby="itemConversions-label"><g:link controller="itemUnitsConversion" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.itemVersions}">
				<li class="fieldcontain">
					<span id="itemVersions-label" class="property-label"><g:message code="item.itemVersions.label" default="Item Versions" /></span>
					
						<g:each in="${itemInstance.itemVersions}" var="i">
						<span class="property-value" aria-labelledby="itemVersions-label"><g:link controller="itemVersion" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="item.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${itemInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.partOfConfigurations}">
				<li class="fieldcontain">
					<span id="partOfConfigurations-label" class="property-label"><g:message code="item.partOfConfigurations.label" default="Part Of Configurations" /></span>
					
						<g:each in="${itemInstance.partOfConfigurations}" var="p">
						<span class="property-value" aria-labelledby="partOfConfigurations-label"><g:link controller="configurationCatalog" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.replacementForItems}">
				<li class="fieldcontain">
					<span id="replacementForItems-label" class="property-label"><g:message code="item.replacementForItems.label" default="Replacement For Items" /></span>
					
						<g:each in="${itemInstance.replacementForItems}" var="r">
						<span class="property-value" aria-labelledby="replacementForItems-label"><g:link controller="replacementItem" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.replacementItems}">
				<li class="fieldcontain">
					<span id="replacementItems-label" class="property-label"><g:message code="item.replacementItems.label" default="Replacement Items" /></span>
					
						<g:each in="${itemInstance.replacementItems}" var="r">
						<span class="property-value" aria-labelledby="replacementItems-label"><g:link controller="replacementItem" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${itemInstance?.id}" />
					<g:link class="edit" action="edit" id="${itemInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
