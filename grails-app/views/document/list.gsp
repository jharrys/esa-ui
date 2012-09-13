
<%@ page import="org.ihc.esa.Document" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'document.label', default: 'Document')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-document" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-document" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="document.form.label" default="Form" /></th>
					
						<g:sortableColumn property="sirpId" title="${message(code: 'document.sirpId.label', default: 'Sirp Id')}" />
					
						<g:sortableColumn property="requestor" title="${message(code: 'document.requestor.label', default: 'Requestor')}" />
					
						<g:sortableColumn property="requestorEmail" title="${message(code: 'document.requestorEmail.label', default: 'Requestor Email')}" />
					
						<g:sortableColumn property="owner" title="${message(code: 'document.owner.label', default: 'Owner')}" />
					
						<g:sortableColumn property="ownerEmail" title="${message(code: 'document.ownerEmail.label', default: 'Owner Email')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${documentInstanceList}" status="i" var="documentInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${documentInstance.id}">${fieldValue(bean: documentInstance, field: "form")}</g:link></td>
					
						<td>${fieldValue(bean: documentInstance, field: "sirpId")}</td>
					
						<td>${fieldValue(bean: documentInstance, field: "requestor")}</td>
					
						<td>${fieldValue(bean: documentInstance, field: "requestorEmail")}</td>
					
						<td>${fieldValue(bean: documentInstance, field: "owner")}</td>
					
						<td>${fieldValue(bean: documentInstance, field: "ownerEmail")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${documentInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
