
<%@ page import="org.ihc.esa.Item" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'item.label', default: 'Item')}" />
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
						
							<g:sortableColumn property="externalId" title="${message(code: 'item.externalId.label', default: 'External Id')}" />
						
							<g:sortableColumn property="sourceSystem" title="${message(code: 'item.sourceSystem.label', default: 'Source System')}" />
						
							<g:sortableColumn property="standard" title="${message(code: 'item.standard.label', default: 'Standard')}" />
						
							<g:sortableColumn property="standardType" title="${message(code: 'item.standardType.label', default: 'Standard Type')}" />
						
							<g:sortableColumn property="exception" title="${message(code: 'item.exception.label', default: 'Exception')}" />
						
							<g:sortableColumn property="deviation" title="${message(code: 'item.deviation.label', default: 'Deviation')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${itemInstanceList}" var="itemInstance">
						<tr>
						
							<td>${fieldValue(bean: itemInstance, field: "externalId")}</td>
						
							<td>${fieldValue(bean: itemInstance, field: "sourceSystem")}</td>
						
							<td>${fieldValue(bean: itemInstance, field: "standard")}</td>
						
							<td>${fieldValue(bean: itemInstance, field: "standardType")}</td>
						
							<td>${fieldValue(bean: itemInstance, field: "exception")}</td>
						
							<td>${fieldValue(bean: itemInstance, field: "deviation")}</td>
						
							<td class="link">
								<g:link action="show" id="${itemInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${itemInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
