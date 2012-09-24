
<%@ page import="org.ihc.esa.PartyAddress" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'partyAddress.label', default: 'PartyAddress')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">
			
			<div class="span2">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li>
							<g:link action="list">
								<i class="icon-list"></i>
								<g:message code="default.list.label" args="[entityName]" />
							</g:link>
						</li>
						<li>
							<g:link action="create">
								<i class="icon-plus"></i>
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
				
					<g:if test="${partyAddressInstance?.partyId}">
						<dt><g:message code="partyAddress.partyId.label" default="Party Id" /></dt>
						
							<dd><g:fieldValue bean="${partyAddressInstance}" field="partyId"/></dd>
						
					</g:if>
				
					<g:if test="${partyAddressInstance?.addressId}">
						<dt><g:message code="partyAddress.addressId.label" default="Address Id" /></dt>
						
							<dd><g:fieldValue bean="${partyAddressInstance}" field="addressId"/></dd>
						
					</g:if>
				
					<g:if test="${partyAddressInstance?.createdBy}">
						<dt><g:message code="partyAddress.createdBy.label" default="Created By" /></dt>
						
							<dd><g:fieldValue bean="${partyAddressInstance}" field="createdBy"/></dd>
						
					</g:if>
				
					<g:if test="${partyAddressInstance?.updatedBy}">
						<dt><g:message code="partyAddress.updatedBy.label" default="Updated By" /></dt>
						
							<dd><g:fieldValue bean="${partyAddressInstance}" field="updatedBy"/></dd>
						
					</g:if>
				
					<g:if test="${partyAddressInstance?.address}">
						<dt><g:message code="partyAddress.address.label" default="Address" /></dt>
						
							<dd><g:link controller="address" action="show" id="${partyAddressInstance?.address?.id}">${partyAddressInstance?.address?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${partyAddressInstance?.dateCreated}">
						<dt><g:message code="partyAddress.dateCreated.label" default="Date Created" /></dt>
						
							<dd><g:formatDate date="${partyAddressInstance?.dateCreated}" /></dd>
						
					</g:if>
				
					<g:if test="${partyAddressInstance?.lastUpdated}">
						<dt><g:message code="partyAddress.lastUpdated.label" default="Last Updated" /></dt>
						
							<dd><g:formatDate date="${partyAddressInstance?.lastUpdated}" /></dd>
						
					</g:if>
				
					<g:if test="${partyAddressInstance?.party}">
						<dt><g:message code="partyAddress.party.label" default="Party" /></dt>
						
							<dd><g:link controller="party" action="show" id="${partyAddressInstance?.party?.id}">${partyAddressInstance?.party?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${partyAddressInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${partyAddressInstance?.id}">
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
