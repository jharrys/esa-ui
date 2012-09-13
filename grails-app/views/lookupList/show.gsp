
<%@ page import="org.ihc.esa.LookupList" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'lookupList.label', default: 'LookupList')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-lookupList" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-lookupList" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list lookupList">
			
				<g:if test="${lookupListInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="lookupList.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${lookupListInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${lookupListInstance?.prompt}">
				<li class="fieldcontain">
					<span id="prompt-label" class="property-label"><g:message code="lookupList.prompt.label" default="Prompt" /></span>
					
						<span class="property-value" aria-labelledby="prompt-label"><g:fieldValue bean="${lookupListInstance}" field="prompt"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${lookupListInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="lookupList.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${lookupListInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${lookupListInstance?.listType}">
				<li class="fieldcontain">
					<span id="listType-label" class="property-label"><g:message code="lookupList.listType.label" default="List Type" /></span>
					
						<span class="property-value" aria-labelledby="listType-label"><g:fieldValue bean="${lookupListInstance}" field="listType"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${lookupListInstance?.sqlStatement}">
				<li class="fieldcontain">
					<span id="sqlStatement-label" class="property-label"><g:message code="lookupList.sqlStatement.label" default="Sql Statement" /></span>
					
						<span class="property-value" aria-labelledby="sqlStatement-label"><g:fieldValue bean="${lookupListInstance}" field="sqlStatement"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${lookupListInstance?.createdBy}">
				<li class="fieldcontain">
					<span id="createdBy-label" class="property-label"><g:message code="lookupList.createdBy.label" default="Created By" /></span>
					
						<span class="property-value" aria-labelledby="createdBy-label"><g:fieldValue bean="${lookupListInstance}" field="createdBy"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${lookupListInstance?.updatedBy}">
				<li class="fieldcontain">
					<span id="updatedBy-label" class="property-label"><g:message code="lookupList.updatedBy.label" default="Updated By" /></span>
					
						<span class="property-value" aria-labelledby="updatedBy-label"><g:fieldValue bean="${lookupListInstance}" field="updatedBy"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${lookupListInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="lookupList.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${lookupListInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${lookupListInstance?.elements}">
				<li class="fieldcontain">
					<span id="elements-label" class="property-label"><g:message code="lookupList.elements.label" default="Elements" /></span>
					
						<g:each in="${lookupListInstance.elements}" var="e">
						<span class="property-value" aria-labelledby="elements-label"><g:link controller="lookupElement" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${lookupListInstance?.fieldLookup}">
				<li class="fieldcontain">
					<span id="fieldLookup-label" class="property-label"><g:message code="lookupList.fieldLookup.label" default="Field Lookup" /></span>
					
						<g:each in="${lookupListInstance.fieldLookup}" var="f">
						<span class="property-value" aria-labelledby="fieldLookup-label"><g:link controller="formField" action="show" id="${f.id}">${f?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${lookupListInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="lookupList.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${lookupListInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${lookupListInstance?.id}" />
					<g:link class="edit" action="edit" id="${lookupListInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
