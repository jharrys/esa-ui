
<%@ page import="org.ihc.esa.Project" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="general">
		<g:set var="entityName" value="${message(code: 'project.label', default: 'Project')}" />
		<title>Project ${projectInstance.id }</title>
	</head>
	<body>
		<div class="row-fluid">

			<div class="span2">
                <div class="well">
                    <ul class="nav nav-pills nav-stacked">
                        <li class="nav-header">${entityName}</li>
                        <li <%= request.forwardURI == "${createLink(uri: '/project/list')}" ? ' class="active"' : '' %>>
                            <g:link action="list">
                                <g:message code="default.list.label" args="[entityName]" />
                            </g:link>
                        </li>
                        <li <%= request.forwardURI.contains("filter") ? ' class="active"' : '' %>>
                            <g:link action="list" params="[mine: 'true']">
                                My Project List
                            </g:link>
                        </li>
                        <li <%= request.forwardURI == "${createLink(uri: '/project/create')}" ? ' class="active"' : '' %>>
                            <g:link action="create">
                                <g:message code="default.create.label" args="[entityName]" />
                            </g:link>
                        </li>
                        <li>
                            <g:form class="form-inline" controller="project" action="show">
                                <input name="id" type="text" class="input-small search-query" placeholder="acid #">
                                <button type="submit" class="btn btn-small">Search</button>
                            </g:form>
                        </li>
                    </ul>
                </div>
            </div>

			<div class="span9">

				<div class="page-header">
					<h1>Details for Project ${projectInstance.name }</h1>
					<h3>${projectInstance.id }</h3>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<dl>

					<g:if test="${projectInstance?.name}">
						<dt><g:message code="project.name.label" default="Name" /></dt>

							<dd><g:fieldValue bean="${projectInstance}" field="name"/></dd>

					</g:if>

					<g:if test="${projectInstance?.type}">
						<dt><g:message code="project.type.label" default="Type" /></dt>

							<dd><g:fieldValue bean="${projectInstance}" field="type"/></dd>

					</g:if>

					<g:if test="${projectInstance?.status}">
						<dt><g:message code="project.status.label" default="Status" /></dt>

							<dd><g:fieldValue bean="${projectInstance}" field="status"/></dd>

					</g:if>

					<g:if test="${projectInstance?.externalProjectNumber}">
						<dt><g:message code="project.externalProjectNumber.label" default="External Project Number" /></dt>

							<dd><g:fieldValue bean="${projectInstance}" field="externalProjectNumber"/></dd>

					</g:if>

					<g:if test="${projectInstance?.architects}">
						<dt><g:message code="project.architect.label" default="Architect" /></dt>

                            <g:each in="${projectInstance.architects }" var="architect">
                                <dd><g:link controller="party" action="show" id="${architect?.id}">${architect?.name.encodeAsHTML()}</g:link></dd>
							</g:each>

					</g:if>

					<g:if test="${projectInstance?.projectManager}">
						<dt><g:message code="project.projectManager.label" default="Project Manager" /></dt>

							<dd><g:link controller="party" action="show" id="${projectInstance?.projectManager?.id}">${projectInstance?.projectManager?.name?.encodeAsHTML()}</g:link></dd>

					</g:if>

					<g:if test="${projectInstance?.dateStart}">
						<dt><g:message code="project.dateStart.label" default="Date Start" /></dt>

							<dd><g:formatDate format="MM-dd-yyyy" date="${projectInstance?.dateStart}" /></dd>

					</g:if>

					<g:if test="${projectInstance?.dateCompleted}">
						<dt><g:message code="project.dateCompleted.label" default="Date Completed" /></dt>

							<dd><g:formatDate format="MM-dd-yyyy" date="${projectInstance?.dateCompleted}" /></dd>

					</g:if>

					<g:if test="${projectInstance?.dateCreated}">
						<dt><g:message code="project.dateCreated.label" default="Date Created" /></dt>

							<dd><g:formatDate format="MM-dd-yyyy" date="${projectInstance?.dateCreated}" /></dd>

					</g:if>

					<g:if test="${projectInstance?.createdBy}">
						<dt><g:message code="project.createdBy.label" default="Created By" /></dt>

							<dd><g:fieldValue bean="${projectInstance}" field="createdBy"/></dd>

					</g:if>

					<g:if test="${projectInstance?.lastUpdated}">
						<dt><g:message code="project.lastUpdated.label" default="Last Updated" /></dt>

							<dd><g:formatDate format="MM-dd-yyyy" date="${projectInstance?.lastUpdated}" /></dd>

					</g:if>

					<g:if test="${projectInstance?.updatedBy}">
						<dt><g:message code="project.updatedBy.label" default="Updated By" /></dt>

							<dd><g:fieldValue bean="${projectInstance}" field="updatedBy"/></dd>

					</g:if>

                    <g:if test="${projectInstance?.notes }">
                        <dt>Notes:</dt>
						<g:each in="${projectInstance.notes }" var="note">
						       <em>for <g:formatDate date="${note.lastUpdated }" format="MMM dd, yyyy" /> </em>
						       <dd>
	                            ${note.text.trim()}
	                            </dd>
	                    </g:each>
                    </g:if>

				</dl>

				<g:form>
					<g:hiddenField name="id" value="${projectInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${projectInstance?.id}">
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
