
<%@ page import="org.ihc.esa.LookupElement" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'lookupElement.label', default: 'LookupElement')}" />
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
				
					<g:if test="${lookupElementInstance?.value}">
						<dt><g:message code="lookupElement.value.label" default="Value" /></dt>
						
							<dd><g:fieldValue bean="${lookupElementInstance}" field="value"/></dd>
						
					</g:if>
				
					<g:if test="${lookupElementInstance?.display}">
						<dt><g:message code="lookupElement.display.label" default="Display" /></dt>
						
							<dd><g:fieldValue bean="${lookupElementInstance}" field="display"/></dd>
						
					</g:if>
				
					<g:if test="${lookupElementInstance?.createdBy}">
						<dt><g:message code="lookupElement.createdBy.label" default="Created By" /></dt>
						
							<dd><g:fieldValue bean="${lookupElementInstance}" field="createdBy"/></dd>
						
					</g:if>
				
					<g:if test="${lookupElementInstance?.updatedBy}">
						<dt><g:message code="lookupElement.updatedBy.label" default="Updated By" /></dt>
						
							<dd><g:fieldValue bean="${lookupElementInstance}" field="updatedBy"/></dd>
						
					</g:if>
				
					<g:if test="${lookupElementInstance?.dateCreated}">
						<dt><g:message code="lookupElement.dateCreated.label" default="Date Created" /></dt>
						
							<dd><g:formatDate date="${lookupElementInstance?.dateCreated}" /></dd>
						
					</g:if>
				
					<g:if test="${lookupElementInstance?.lastUpdated}">
						<dt><g:message code="lookupElement.lastUpdated.label" default="Last Updated" /></dt>
						
							<dd><g:formatDate date="${lookupElementInstance?.lastUpdated}" /></dd>
						
					</g:if>
				
					<g:if test="${lookupElementInstance?.lookupList}">
						<dt><g:message code="lookupElement.lookupList.label" default="Lookup List" /></dt>
						
							<dd><g:link controller="lookupList" action="show" id="${lookupElementInstance?.lookupList?.id}">${lookupElementInstance?.lookupList?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${lookupElementInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${lookupElementInstance?.id}">
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
