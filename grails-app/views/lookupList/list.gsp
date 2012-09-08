
<%@ page import="org.ihc.esa.domain.LookupList" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'lookupList.label', default: 'LookupList')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-lookupList" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-lookupList" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'lookupList.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="prompt" title="${message(code: 'lookupList.prompt.label', default: 'Prompt')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'lookupList.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="listType" title="${message(code: 'lookupList.listType.label', default: 'List Type')}" />
					
						<g:sortableColumn property="sqlStatement" title="${message(code: 'lookupList.sqlStatement.label', default: 'Sql Statement')}" />
					
						<g:sortableColumn property="createdBy" title="${message(code: 'lookupList.createdBy.label', default: 'Created By')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${lookupListInstanceList}" status="i" var="lookupListInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${lookupListInstance.id}">${fieldValue(bean: lookupListInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: lookupListInstance, field: "prompt")}</td>
					
						<td>${fieldValue(bean: lookupListInstance, field: "description")}</td>
					
						<td>${fieldValue(bean: lookupListInstance, field: "listType")}</td>
					
						<td>${fieldValue(bean: lookupListInstance, field: "sqlStatement")}</td>
					
						<td>${fieldValue(bean: lookupListInstance, field: "createdBy")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${lookupListInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
