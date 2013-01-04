<g:logMsg level="debug">= begin rendering of project/list view =</g:logMsg>
<g:logMsg level="debug">= params: ${params } =</g:logMsg>
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
							<g:link action="list" params="[mine: 'true']">
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

                <g:logMsg level="debug">= identifying filter button values =</g:logMsg>
                <g:logMsg level="debug">= filter: "${params.filter }" =</g:logMsg>

				<%
				String projectFilterClass = ""
				String projectFilterButtonText = ""
				String projectFilterNewValue = ""

				 if (params.filter?.equals("off")) {
					 projectFilterClass = "btn btn-primary pull-right"
					 projectFilterButtonText = "Filter"
					 projectFilterNewValue = "on"
				 } else if (params.filter?.equals("on")) {
				     projectFilterClass = "btn btn-danger pull-right"
					 projectFilterButtonText = "Clear Filter"
					 projectFilterNewValue = "off"
				 } else {
					params.filter = "off"
				    projectFilterClass = "btn btn-primary pull-right disabled"
					projectFilterButtonText = "Filter"
					projectFilterNewValue = "on"
				 }
				 %>

				<g:logMsg level="debug">projectFilterClass = ${projectFilterClass }</g:logMsg>
				<g:logMsg level="debug">projectFilterButtonText = ${projectFilterButtonText }</g:logMsg>
				<g:logMsg level="debug">projectFilterNewValue = ${projectFilterNewValue }</g:logMsg>

                <g:form action="list">
                    <fieldset>
						<div class="page-header">
							<h1><g:message code="default.list.label" args="[entityName]" /></h1>
							<g:field type="hidden" name="filter" value="${params.filter }" />
                            <g:select from="${Project.ProjectType }" name="filterByType"  optionKey="key" noSelection="['':'-Project Type-']"  value="${filterByType }"/>
                            <g:select from="${Project.ProjectStatus }" name="filterByStatus"  optionKey="key" noSelection="['':'-Project Status-']"  value="${filterByStatus }"/>
                            <g:select from="${Party.listArchitects }" name="filterByArchitect"  optionKey="id" optionValue="name" noSelection="['':'-Architect-']"  value="${filterByArchitect }"/>
							<button id="projectFilter" type="submit" class="${projectFilterClass }" name="setFilter" value="${projectFilterNewValue }" >
							    <i class="icon-filter icon-white"></i>
							    ${projectFilterButtonText }
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
					<g:logMsg level="debug">= start looping through projectInstanceList =</g:logMsg>
					<g:logMsg level="debug">= projectInstanceList: ${projectInstanceList } =</g:logMsg>
					<g:logMsg level="debug">= projectInstanceTotal: ${projectInstanceTotal } =</g:logMsg>

					<g:each in="${projectInstanceList}" var="projectInstance">
					   <g:logMsg level="debug">= project ${projectInstance.id } =</g:logMsg>
						<tr>
							<td><f:display bean="${projectInstance }" property="id" /></td>

							<td><f:display bean="${projectInstance }" property="name" /></td>

							<td><f:display bean="${projectInstance }" property="type" /></td>

							<td><f:display bean="${projectInstance }" property="status" /></td>

							<td><f:display bean="${projectInstance }" property="externalProjectNumber" /></td>

							<td><f:display bean="${projectInstance }" property="architects" /></td>

							<td><f:display bean="${projectInstance }" property="projectManager.name" /></td>

							<td class="link">
								<g:link action="show" id="${projectInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					<g:logMsg level="debug">= done looping through projects =</g:logMsg>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${projectInstanceTotal}" params="${params }"/>
				</div>
			</div>

		</div>
	</body>
</html>
