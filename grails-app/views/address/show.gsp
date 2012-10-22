
<%@ page import="org.ihc.esa.Address" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'address.label', default: 'Address')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">
			
			<div class="span2">
				<div class="well">
					<ul class="nav nav-pills nav-stacked">
						<li class="nav-header">${entityName}</li>
						<li>
							<g:link action="list">
								<g:message code="default.list.label" args="[entityName]" />
							</g:link>
						</li>
						<li>
							<g:link action="create">
								<g:message code="default.create.label" args="[entityName]" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>
			
			<div class="span9">

				<div class="page-header">
					<h1><g:message code="default.show.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<dl>
				
					<g:if test="${addressInstance?.externalId}">
						<dt><g:message code="address.externalId.label" default="External Id" /></dt>
						
							<dd><g:fieldValue bean="${addressInstance}" field="externalId"/></dd>
						
					</g:if>
				
					<g:if test="${addressInstance?.addressLine1}">
						<dt><g:message code="address.addressLine1.label" default="Address Line1" /></dt>
						
							<dd><g:fieldValue bean="${addressInstance}" field="addressLine1"/></dd>
						
					</g:if>
				
					<g:if test="${addressInstance?.addressLine2}">
						<dt><g:message code="address.addressLine2.label" default="Address Line2" /></dt>
						
							<dd><g:fieldValue bean="${addressInstance}" field="addressLine2"/></dd>
						
					</g:if>
				
					<g:if test="${addressInstance?.addressLine3}">
						<dt><g:message code="address.addressLine3.label" default="Address Line3" /></dt>
						
							<dd><g:fieldValue bean="${addressInstance}" field="addressLine3"/></dd>
						
					</g:if>
				
					<g:if test="${addressInstance?.addressLine4}">
						<dt><g:message code="address.addressLine4.label" default="Address Line4" /></dt>
						
							<dd><g:fieldValue bean="${addressInstance}" field="addressLine4"/></dd>
						
					</g:if>
				
					<g:if test="${addressInstance?.city}">
						<dt><g:message code="address.city.label" default="City" /></dt>
						
							<dd><g:fieldValue bean="${addressInstance}" field="city"/></dd>
						
					</g:if>
				
					<g:if test="${addressInstance?.state}">
						<dt><g:message code="address.state.label" default="State" /></dt>
						
							<dd><g:fieldValue bean="${addressInstance}" field="state"/></dd>
						
					</g:if>
				
					<g:if test="${addressInstance?.zip}">
						<dt><g:message code="address.zip.label" default="Zip" /></dt>
						
							<dd><g:fieldValue bean="${addressInstance}" field="zip"/></dd>
						
					</g:if>
				
					<g:if test="${addressInstance?.createdBy}">
						<dt><g:message code="address.createdBy.label" default="Created By" /></dt>
						
							<dd><g:fieldValue bean="${addressInstance}" field="createdBy"/></dd>
						
					</g:if>
				
					<g:if test="${addressInstance?.updatedBy}">
						<dt><g:message code="address.updatedBy.label" default="Updated By" /></dt>
						
							<dd><g:fieldValue bean="${addressInstance}" field="updatedBy"/></dd>
						
					</g:if>
				
					<g:if test="${addressInstance?.dateCreated}">
						<dt><g:message code="address.dateCreated.label" default="Date Created" /></dt>
						
							<dd><g:formatDate format="MM-dd-yyyy" date="${addressInstance?.dateCreated}" /></dd>
						
					</g:if>
				
					<g:if test="${addressInstance?.lastUpdated}">
						<dt><g:message code="address.lastUpdated.label" default="Last Updated" /></dt>
						
							<dd><g:formatDate format="MM-dd-yyyy" date="${addressInstance?.lastUpdated}" /></dd>
						
					</g:if>
				
					<g:if test="${addressInstance?.partyAddressAddress}">
						<dt><g:message code="address.partyAddressAddress.label" default="Party Address Address" /></dt>
						
							<g:each in="${addressInstance.partyAddressAddress}" var="p">
							<dd><g:link controller="partyAddress" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${addressInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${addressInstance?.id}">
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
