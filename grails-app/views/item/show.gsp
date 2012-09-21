
<%@ page import="org.ihc.esa.Item"%>
<% 
    def itemName = itemInstance.name + " (${itemInstance.description})"
	def isStandard = (itemInstance.standard == 'Y' ? true:false) 
%>
<!doctype html>
<html>
<head>
<meta name="layout" content="bootstrap">
<g:set var="entityName" value="${message(code: 'item.label', default: 'Item')}" />
<title>
	${itemInstance.name }
</title>
</head>
<body>
	<div class="row-fluid">

		<div class="span2">
			<div class="well">
				<ul class="nav nav-list">
					<li class="nav-header">
						${entityName}
					</li>
					<li><g:link action="list">
							<i class="icon-list"></i>
							<g:message code="default.list.label" args="[entityName]" />
						</g:link></li>
					<li><g:link action="create">
							<i class="icon-plus"></i>
							<g:message code="default.create.label" args="[entityName]" />
						</g:link></li>
				</ul>
			</div>
		</div>

		<div class="span9">

			<div class="page-header">
				<h1>
					${itemName }
				</h1>
			</div>

			<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">
					${flash.message}
				</bootstrap:alert>
			</g:if>

			<dl>

				<g:if test="${itemInstance?.externalId}">
					<dt>
						<g:message code="item.externalId.label" default="External Id" />
					</dt>

					<dd>
						<g:fieldValue bean="${itemInstance}" field="externalId" />
					</dd>

				</g:if>

				<g:if test="${itemInstance?.sourceSystem}">
					<dt>
						<g:message code="item.sourceSystem.label" default="Source System" />
					</dt>

					<dd>
						<g:fieldValue bean="${itemInstance}" field="sourceSystem" />
					</dd>

				</g:if>

				<g:if test="${itemInstance?.standard}">
					<dt>
						<g:message code="item.standard.label" default="Standard" />
					</dt>

                    <dd>
					   <g:if test="${isStandard }">
                           <span class="label label-success">Yes</span>
                       </g:if>
					   <g:else>
					       <span class="label label-important">No</span>
					   </g:else>
					</dd>

				</g:if>

				<g:if test="${itemInstance?.document}">
					<dt>
						<g:message code="item.document.label" default="Document" />
					</dt>

					<dd>
						<g:link controller="document" action="show" id="${itemInstance?.document?.id}">
							${itemInstance?.document?.encodeAsHTML()}
						</g:link>
					</dd>

				</g:if>

				<g:if test="${itemInstance?.intermountainItemNumber}">
					<dt>
						<g:message code="item.intermountainItemNumber.label" default="Intermountain Item Number" />
					</dt>

					<dd>
						<g:fieldValue bean="${itemInstance}" field="intermountainItemNumber" />
					</dd>

				</g:if>

				<g:if test="${itemInstance?.name}">
					<dt>
						<g:message code="item.name.label" default="Name" />
					</dt>

					<dd>
						<g:fieldValue bean="${itemInstance}" field="name" />
					</dd>

				</g:if>

				<g:if test="${itemInstance?.description}">
					<dt>
						<g:message code="item.description.label" default="Description" />
					</dt>

					<dd>
						<g:fieldValue bean="${itemInstance}" field="description" />
					</dd>

				</g:if>

				<g:if test="${itemInstance?.generalLedgerCode}">
					<dt>
						<g:message code="item.generalLedgerCode.label" default="General Ledger Code" />
					</dt>

					<dd>
						<g:fieldValue bean="${itemInstance}" field="generalLedgerCode" />
					</dd>

				</g:if>

				<g:if test="${itemInstance?.productGroup}">
					<dt>
						<g:message code="item.productGroup.label" default="Product Group" />
					</dt>

					<dd>
						<g:fieldValue bean="${itemInstance}" field="productGroup" />
					</dd>

				</g:if>

				<g:if test="${itemInstance?.technologyGroup}">
					<dt>
						<g:message code="item.technologyGroup.label" default="Technology Group" />
					</dt>

					<dd>
						<g:fieldValue bean="${itemInstance}" field="technologyGroup" />
					</dd>

				</g:if>

				<g:if test="${itemInstance?.contract}">
					<dt>
						<g:message code="item.contract.label" default="Contract" />
					</dt>

					<dd>
						<g:link controller="contract" action="show" id="${itemInstance?.contract?.id}">
							${itemInstance?.contract?.encodeAsHTML()}
						</g:link>
					</dd>

				</g:if>

				<g:if test="${itemInstance?.availableDate}">
					<dt>
						<g:message code="item.availableDate.label" default="Available Date" />
					</dt>

					<dd>
						<g:formatDate date="${itemInstance?.availableDate}" />
					</dd>

				</g:if>

				<g:if test="${itemInstance?.ihcActualDecomissioned}">
					<dt>
						<g:message code="item.ihcActualDecomissioned.label" default="Ihc Actual Decomissioned" />
					</dt>

					<dd>
						<g:formatDate date="${itemInstance?.ihcActualDecomissioned}" />
					</dd>

				</g:if>

				<g:if test="${itemInstance?.ihcProposedDecomissioned}">
					<dt>
						<g:message code="item.ihcProposedDecomissioned.label" default="Ihc Proposed Decomissioned" />
					</dt>

					<dd>
						<g:formatDate date="${itemInstance?.ihcProposedDecomissioned}" />
					</dd>

				</g:if>

				<g:if test="${itemInstance?.vendorDecomissioned}">
					<dt>
						<g:message code="item.vendorDecomissioned.label" default="Vendor Decomissioned" />
					</dt>

					<dd>
						<g:formatDate date="${itemInstance?.vendorDecomissioned}" />
					</dd>

				</g:if>

				<g:if test="${itemInstance?.vendorCatalogNumber}">
					<dt>
						<g:message code="item.vendorCatalogNumber.label" default="Vendor Catalog Number" />
					</dt>

					<dd>
						<g:fieldValue bean="${itemInstance}" field="vendorCatalogNumber" />
					</dd>

				</g:if>

				<g:if test="${itemInstance?.manufacturerPartId}">
					<dt>
						<g:message code="item.manufacturerPartId.label" default="Manufacturer Part Id" />
					</dt>

					<dd>
						<g:fieldValue bean="${itemInstance}" field="manufacturerPartId" />
					</dd>

				</g:if>

				<g:if test="${itemInstance?.manufacturerCatalogNumber}">
					<dt>
						<g:message code="item.manufacturerCatalogNumber.label" default="Manufacturer Catalog Number" />
					</dt>

					<dd>
						<g:fieldValue bean="${itemInstance}" field="manufacturerCatalogNumber" />
					</dd>

				</g:if>

				<g:if test="${itemInstance?.purchasingUnitOfMeasure}">
					<dt>
						<g:message code="item.purchasingUnitOfMeasure.label" default="Purchasing Unit Of Measure" />
					</dt>

					<dd>
						<g:fieldValue bean="${itemInstance}" field="purchasingUnitOfMeasure" />
					</dd>

				</g:if>

				<g:if test="${itemInstance?.purchasingUnitPrice}">
					<dt>
						<g:message code="item.purchasingUnitPrice.label" default="Purchasing Unit Price" />
					</dt>

					<dd>
						<g:fieldValue bean="${itemInstance}" field="purchasingUnitPrice" />
					</dd>

				</g:if>

				<g:if test="${itemInstance?.unspscNumber}">
					<dt>
						<g:message code="item.unspscNumber.label" default="Unspsc Number" />
					</dt>

					<dd>
						<g:fieldValue bean="${itemInstance}" field="unspscNumber" />
					</dd>

				</g:if>

				<g:if test="${itemInstance?.createdBy}">
					<dt>
						<g:message code="item.createdBy.label" default="Created By" />
					</dt>

					<dd>
						<g:fieldValue bean="${itemInstance}" field="createdBy" />
					</dd>

				</g:if>

				<g:if test="${itemInstance?.updatedBy}">
					<dt>
						<g:message code="item.updatedBy.label" default="Updated By" />
					</dt>

					<dd>
						<g:fieldValue bean="${itemInstance}" field="updatedBy" />
					</dd>

				</g:if>

				<g:if test="${itemInstance?.vendorParty}">
					<dt>
						<g:message code="item.vendorParty.label" default="Vendor Party" />
					</dt>

					<dd>
						<g:link controller="party" action="show" id="${itemInstance?.vendorParty?.id}">
							${itemInstance?.vendorParty?.encodeAsHTML()}
						</g:link>
					</dd>

				</g:if>

				<g:if test="${itemInstance?.belongsToCatalogs}">
					<dt>
						<g:message code="item.belongsToCatalogs.label" default="Belongs To Catalogs" />
					</dt>

					<g:each in="${itemInstance.belongsToCatalogs}" var="b">
						<dd>
							<g:link controller="catalogItem" action="show" id="${b.id}">
								${b?.encodeAsHTML()}
							</g:link>
						</dd>
					</g:each>

				</g:if>

				<g:if test="${itemInstance?.configurationElements}">
					<dt>
						<g:message code="item.configurationElements.label" default="Configuration Elements" />
					</dt>

					<g:each in="${itemInstance.configurationElements}" var="c">
						<dd>
							<g:link controller="configurationCatalog" action="show" id="${c.id}">
								${c?.encodeAsHTML()}
							</g:link>
						</dd>
					</g:each>

				</g:if>

				<g:if test="${itemInstance?.dateCreated}">
					<dt>
						<g:message code="item.dateCreated.label" default="Date Created" />
					</dt>

					<dd>
						<g:formatDate date="${itemInstance?.dateCreated}" />
					</dd>

				</g:if>

				<g:if test="${itemInstance?.itemConversions}">
					<dt>
						<g:message code="item.itemConversions.label" default="Item Conversions" />
					</dt>

					<g:each in="${itemInstance.itemConversions}" var="i">
						<dd>
							<g:link controller="itemUnitsConversion" action="show" id="${i.id}">
								${i?.encodeAsHTML()}
							</g:link>
						</dd>
					</g:each>

				</g:if>

				<g:if test="${itemInstance?.itemVersions}">
					<dt>
						<g:message code="item.itemVersions.label" default="Item Versions" />
					</dt>

					<g:each in="${itemInstance.itemVersions}" var="i">
						<dd>
							<g:link controller="itemVersion" action="show" id="${i.id}">
								${i?.encodeAsHTML()}
							</g:link>
						</dd>
					</g:each>

				</g:if>

				<g:if test="${itemInstance?.lastUpdated}">
					<dt>
						<g:message code="item.lastUpdated.label" default="Last Updated" />
					</dt>

					<dd>
						<g:formatDate date="${itemInstance?.lastUpdated}" />
					</dd>

				</g:if>

				<g:if test="${itemInstance?.partOfConfigurations}">
					<dt>
						<g:message code="item.partOfConfigurations.label" default="Part Of Configurations" />
					</dt>

					<g:each in="${itemInstance.partOfConfigurations}" var="p">
						<dd>
							<g:link controller="configurationCatalog" action="show" id="${p.id}">
								${p?.encodeAsHTML()}
							</g:link>
						</dd>
					</g:each>

				</g:if>

				<g:if test="${itemInstance?.replacementForItems}">
					<dt>
						<g:message code="item.replacementForItems.label" default="Replacement For Items" />
					</dt>

					<g:each in="${itemInstance.replacementForItems}" var="r">
						<dd>
							<g:link controller="replacementItem" action="show" id="${r.id}">
								${r?.encodeAsHTML()}
							</g:link>
						</dd>
					</g:each>

				</g:if>

				<g:if test="${itemInstance?.replacementItems}">
					<dt>
						<g:message code="item.replacementItems.label" default="Replacement Items" />
					</dt>

					<g:each in="${itemInstance.replacementItems}" var="r">
						<dd>
							<g:link controller="replacementItem" action="show" id="${r.id}">
								${r?.encodeAsHTML()}
							</g:link>
						</dd>
					</g:each>

				</g:if>

			</dl>

			<g:form>
				<g:hiddenField name="id" value="${itemInstance?.id}" />
				<div class="form-actions">
					<g:link class="btn" action="edit" id="${itemInstance?.id}">
						<i class="icon-pencil"></i>
						<g:message code="default.button.edit.label" default="Edit" />
					</g:link>
					<button class="btn btn-danger" type="submit" name="_action_delete">
						<i class="icon-trash icon-white"></i>
						<g:message code="default.button.delete.label" default="Delete" />
					</button>
				</div>
			</g:form>

		</div>

	</div>
</body>
</html>
