
<%@ page import="org.ihc.esa.LookupList" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'lookupList.label', default: 'LookupList')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">
			
			<div class="span2">
				<div class="well">
					<ul class="nav nav-pills nav-stacked">
						<li class="nav-header">${entityName}</li>
						<li>
							<g:link action="list">
								<g:message code="default.list.label" args="[entityName]" />
							</g:link>
						</li>
						<li>
							<g:link action="create">
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
				
					<g:if test="${lookupListInstance?.name}">
						<dt><g:message code="lookupList.name.label" default="Name" /></dt>
						
							<dd><g:fieldValue bean="${lookupListInstance}" field="name"/></dd>
						
					</g:if>
				
					<g:if test="${lookupListInstance?.prompt}">
						<dt><g:message code="lookupList.prompt.label" default="Prompt" /></dt>
						
							<dd><g:fieldValue bean="${lookupListInstance}" field="prompt"/></dd>
						
					</g:if>
				
					<g:if test="${lookupListInstance?.description}">
						<dt><g:message code="lookupList.description.label" default="Description" /></dt>
						
							<dd><g:fieldValue bean="${lookupListInstance}" field="description"/></dd>
						
					</g:if>
				
					<g:if test="${lookupListInstance?.listType}">
						<dt><g:message code="lookupList.listType.label" default="List Type" /></dt>
						
							<dd><g:fieldValue bean="${lookupListInstance}" field="listType"/></dd>
						
					</g:if>
				
					<g:if test="${lookupListInstance?.sqlStatement}">
						<dt><g:message code="lookupList.sqlStatement.label" default="Sql Statement" /></dt>
						
							<dd><g:fieldValue bean="${lookupListInstance}" field="sqlStatement"/></dd>
						
					</g:if>
				
					<g:if test="${lookupListInstance?.createdBy}">
						<dt><g:message code="lookupList.createdBy.label" default="Created By" /></dt>
						
							<dd><g:fieldValue bean="${lookupListInstance}" field="createdBy"/></dd>
						
					</g:if>
				
					<g:if test="${lookupListInstance?.updatedBy}">
						<dt><g:message code="lookupList.updatedBy.label" default="Updated By" /></dt>
						
							<dd><g:fieldValue bean="${lookupListInstance}" field="updatedBy"/></dd>
						
					</g:if>
				
					<g:if test="${lookupListInstance?.dateCreated}">
						<dt><g:message code="lookupList.dateCreated.label" default="Date Created" /></dt>
						
							<dd><g:formatDate date="${lookupListInstance?.dateCreated}" /></dd>
						
					</g:if>
				
					<g:if test="${lookupListInstance?.elements}">
						<dt><g:message code="lookupList.elements.label" default="Elements" /></dt>
						
							<g:each in="${lookupListInstance.elements}" var="e">
							<dd><g:link controller="lookupElement" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
					<g:if test="${lookupListInstance?.fieldLookup}">
						<dt><g:message code="lookupList.fieldLookup.label" default="Field Lookup" /></dt>
						
							<g:each in="${lookupListInstance.fieldLookup}" var="f">
							<dd><g:link controller="formField" action="show" id="${f.id}">${f?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
					<g:if test="${lookupListInstance?.lastUpdated}">
						<dt><g:message code="lookupList.lastUpdated.label" default="Last Updated" /></dt>
						
							<dd><g:formatDate date="${lookupListInstance?.lastUpdated}" /></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${lookupListInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${lookupListInstance?.id}">
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
