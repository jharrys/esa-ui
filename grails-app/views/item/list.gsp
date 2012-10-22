
<%@page import="org.jfree.chart.renderer.xy.DeviationRenderer"%>
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
						
							<g:sortableColumn property="name" title="${message(code: 'item.name.label', default: 'Name')}" />
						
							<g:sortableColumn property="standard" title="${message(code: 'item.standard.label', default: 'Standard')}" />
						
							<g:sortableColumn property="productGroup" title="${message(code: 'item.productGroup.label', default: 'Product Group')}" />
							
							<g:sortableColumn property="technologyGroup" title="${message(code: 'item.technologyGroup.label', default: 'Technology Group')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${itemInstanceList}" var="itemInstance">
						<tr>
						
							<td>${fieldValue(bean: itemInstance, field: "name")}</td>
						
							<td>
							     <%
								    String display = ""
									if (itemInstance.standard.equalsIgnoreCase('Y')) {
										display = "Standard"
									} else if (itemInstance.standard.equalsIgnoreCase('A')) {
									   display = "Standard Alternate"
									} else {
									   display = "Non-Standard"
									}
									
									if (itemInstance.exception.equalsIgnoreCase('Y')) {
										display = display + "; Approved as an Exception."
									}
									
									if (itemInstance.deviation.equalsIgnoreCase('Y')) {
										display = display + "; Requires a Deviation."
									}
								  %>
							     ${display}
							</td>
						
							<td>${fieldValue(bean: itemInstance, field: "productGroup")}</td>
						
							<td>${fieldValue(bean: itemInstance, field: "technologyGroup")}</td>
						
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
