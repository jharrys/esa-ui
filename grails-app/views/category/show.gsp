
<%@ page import="org.ihc.esa.Category" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'category.label', default: 'Category')}" />
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
				
					<g:if test="${categoryInstance?.parentCategory}">
						<dt><g:message code="category.parentCategory.label" default="Parent Category" /></dt>
						
							<dd><g:link controller="category" action="show" id="${categoryInstance?.parentCategory?.id}">${categoryInstance?.parentCategory?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${categoryInstance?.parentCategoryPath}">
						<dt><g:message code="category.parentCategoryPath.label" default="Parent Category Path" /></dt>
						
							<dd><g:fieldValue bean="${categoryInstance}" field="parentCategoryPath"/></dd>
						
					</g:if>
				
					<g:if test="${categoryInstance?.name}">
						<dt><g:message code="category.name.label" default="Name" /></dt>
						
							<dd><g:fieldValue bean="${categoryInstance}" field="name"/></dd>
						
					</g:if>
				
					<g:if test="${categoryInstance?.dateCreated}">
						<dt><g:message code="category.dateCreated.label" default="Date Created" /></dt>
						
							<dd><g:formatDate date="${categoryInstance?.dateCreated}" /></dd>
						
					</g:if>
				
					<g:if test="${categoryInstance?.createdBy}">
						<dt><g:message code="category.createdBy.label" default="Created By" /></dt>
						
							<dd><g:fieldValue bean="${categoryInstance}" field="createdBy"/></dd>
						
					</g:if>
				
					<g:if test="${categoryInstance?.lastUpdated}">
						<dt><g:message code="category.lastUpdated.label" default="Last Updated" /></dt>
						
							<dd><g:formatDate date="${categoryInstance?.lastUpdated}" /></dd>
						
					</g:if>
				
					<g:if test="${categoryInstance?.updatedBy}">
						<dt><g:message code="category.updatedBy.label" default="Updated By" /></dt>
						
							<dd><g:fieldValue bean="${categoryInstance}" field="updatedBy"/></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${categoryInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${categoryInstance?.id}">
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
