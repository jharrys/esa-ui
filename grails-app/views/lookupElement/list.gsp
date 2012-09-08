
<%@ page import="org.ihc.esa.domain.LookupElement" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'lookupElement.label', default: 'LookupElement')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-lookupElement" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-lookupElement" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="value" title="${message(code: 'lookupElement.value.label', default: 'Value')}" />
					
						<g:sortableColumn property="display" title="${message(code: 'lookupElement.display.label', default: 'Display')}" />
					
						<g:sortableColumn property="createdBy" title="${message(code: 'lookupElement.createdBy.label', default: 'Created By')}" />
					
						<g:sortableColumn property="updatedBy" title="${message(code: 'lookupElement.updatedBy.label', default: 'Updated By')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'lookupElement.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'lookupElement.lastUpdated.label', default: 'Last Updated')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${lookupElementInstanceList}" status="i" var="lookupElementInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${lookupElementInstance.id}">${fieldValue(bean: lookupElementInstance, field: "value")}</g:link></td>
					
						<td>${fieldValue(bean: lookupElementInstance, field: "display")}</td>
					
						<td>${fieldValue(bean: lookupElementInstance, field: "createdBy")}</td>
					
						<td>${fieldValue(bean: lookupElementInstance, field: "updatedBy")}</td>
					
						<td><g:formatDate date="${lookupElementInstance.dateCreated}" /></td>
					
						<td><g:formatDate date="${lookupElementInstance.lastUpdated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${lookupElementInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
