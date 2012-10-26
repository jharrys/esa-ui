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
						
							<g:sortableColumn property="name" title="${message(code: 'item.name.label', default: 'Item')}" />
							
							<g:sortableColumn property="description" title="${message(code: 'item.description.label', default: 'Description')}" />
							
							<g:sortableColumn property="comments" title="${message(code: 'item.comments.label', default: 'Comments')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${itemInstanceList}" var="itemInstance">
						<tr>
						
							<td>${fieldValue(bean: itemInstance, field: "name")}</td>
							
							<td><div style="word-wrap: break-word">${fieldValue(bean: itemInstance, field: "description")}</div></td>
							
							<td>${fieldValue(bean: itemInstance, field: "comments")}</td>
							
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
