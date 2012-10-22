
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
					<h1>${categoryInstance?.name }</h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<dl>
				
					<g:if test="${categoryInstance?.parentCategoryPath}">
						<dt><g:message code="category.parentCategoryPath.label" default="Full Path" /></dt>
						
							<dd>${(categoryInstance?.parentCategoryPath.equals('/')) ? ("/" + categoryInstance?.name?.encodeAsHTML()) : (categoryInstance?.parentCategoryPath?.encodeAsHTML() + '/' + categoryInstance?.name?.encodeAsHTML()) }</dd>
						
					</g:if>
					
					<g:if test="${categoryInstance?.parentCategory}">
						<dt><g:message code="category.parentCategory.label" default="Child of" /></dt>
						    
						    <g:if test="${categoryInstance?.parentCategoryPath?.equals('/') }">
						      <dd>${categoryInstance?.parentCategoryPath?.encodeAsHTML()}</dd>
						    </g:if>
						    
						    <g:else>
							  <dd><g:link controller="category" action="show" id="${categoryInstance?.parentCategory?.id}">${categoryInstance?.parentCategoryPath?.encodeAsHTML()}</g:link></dd>
						    </g:else>
						    
					</g:if>
					
					<g:if test="${categoryInstance?.categories}">
						<dt><g:message code="category.categories.label" default="Contains" /></dt>
						
						    <g:each in="${categoryInstance?.categories }" var="child">
							     <dd><g:link controller="category" action="show" id="${child?.id}">${child?.name?.encodeAsHTML()}</g:link></dd>
						    </g:each>
					</g:if>
				
					<g:if test="${categoryInstance?.createdBy}">
						<dt><g:message code="category.createdBy.label" default="Created By" /></dt>
						
							<dd><g:fieldValue bean="${categoryInstance}" field="createdBy"/></dd>
						
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
