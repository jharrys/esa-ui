
<%@ page import="org.ihc.esa.ConfigurationCatalog" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'configurationCatalog.label', default: 'ConfigurationCatalog')}" />
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
						
							<th class="header"><g:message code="configurationCatalog.parentItem.label" default="Parent Item" /></th>
						
							<th class="header"><g:message code="configurationCatalog.parentItemVersion.label" default="Parent Item Version" /></th>
						
							<th class="header"><g:message code="configurationCatalog.elementItem.label" default="Element Item" /></th>
						
							<th class="header"><g:message code="configurationCatalog.elementItemVersion.label" default="Element Item Version" /></th>
						
							<g:sortableColumn property="createdBy" title="${message(code: 'configurationCatalog.createdBy.label', default: 'Created By')}" />
						
							<g:sortableColumn property="updatedBy" title="${message(code: 'configurationCatalog.updatedBy.label', default: 'Updated By')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${configurationCatalogInstanceList}" var="configurationCatalogInstance">
						<tr>
						
							<td>${fieldValue(bean: configurationCatalogInstance, field: "parentItem")}</td>
						
							<td>${fieldValue(bean: configurationCatalogInstance, field: "parentItemVersion")}</td>
						
							<td>${fieldValue(bean: configurationCatalogInstance, field: "elementItem")}</td>
						
							<td>${fieldValue(bean: configurationCatalogInstance, field: "elementItemVersion")}</td>
						
							<td>${fieldValue(bean: configurationCatalogInstance, field: "createdBy")}</td>
						
							<td>${fieldValue(bean: configurationCatalogInstance, field: "updatedBy")}</td>
						
							<td class="link">
								<g:link action="show" id="${configurationCatalogInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${configurationCatalogInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
