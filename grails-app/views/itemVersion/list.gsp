
<%@ page import="org.ihc.esa.domain.ItemVersion" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'itemVersion.label', default: 'ItemVersion')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-itemVersion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-itemVersion" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="itemVersion.item.label" default="Item" /></th>
					
						<g:sortableColumn property="versionNumber" title="${message(code: 'itemVersion.versionNumber.label', default: 'Version Number')}" />
					
						<g:sortableColumn property="ihcActualDecomission" title="${message(code: 'itemVersion.ihcActualDecomission.label', default: 'Ihc Actual Decomission')}" />
					
						<g:sortableColumn property="ihcProposedDecomissioned" title="${message(code: 'itemVersion.ihcProposedDecomissioned.label', default: 'Ihc Proposed Decomissioned')}" />
					
						<g:sortableColumn property="vendorDecomission" title="${message(code: 'itemVersion.vendorDecomission.label', default: 'Vendor Decomission')}" />
					
						<g:sortableColumn property="createdBy" title="${message(code: 'itemVersion.createdBy.label', default: 'Created By')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${itemVersionInstanceList}" status="i" var="itemVersionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${itemVersionInstance.id}">${fieldValue(bean: itemVersionInstance, field: "item")}</g:link></td>
					
						<td>${fieldValue(bean: itemVersionInstance, field: "versionNumber")}</td>
					
						<td><g:formatDate date="${itemVersionInstance.ihcActualDecomission}" /></td>
					
						<td><g:formatDate date="${itemVersionInstance.ihcProposedDecomissioned}" /></td>
					
						<td><g:formatDate date="${itemVersionInstance.vendorDecomission}" /></td>
					
						<td>${fieldValue(bean: itemVersionInstance, field: "createdBy")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${itemVersionInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
