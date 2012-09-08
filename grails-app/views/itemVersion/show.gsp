
<%@ page import="org.ihc.esa.domain.ItemVersion" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'itemVersion.label', default: 'ItemVersion')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-itemVersion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-itemVersion" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list itemVersion">
			
				<g:if test="${itemVersionInstance?.item}">
				<li class="fieldcontain">
					<span id="item-label" class="property-label"><g:message code="itemVersion.item.label" default="Item" /></span>
					
						<span class="property-value" aria-labelledby="item-label"><g:link controller="item" action="show" id="${itemVersionInstance?.item?.id}">${itemVersionInstance?.item?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemVersionInstance?.versionNumber}">
				<li class="fieldcontain">
					<span id="versionNumber-label" class="property-label"><g:message code="itemVersion.versionNumber.label" default="Version Number" /></span>
					
						<span class="property-value" aria-labelledby="versionNumber-label"><g:fieldValue bean="${itemVersionInstance}" field="versionNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemVersionInstance?.ihcActualDecomission}">
				<li class="fieldcontain">
					<span id="ihcActualDecomission-label" class="property-label"><g:message code="itemVersion.ihcActualDecomission.label" default="Ihc Actual Decomission" /></span>
					
						<span class="property-value" aria-labelledby="ihcActualDecomission-label"><g:formatDate date="${itemVersionInstance?.ihcActualDecomission}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemVersionInstance?.ihcProposedDecomissioned}">
				<li class="fieldcontain">
					<span id="ihcProposedDecomissioned-label" class="property-label"><g:message code="itemVersion.ihcProposedDecomissioned.label" default="Ihc Proposed Decomissioned" /></span>
					
						<span class="property-value" aria-labelledby="ihcProposedDecomissioned-label"><g:formatDate date="${itemVersionInstance?.ihcProposedDecomissioned}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemVersionInstance?.vendorDecomission}">
				<li class="fieldcontain">
					<span id="vendorDecomission-label" class="property-label"><g:message code="itemVersion.vendorDecomission.label" default="Vendor Decomission" /></span>
					
						<span class="property-value" aria-labelledby="vendorDecomission-label"><g:formatDate date="${itemVersionInstance?.vendorDecomission}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemVersionInstance?.createdBy}">
				<li class="fieldcontain">
					<span id="createdBy-label" class="property-label"><g:message code="itemVersion.createdBy.label" default="Created By" /></span>
					
						<span class="property-value" aria-labelledby="createdBy-label"><g:fieldValue bean="${itemVersionInstance}" field="createdBy"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemVersionInstance?.updatedBy}">
				<li class="fieldcontain">
					<span id="updatedBy-label" class="property-label"><g:message code="itemVersion.updatedBy.label" default="Updated By" /></span>
					
						<span class="property-value" aria-labelledby="updatedBy-label"><g:fieldValue bean="${itemVersionInstance}" field="updatedBy"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemVersionInstance?.configurationVersionIs}">
				<li class="fieldcontain">
					<span id="configurationVersionIs-label" class="property-label"><g:message code="itemVersion.configurationVersionIs.label" default="Configuration Version Is" /></span>
					
						<g:each in="${itemVersionInstance.configurationVersionIs}" var="c">
						<span class="property-value" aria-labelledby="configurationVersionIs-label"><g:link controller="configurationCatalog" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${itemVersionInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="itemVersion.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${itemVersionInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemVersionInstance?.elementVersionIs}">
				<li class="fieldcontain">
					<span id="elementVersionIs-label" class="property-label"><g:message code="itemVersion.elementVersionIs.label" default="Element Version Is" /></span>
					
						<g:each in="${itemVersionInstance.elementVersionIs}" var="e">
						<span class="property-value" aria-labelledby="elementVersionIs-label"><g:link controller="configurationCatalog" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${itemVersionInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="itemVersion.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${itemVersionInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemVersionInstance?.usingVersion}">
				<li class="fieldcontain">
					<span id="usingVersion-label" class="property-label"><g:message code="itemVersion.usingVersion.label" default="Using Version" /></span>
					
						<g:each in="${itemVersionInstance.usingVersion}" var="u">
						<span class="property-value" aria-labelledby="usingVersion-label"><g:link controller="catalogItem" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${itemVersionInstance?.id}" />
					<g:link class="edit" action="edit" id="${itemVersionInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
