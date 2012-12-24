<%@ page import="org.ihc.esa.Project" %>
<%@ page import="org.ihc.esa.Party" %>

<!doctype html>
<html>
	<head>
		<meta name="layout" content="general">
		<g:set var="entityName" value="${message(code: 'project.label', default: 'Project')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>

	</head>
	<body>

		<div class="row-fluid">

			<div class="span2">
				<div class="well">
					<ul class="nav nav-pills nav-stacked">
						<li class="nav-header">${entityName}</li>
						<li class="active">
							<g:link action="list">
								<g:message code="default.list.label" args="[entityName]" />
							</g:link>
						</li>
						<li>
							<g:link action="list" params="[mine: 'true', sort: 'name', order: 'asc', ]">
								My Project List
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

                <g:form action="list">
                    <fieldset>
						<div class="page-header">
							<h1><g:message code="default.list.label" args="[entityName]" /></h1>
							<g:field type="hidden" name="filter" value="${filter ?: 'off' }" />
                            <g:select from="${Project.ProjectType }" name="filterByType"  optionKey="key" noSelection="['':'-Project Type-']"  value="${filterByType }"/>
                            <g:select from="${Project.ProjectStatus }" name="filterByStatus"  optionKey="key" noSelection="['':'-Project Status-']"  value="${filterByStatus }"/>
                            <g:select from="${Party.listArchitects }" name="filterByArchitect"  optionKey="id" optionValue="name" noSelection="['':'-Architect-']"  value="${filterByArchitect }"/>
                            <%
							String projectFilterClass = ""
							 if (filter.equals("off")) {
								 projectFilterClass = "btn btn-primary pull-right"
							 } else {
							     projectFilterClass = "btn btn-danger pull-right"
							 }
							 %>
							<button id="projectFilter" type="submit" class="${projectFilterClass }" name="setFilter" value="${filter.equals('off') ? 'on' : 'off' }" >
							    <i class="icon-filter icon-white"></i>
							    ${filter.equals('off') ? 'Filter' : 'Clear Filter' }
							</button>
						</div>
                    </fieldset>
                </g:form>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<table class="table table-striped">
					<thead>
						<tr>

							<g:sortableColumn property="id" title="${message(code: 'project.id.label', default: 'ACID')}" />

							<g:sortableColumn property="name" title="${message(code: 'project.name.label', default: 'Name')}" />

							<g:sortableColumn property="type" title="${message(code: 'project.type.label', default: 'Type')}" />

							<g:sortableColumn property="status" title="${message(code: 'project.status.label', default: 'Status')}" />

							<g:sortableColumn property="externalProjectNumber" title="${message(code: 'project.externalProjectNumber.label', default: 'External Project Number')}" />

							<th class="header"><g:message code="project.architect.label" default="Architect" /></th>

							<th class="header"><g:message code="project.projectManager.label" default="Project Manager" /></th>

							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${projectInstanceList}" var="projectInstance">
						<tr>
							<td><f:display bean="${projectInstance }" property="id" /></td>

							<td><f:display bean="${projectInstance }" property="name" /></td>

							<td><f:display bean="${projectInstance }" property="type" /></td>

							<td><f:display bean="${projectInstance }" property="status" /></td>

							<td><f:display bean="${projectInstance }" property="externalProjectNumber" /></td>

							<td><f:display bean="${projectInstance }" property="architect" /></td>

							<td><f:display bean="${projectInstance }" property="projectManager.name" /></td>

							<td class="link">
								<g:link action="show" id="${projectInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${projectInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
