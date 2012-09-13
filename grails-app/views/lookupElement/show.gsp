
<%@ page import="org.ihc.esa.LookupElement" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'lookupElement.label', default: 'LookupElement')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-lookupElement" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-lookupElement" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list lookupElement">
			
				<g:if test="${lookupElementInstance?.value}">
				<li class="fieldcontain">
					<span id="value-label" class="property-label"><g:message code="lookupElement.value.label" default="Value" /></span>
					
						<span class="property-value" aria-labelledby="value-label"><g:fieldValue bean="${lookupElementInstance}" field="value"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${lookupElementInstance?.display}">
				<li class="fieldcontain">
					<span id="display-label" class="property-label"><g:message code="lookupElement.display.label" default="Display" /></span>
					
						<span class="property-value" aria-labelledby="display-label"><g:fieldValue bean="${lookupElementInstance}" field="display"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${lookupElementInstance?.createdBy}">
				<li class="fieldcontain">
					<span id="createdBy-label" class="property-label"><g:message code="lookupElement.createdBy.label" default="Created By" /></span>
					
						<span class="property-value" aria-labelledby="createdBy-label"><g:fieldValue bean="${lookupElementInstance}" field="createdBy"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${lookupElementInstance?.updatedBy}">
				<li class="fieldcontain">
					<span id="updatedBy-label" class="property-label"><g:message code="lookupElement.updatedBy.label" default="Updated By" /></span>
					
						<span class="property-value" aria-labelledby="updatedBy-label"><g:fieldValue bean="${lookupElementInstance}" field="updatedBy"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${lookupElementInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="lookupElement.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${lookupElementInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${lookupElementInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="lookupElement.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${lookupElementInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${lookupElementInstance?.lookupList}">
				<li class="fieldcontain">
					<span id="lookupList-label" class="property-label"><g:message code="lookupElement.lookupList.label" default="Lookup List" /></span>
					
						<span class="property-value" aria-labelledby="lookupList-label"><g:link controller="lookupList" action="show" id="${lookupElementInstance?.lookupList?.id}">${lookupElementInstance?.lookupList?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${lookupElementInstance?.id}" />
					<g:link class="edit" action="edit" id="${lookupElementInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
