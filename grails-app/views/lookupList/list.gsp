
<%@ page import="org.ihc.esa.LookupList" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'lookupList.label', default: 'LookupList')}" />
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
						
							<g:sortableColumn property="name" title="${message(code: 'lookupList.name.label', default: 'Name')}" />
						
							<g:sortableColumn property="prompt" title="${message(code: 'lookupList.prompt.label', default: 'Prompt')}" />
						
							<g:sortableColumn property="description" title="${message(code: 'lookupList.description.label', default: 'Description')}" />
						
							<g:sortableColumn property="listType" title="${message(code: 'lookupList.listType.label', default: 'List Type')}" />
						
							<g:sortableColumn property="sqlStatement" title="${message(code: 'lookupList.sqlStatement.label', default: 'Sql Statement')}" />
						
							<g:sortableColumn property="createdBy" title="${message(code: 'lookupList.createdBy.label', default: 'Created By')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${lookupListInstanceList}" var="lookupListInstance">
						<tr>
						
							<td>${fieldValue(bean: lookupListInstance, field: "name")}</td>
						
							<td>${fieldValue(bean: lookupListInstance, field: "prompt")}</td>
						
							<td>${fieldValue(bean: lookupListInstance, field: "description")}</td>
						
							<td>${fieldValue(bean: lookupListInstance, field: "listType")}</td>
						
							<td>${fieldValue(bean: lookupListInstance, field: "sqlStatement")}</td>
						
							<td>${fieldValue(bean: lookupListInstance, field: "createdBy")}</td>
						
							<td class="link">
								<g:link action="show" id="${lookupListInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${lookupListInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
