
<%@ page import="org.ihc.esa.Document" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'standard.label', default: 'Standard')}" />
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
						
							<th class="header"><g:message code="item.form.label" default="Item" /></th>
						
							<g:sortableColumn property="createdBy" title="${message(code: 'item.createdBy.label', default: 'Created By')}" />
						
							<g:sortableColumn property="updatedBy" title="${message(code: 'item.updatedBy.label', default: 'Updated By')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${itemInstanceList}" var="itemInstance">
						<tr>
						
							<td>${fieldValue(bean: itemInstance, field: "name")}</td>
							
							<td>${fieldValue(bean: itemInstance, field: "description")}</td>
							
							<td>${fieldValue(bean: itemInstance, field: "usefulLife")}</td>
							
							<td>${fieldValue(bean: itemInstance, field: "availableDate")}</td>
							
							<td>${fieldValue(bean: itemInstance, field: "inService")}</td>
							
							<td>${fieldValue(bean: itemInstance, field: "comments")}</td>
							
							<td>${fieldValue(bean: itemInstance, field: "sourceSystem")}</td>
						
							<td>${fieldValue(bean: itemInstance, field: "createdBy")}</td>
						
							<td>${fieldValue(bean: itemInstance, field: "updatedBy")}</td>
						
							<td class="link">
								<g:link action="show" id="${itemInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${documentInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
