
<%@ page import="org.ihc.esa.Category" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'category.label', default: 'Category')}" />
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
						<li>
						    <g:link action="imports" name="imports" class="btn btn-mini">Import</g:link>
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
							<g:sortableColumn property="name" title="${message(code: 'category.name.label', default: 'Name')}" />
						
							<g:sortableColumn property="parentCategoryPath" title="${message(code: 'category.parentCategoryPath.label', default: 'Path')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${categoryInstanceList}" var="categoryInstance">
						<tr>
						
							<td>${fieldValue(bean: categoryInstance, field: "name")}</td>
						
							<td>${fieldValue(bean: categoryInstance, field: "parentCategoryPath")}</td>
						
							<td class="link">
								<g:link action="show" id="${categoryInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${categoryInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
