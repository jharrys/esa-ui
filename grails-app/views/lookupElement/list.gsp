
<%@ page import="org.ihc.esa.LookupElement" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'lookupElement.label', default: 'LookupElement')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">
			
			<div class="span2">
				<div class="well">
					<ul class="nav nav-pills nav-stacked">
						<li class="nav-header">${entityName}</li>
						<li class="active">
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
					<h1><g:message code="default.list.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				
				<table class="table table-striped">
					<thead>
						<tr>
						
							<g:sortableColumn property="value" title="${message(code: 'lookupElement.value.label', default: 'Value')}" />
						
							<g:sortableColumn property="display" title="${message(code: 'lookupElement.display.label', default: 'Display')}" />
						
							<g:sortableColumn property="createdBy" title="${message(code: 'lookupElement.createdBy.label', default: 'Created By')}" />
						
							<g:sortableColumn property="updatedBy" title="${message(code: 'lookupElement.updatedBy.label', default: 'Updated By')}" />
						
							<g:sortableColumn property="dateCreated" title="${message(code: 'lookupElement.dateCreated.label', default: 'Date Created')}" />
						
							<g:sortableColumn property="lastUpdated" title="${message(code: 'lookupElement.lastUpdated.label', default: 'Last Updated')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${lookupElementInstanceList}" var="lookupElementInstance">
						<tr>
						
							<td>${fieldValue(bean: lookupElementInstance, field: "value")}</td>
						
							<td>${fieldValue(bean: lookupElementInstance, field: "display")}</td>
						
							<td>${fieldValue(bean: lookupElementInstance, field: "createdBy")}</td>
						
							<td>${fieldValue(bean: lookupElementInstance, field: "updatedBy")}</td>
						
							<td><g:formatDate date="${lookupElementInstance.dateCreated}" /></td>
						
							<td><g:formatDate date="${lookupElementInstance.lastUpdated}" /></td>
						
							<td class="link">
								<g:link action="show" id="${lookupElementInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${lookupElementInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
