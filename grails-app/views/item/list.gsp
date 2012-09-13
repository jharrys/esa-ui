
<%@ page import="org.ihc.esa.Item" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'item.label', default: 'Item')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-item" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-item" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="externalId" title="${message(code: 'item.externalId.label', default: 'External Id')}" />
					
						<g:sortableColumn property="sourceSystem" title="${message(code: 'item.sourceSystem.label', default: 'Source System')}" />
					
						<g:sortableColumn property="standard" title="${message(code: 'item.standard.label', default: 'Standard')}" />
					
						<th><g:message code="item.document.label" default="Document" /></th>
					
						<g:sortableColumn property="intermountainItemNumber" title="${message(code: 'item.intermountainItemNumber.label', default: 'Intermountain Item Number')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'item.name.label', default: 'Name')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${itemInstanceList}" status="i" var="itemInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${itemInstance.id}">${fieldValue(bean: itemInstance, field: "externalId")}</g:link></td>
					
						<td>${fieldValue(bean: itemInstance, field: "sourceSystem")}</td>
					
						<td>${fieldValue(bean: itemInstance, field: "standard")}</td>
					
						<td>${fieldValue(bean: itemInstance, field: "document")}</td>
					
						<td>${fieldValue(bean: itemInstance, field: "intermountainItemNumber")}</td>
					
						<td>${fieldValue(bean: itemInstance, field: "name")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${itemInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
