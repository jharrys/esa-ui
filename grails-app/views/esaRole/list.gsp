
<%@ page import="org.ihc.esa.domain.EsaRole" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'esaRole.label', default: 'Role')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-esaRole" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				<li><g:link class="list" controller="esaUser" action="list">Manage Users</g:link></li>
			</ul>
		</div>
		<div id="list-esaRole" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="authority" title="${message(code: 'esaRole.authority.label', default: 'Authority')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${esaRoleInstanceList}" status="i" var="esaRoleInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${esaRoleInstance.id}">${fieldValue(bean: esaRoleInstance, field: "authority")}</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${esaRoleInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
