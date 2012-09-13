
<%@ page import="org.ihc.esa.EsaUser" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'esaUser.label', default: 'User')}" />
		<title>User Screen</title>
	</head>
	<body>
		<a href="#show-esaUser" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				<li><g:link class="list" controller="esaRole" action="list">Manage Roles</g:link></li>
			</ul>
		</div>
		<div id="show-esaUser" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list esaUser">
			
				<g:if test="${esaUserInstance?.username}">
				<li class="fieldcontain">
					<span id="username-label" class="property-label"><g:message code="esaUser.username.label" default="Username" /></span>
					
						<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${esaUserInstance}" field="username"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${esaUserInstance?.password}">
				<li class="fieldcontain">
					<span id="password-label" class="property-label"><g:message code="esaUser.password.label" default="Password" /></span>
					
						<span class="property-value" aria-labelledby="password-label">****</span>
					
				</li>
				</g:if>
			
				<g:if test="${esaUserInstance?.accountExpired}">
				<li class="fieldcontain">
					<span id="accountExpired-label" class="property-label"><g:message code="esaUser.accountExpired.label" default="Account Expired" /></span>
					
						<span class="property-value" aria-labelledby="accountExpired-label"><g:formatBoolean boolean="${esaUserInstance?.accountExpired}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${esaUserInstance?.accountLocked}">
				<li class="fieldcontain">
					<span id="accountLocked-label" class="property-label"><g:message code="esaUser.accountLocked.label" default="Account Locked" /></span>
					
						<span class="property-value" aria-labelledby="accountLocked-label"><g:formatBoolean boolean="${esaUserInstance?.accountLocked}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${esaUserInstance?.enabled}">
				<li class="fieldcontain">
					<span id="enabled-label" class="property-label"><g:message code="esaUser.enabled.label" default="Enabled" /></span>
					
						<span class="property-value" aria-labelledby="enabled-label"><g:formatBoolean boolean="${esaUserInstance?.enabled}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${esaUserInstance?.passwordExpired}">
				<li class="fieldcontain">
					<span id="passwordExpired-label" class="property-label"><g:message code="esaUser.passwordExpired.label" default="Password Expired" /></span>
					
						<span class="property-value" aria-labelledby="passwordExpired-label"><g:formatBoolean boolean="${esaUserInstance?.passwordExpired}" /></span>
					
				</li>
				</g:if>
				
				<li class="fieldcontain">
				    <span id="property-label" class="property-label"><g:message code="esaUser.authorities.label" default="Roles" /></span>
				    <span class="property-value" aria-labelledby="property-label"><g:each in="${esaUserInstance?.authorities }" var="a" >${a.authority}</g:each></span>
				</li>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${esaUserInstance?.id}" />
					<g:link class="edit" action="edit" id="${esaUserInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
