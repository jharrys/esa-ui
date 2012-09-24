
<%@ page import="org.ihc.esa.PartyAddress" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'partyAddress.label', default: 'PartyAddress')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">
			
			<div class="span2">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li class="active">
							<g:link action="list">
								<i class="icon-list icon-white"></i>
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
					<h1><g:message code="default.list.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				
				<table class="table table-striped">
					<thead>
						<tr>
						
							<g:sortableColumn property="partyId" title="${message(code: 'partyAddress.partyId.label', default: 'Party Id')}" />
						
							<g:sortableColumn property="addressId" title="${message(code: 'partyAddress.addressId.label', default: 'Address Id')}" />
						
							<g:sortableColumn property="createdBy" title="${message(code: 'partyAddress.createdBy.label', default: 'Created By')}" />
						
							<g:sortableColumn property="updatedBy" title="${message(code: 'partyAddress.updatedBy.label', default: 'Updated By')}" />
						
							<th class="header"><g:message code="partyAddress.address.label" default="Address" /></th>
						
							<g:sortableColumn property="dateCreated" title="${message(code: 'partyAddress.dateCreated.label', default: 'Date Created')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${partyAddressInstanceList}" var="partyAddressInstance">
						<tr>
						
							<td>${fieldValue(bean: partyAddressInstance, field: "partyId")}</td>
						
							<td>${fieldValue(bean: partyAddressInstance, field: "addressId")}</td>
						
							<td>${fieldValue(bean: partyAddressInstance, field: "createdBy")}</td>
						
							<td>${fieldValue(bean: partyAddressInstance, field: "updatedBy")}</td>
						
							<td>${fieldValue(bean: partyAddressInstance, field: "address")}</td>
						
							<td><g:formatDate date="${partyAddressInstance.dateCreated}" /></td>
						
							<td class="link">
								<g:link action="show" id="${partyAddressInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${partyAddressInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
