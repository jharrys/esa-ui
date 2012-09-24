
<%@ page import="org.ihc.esa.Address" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'address.label', default: 'Address')}" />
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
						
							<g:sortableColumn property="externalId" title="${message(code: 'address.externalId.label', default: 'External Id')}" />
						
							<g:sortableColumn property="addressLine1" title="${message(code: 'address.addressLine1.label', default: 'Address Line1')}" />
						
							<g:sortableColumn property="addressLine2" title="${message(code: 'address.addressLine2.label', default: 'Address Line2')}" />
						
							<g:sortableColumn property="addressLine3" title="${message(code: 'address.addressLine3.label', default: 'Address Line3')}" />
						
							<g:sortableColumn property="addressLine4" title="${message(code: 'address.addressLine4.label', default: 'Address Line4')}" />
						
							<g:sortableColumn property="city" title="${message(code: 'address.city.label', default: 'City')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${addressInstanceList}" var="addressInstance">
						<tr>
						
							<td>${fieldValue(bean: addressInstance, field: "externalId")}</td>
						
							<td>${fieldValue(bean: addressInstance, field: "addressLine1")}</td>
						
							<td>${fieldValue(bean: addressInstance, field: "addressLine2")}</td>
						
							<td>${fieldValue(bean: addressInstance, field: "addressLine3")}</td>
						
							<td>${fieldValue(bean: addressInstance, field: "addressLine4")}</td>
						
							<td>${fieldValue(bean: addressInstance, field: "city")}</td>
						
							<td class="link">
								<g:link action="show" id="${addressInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${addressInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
