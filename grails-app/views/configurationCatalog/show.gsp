
<%@ page import="org.ihc.esa.ConfigurationCatalog" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'configurationCatalog.label', default: 'ConfigurationCatalog')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">
			
			<div class="span2">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li>
							<g:link action="list">
								<i class="icon-list"></i>
								<g:message code="default.list.label" args="[entityName]" />
							</g:link>
						</li>
						<li>
							<g:link action="create">
								<i class="icon-plus"></i>
								<g:message code="default.create.label" args="[entityName]" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>
			
			<div class="span9">

				<div class="page-header">
					<h1><g:message code="default.show.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<dl>
				
					<g:if test="${configurationCatalogInstance?.parentItem}">
						<dt><g:message code="configurationCatalog.parentItem.label" default="Parent Item" /></dt>
						
							<dd><g:link controller="item" action="show" id="${configurationCatalogInstance?.parentItem?.id}">${configurationCatalogInstance?.parentItem?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${configurationCatalogInstance?.parentItemVersion}">
						<dt><g:message code="configurationCatalog.parentItemVersion.label" default="Parent Item Version" /></dt>
						
							<dd><g:link controller="itemVersion" action="show" id="${configurationCatalogInstance?.parentItemVersion?.id}">${configurationCatalogInstance?.parentItemVersion?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${configurationCatalogInstance?.elementItem}">
						<dt><g:message code="configurationCatalog.elementItem.label" default="Element Item" /></dt>
						
							<dd><g:link controller="item" action="show" id="${configurationCatalogInstance?.elementItem?.id}">${configurationCatalogInstance?.elementItem?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${configurationCatalogInstance?.elementItemVersion}">
						<dt><g:message code="configurationCatalog.elementItemVersion.label" default="Element Item Version" /></dt>
						
							<dd><g:link controller="itemVersion" action="show" id="${configurationCatalogInstance?.elementItemVersion?.id}">${configurationCatalogInstance?.elementItemVersion?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${configurationCatalogInstance?.createdBy}">
						<dt><g:message code="configurationCatalog.createdBy.label" default="Created By" /></dt>
						
							<dd><g:fieldValue bean="${configurationCatalogInstance}" field="createdBy"/></dd>
						
					</g:if>
				
					<g:if test="${configurationCatalogInstance?.updatedBy}">
						<dt><g:message code="configurationCatalog.updatedBy.label" default="Updated By" /></dt>
						
							<dd><g:fieldValue bean="${configurationCatalogInstance}" field="updatedBy"/></dd>
						
					</g:if>
				
					<g:if test="${configurationCatalogInstance?.dateCreated}">
						<dt><g:message code="configurationCatalog.dateCreated.label" default="Date Created" /></dt>
						
							<dd><g:formatDate date="${configurationCatalogInstance?.dateCreated}" /></dd>
						
					</g:if>
				
					<g:if test="${configurationCatalogInstance?.lastUpdated}">
						<dt><g:message code="configurationCatalog.lastUpdated.label" default="Last Updated" /></dt>
						
							<dd><g:formatDate date="${configurationCatalogInstance?.lastUpdated}" /></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${configurationCatalogInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${configurationCatalogInstance?.id}">
							<i class="icon-pencil"></i>
							<g:message code="default.button.edit.label" default="Edit" />
						</g:link>
						<button class="btn btn-danger" type="submit" name="_action_delete">
							<i class="icon-trash icon-white"></i>
							<g:message code="default.button.delete.label" default="Delete" />
						</button>
					</div>
				</g:form>

			</div>

		</div>
	</body>
</html>
