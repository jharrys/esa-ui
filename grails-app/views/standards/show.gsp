<%@ page import="org.ihc.esa.Item" %>
<!doctype html>
<html>
	<head>
		<g:set var="entityName" value="${message(code: 'item.label', default: 'Item')}" />
		<title>Item</title>
	</head>
	<body>
			
			<div class="span10">

				<div class="page-header">
					<h1>Details for ${itemInstance?.name }</h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<dl>
				
					<g:if test="${itemInstance?.standard}">
						<dt><g:message code="item.standard.label" default="Standard" /></dt>
						
							<dd><g:fieldValue bean="${itemInstance}" field="standard"/></dd>
						
					</g:if>
				
					<g:if test="${itemInstance?.standardType}">
						<dt><g:message code="item.standardType.label" default="Standard Type" /></dt>
						
							<dd><g:fieldValue bean="${itemInstance}" field="standardType"/></dd>
						
					</g:if>
				
					<g:if test="${itemInstance?.exception}">
						<dt><g:message code="item.exception.label" default="Exception" /></dt>
						
							<dd><g:fieldValue bean="${itemInstance}" field="exception"/></dd>
						
					</g:if>
				
					<g:if test="${itemInstance?.deviation}">
						<dt><g:message code="item.deviation.label" default="Deviation" /></dt>
						
							<dd><g:fieldValue bean="${itemInstance}" field="deviation"/></dd>
						
					</g:if>
				
					<g:if test="${itemInstance?.inService}">
						<dt><g:message code="item.inService.label" default="In Service" /></dt>
						
							<dd><g:fieldValue bean="${itemInstance}" field="inService"/></dd>
						
					</g:if>
				
					<g:if test="${itemInstance?.exceptionRequired}">
						<dt><g:message code="item.exceptionRequired.label" default="Exception Required" /></dt>
						
							<dd><g:fieldValue bean="${itemInstance}" field="exceptionRequired"/></dd>
						
					</g:if>
				
					<g:if test="${itemInstance?.exceptionCriteria}">
						<dt><g:message code="item.exceptionCriteria.label" default="Exception Criteria" /></dt>
						
							<dd><g:fieldValue bean="${itemInstance}" field="exceptionCriteria"/></dd>
						
					</g:if>
				
					<g:if test="${itemInstance?.document}">
						<dt><g:message code="item.document.label" default="Document" /></dt>
						
							<dd><g:link controller="document" action="show" id="${itemInstance?.document?.id}">${itemInstance?.document?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${itemInstance?.description}">
						<dt><g:message code="item.description.label" default="Description" /></dt>
						
							<dd><g:fieldValue bean="${itemInstance}" field="description"/></dd>
						
					</g:if>
				
					<g:if test="${itemInstance?.generalLedgerCode}">
						<dt><g:message code="item.generalLedgerCode.label" default="General Ledger Code" /></dt>
						
							<dd><g:fieldValue bean="${itemInstance}" field="generalLedgerCode"/></dd>
						
					</g:if>
				
					<g:if test="${itemInstance?.productGroup}">
						<dt><g:message code="item.productGroup.label" default="Product Group" /></dt>
						
							<dd><g:fieldValue bean="${itemInstance}" field="productGroup"/></dd>
						
					</g:if>
				
					<g:if test="${itemInstance?.technologyGroup}">
						<dt><g:message code="item.technologyGroup.label" default="Technology Group" /></dt>
						
							<dd><g:fieldValue bean="${itemInstance}" field="technologyGroup"/></dd>
						
					</g:if>
				
					<g:if test="${itemInstance?.contract}">
						<dt><g:message code="item.contract.label" default="Contract" /></dt>
						
							<dd><g:link controller="contract" action="show" id="${itemInstance?.contract?.id}">${itemInstance?.contract?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${itemInstance?.availableDate}">
						<dt><g:message code="item.availableDate.label" default="Available Date" /></dt>
						
							<dd><g:formatDate format="MM-dd-yyyy" date="${itemInstance?.availableDate}" /></dd>
						
					</g:if>
				
					<g:if test="${itemInstance?.ihcActualDecomissioned}">
						<dt><g:message code="item.ihcActualDecomissioned.label" default="Ihc Actual Decomissioned" /></dt>
						
							<dd><g:formatDate format="MM-dd-yyyy" date="${itemInstance?.ihcActualDecomissioned}" /></dd>
						
					</g:if>
				
					<g:if test="${itemInstance?.ihcProposedDecomissioned}">
						<dt><g:message code="item.ihcProposedDecomissioned.label" default="Ihc Proposed Decomissioned" /></dt>
						
							<dd><g:formatDate format="MM-dd-yyyy" date="${itemInstance?.ihcProposedDecomissioned}" /></dd>
						
					</g:if>
				
					<g:if test="${itemInstance?.vendorDecomissioned}">
						<dt><g:message code="item.vendorDecomissioned.label" default="Vendor Decomissioned" /></dt>
						
							<dd><g:formatDate format="MM-dd-yyyy" date="${itemInstance?.vendorDecomissioned}" /></dd>
						
					</g:if>
				
					<g:if test="${itemInstance?.vendorCatalogNumber}">
						<dt><g:message code="item.vendorCatalogNumber.label" default="Vendor Catalog Number" /></dt>
						
							<dd><g:fieldValue bean="${itemInstance}" field="vendorCatalogNumber"/></dd>
						
					</g:if>
				
					<g:if test="${itemInstance?.manufacturerPartId}">
						<dt><g:message code="item.manufacturerPartId.label" default="Manufacturer Part Id" /></dt>
						
							<dd><g:fieldValue bean="${itemInstance}" field="manufacturerPartId"/></dd>
						
					</g:if>
				
					<g:if test="${itemInstance?.manufacturerCatalogNumber}">
						<dt><g:message code="item.manufacturerCatalogNumber.label" default="Manufacturer Catalog Number" /></dt>
						
							<dd><g:fieldValue bean="${itemInstance}" field="manufacturerCatalogNumber"/></dd>
						
					</g:if>
				
					<g:if test="${itemInstance?.purchasingUnitOfMeasure}">
						<dt><g:message code="item.purchasingUnitOfMeasure.label" default="Purchasing Unit Of Measure" /></dt>
						
							<dd><g:fieldValue bean="${itemInstance}" field="purchasingUnitOfMeasure"/></dd>
						
					</g:if>
				
					<g:if test="${itemInstance?.purchasingUnitPrice}">
						<dt><g:message code="item.purchasingUnitPrice.label" default="Purchasing Unit Price" /></dt>
						
							<dd><g:fieldValue bean="${itemInstance}" field="purchasingUnitPrice"/></dd>
						
					</g:if>
				
					<g:if test="${itemInstance?.unspscNumber}">
						<dt><g:message code="item.unspscNumber.label" default="Unspsc Number" /></dt>
						
							<dd><g:fieldValue bean="${itemInstance}" field="unspscNumber"/></dd>
						
					</g:if>
				
					<g:if test="${itemInstance?.usefulLife}">
						<dt><g:message code="item.usefulLife.label" default="Useful Life" /></dt>
						
							<dd><g:fieldValue bean="${itemInstance}" field="usefulLife"/></dd>
						
					</g:if>
				
					<g:if test="${itemInstance?.comments}">
						<dt><g:message code="item.comments.label" default="Comments" /></dt>
						
							<dd><g:fieldValue bean="${itemInstance}" field="comments"/></dd>
						
					</g:if>
				
					<g:if test="${itemInstance?.party}">
						<dt><g:message code="item.party.label" default="Party" /></dt>
						
							<dd><g:link controller="party" action="show" id="${itemInstance?.party?.id}">${itemInstance?.party?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${itemInstance?.dateCreated}">
						<dt><g:message code="item.dateCreated.label" default="Date Created" /></dt>
						
							<dd><g:formatDate format="MM-dd-yyyy" date="${itemInstance?.dateCreated}" /></dd>
						
					</g:if>
				
					<g:if test="${itemInstance?.createdBy}">
						<dt><g:message code="item.createdBy.label" default="Created By" /></dt>
						
							<dd><g:fieldValue bean="${itemInstance}" field="createdBy"/></dd>
						
					</g:if>
				
					<g:if test="${itemInstance?.lastUpdated}">
						<dt><g:message code="item.lastUpdated.label" default="Last Updated" /></dt>
						
							<dd><g:formatDate format="MM-dd-yyyy" date="${itemInstance?.lastUpdated}" /></dd>
						
					</g:if>
				
					<g:if test="${itemInstance?.updatedBy}">
						<dt><g:message code="item.updatedBy.label" default="Updated By" /></dt>
						
							<dd><g:fieldValue bean="${itemInstance}" field="updatedBy"/></dd>
						
					</g:if>
				
					<g:if test="${itemInstance?.catalogs}">
						<dt><g:message code="item.catalogs.label" default="Catalogs" /></dt>
						
							<g:each in="${itemInstance.catalogs}" var="c">
							<dd><g:link controller="catalog" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
					<g:if test="${itemInstance?.categories}">
						<dt><g:message code="item.categories.label" default="Categories" /></dt>
						
							<g:each in="${itemInstance.categories}" var="category">
							<dd><g:link controller="category" action="show" id="${category.id}">${category.name?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
					<g:if test="${itemInstance?.configurationElements}">
						<dt><g:message code="item.configurationElements.label" default="Configuration Elements" /></dt>
						
							<g:each in="${itemInstance.configurationElements}" var="c">
							<dd><g:link controller="configurationCatalog" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
					<g:if test="${itemInstance?.itemConversions}">
						<dt><g:message code="item.itemConversions.label" default="Item Conversions" /></dt>
						
							<g:each in="${itemInstance.itemConversions}" var="i">
							<dd><g:link controller="itemUnitsConversion" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
					<g:if test="${itemInstance?.itemVersions}">
						<dt><g:message code="item.itemVersions.label" default="Item Versions" /></dt>
						
							<g:each in="${itemInstance.itemVersions}" var="i">
							<dd><g:link controller="itemVersion" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
					<g:if test="${itemInstance?.notes}">
						<dt><g:message code="item.notes.label" default="Notes" /></dt>
						
							<g:each in="${itemInstance.notes}" var="n">
							<dd><g:link controller="note" action="show" id="${n.id}">${n?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
					<g:if test="${itemInstance?.partOfConfigurations}">
						<dt><g:message code="item.partOfConfigurations.label" default="Part Of Configurations" /></dt>
						
							<g:each in="${itemInstance.partOfConfigurations}" var="p">
							<dd><g:link controller="configurationCatalog" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
					<g:if test="${itemInstance?.replacementForItems}">
						<dt><g:message code="item.replacementForItems.label" default="Replacement For Items" /></dt>
						
							<g:each in="${itemInstance.replacementForItems}" var="r">
							<dd><g:link controller="replacementItem" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
					<g:if test="${itemInstance?.replacementItems}">
						<dt><g:message code="item.replacementItems.label" default="Replacement Items" /></dt>
						
							<g:each in="${itemInstance.replacementItems}" var="r">
							<dd><g:link controller="replacementItem" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${itemInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="editItem" id="${itemInstance?.id}">
							<i class="icon-pencil"></i>
							<g:message code="default.button.edit.label" default="Edit" />
						</g:link>
						<button class="btn btn-danger" type="submit" name="_action_deleteItem">
							<i class="icon-trash icon-white"></i>
							<g:message code="default.button.delete.label" default="Delete" />
						</button>
					</div>
				</g:form>

			</div>

		</div>
	</body>
</html>
