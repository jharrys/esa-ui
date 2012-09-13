
<%@ page import="org.ihc.esa.EsaUser" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'esaUser.label', default: 'User')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-esaUser" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				<li><g:link class="list" controller="esaRole" action="list">Manage Roles</g:link></li>
			</ul>
		</div>
		<div id="list-esaUser" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /><sec:loggedInUserInfo field="username" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="username" title="${message(code: 'esaUser.username.label', default: 'Username')}" />
					
						<g:sortableColumn property="password" title="${message(code: 'esaUser.password.label', default: 'Password')}" />
					
						<g:sortableColumn property="accountExpired" title="${message(code: 'esaUser.accountExpired.label', default: 'Account Expired')}" />
					
						<g:sortableColumn property="accountLocked" title="${message(code: 'esaUser.accountLocked.label', default: 'Account Locked')}" />
					
						<g:sortableColumn property="enabled" title="${message(code: 'esaUser.enabled.label', default: 'Enabled')}" />
					
						<g:sortableColumn property="passwordExpired" title="${message(code: 'esaUser.passwordExpired.label', default: 'Password Expired')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${esaUserInstanceList}" status="i" var="esaUserInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${esaUserInstance.id}">${fieldValue(bean: esaUserInstance, field: "username")}</g:link></td>
					
						<td>****</td>
					
						<td><g:formatBoolean boolean="${esaUserInstance.accountExpired}" /></td>
					
						<td><g:formatBoolean boolean="${esaUserInstance.accountLocked}" /></td>
					
						<td><g:formatBoolean boolean="${esaUserInstance.enabled}" /></td>
					
						<td><g:formatBoolean boolean="${esaUserInstance.passwordExpired}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${esaUserInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
