<g:logMsg level="debug">= begin rendering of project/list view =</g:logMsg>
<g:logMsg level="debug">= params: ${params } =</g:logMsg>
<%@ page import="org.ihc.esa.Project" %>
<%@ page import="org.ihc.esa.Project.ProjectStatus" %>
<%@ page import="org.ihc.esa.Party" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy")
def jparams = [:]
jparams['mine'] = params.mine
jparams['setFilter'] = params.filter
jparams['filterByArchitect'] = params.filterByArchitect
jparams['filterByStatus'] = params.filterByStatus
jparams['filterByType'] = params.filterByType
jparams['filterByName'] = params.filterByName
 %>

<!doctype html>
<html>
	<head>
		<meta name="layout" content="general">
		<g:set var="entityName" value="${message(code: 'project.label', default: 'Project')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>

	</head>
	<body>

	   <r:script>
	       function toggleFilterOn() {
	           var filterByType = $('#filterByType').val()
	           var filterByStatus = $('#filterByStatus').val()
	           var filterByArchitect = $('#filterByArchitect').val()
	           var filterByName = $('#filterByName').val()

	           if (filterByType || filterByStatus || filterByArchitect || filterByName) {
		           var projectFilter = $('#projectFilter')
	               projectFilter.val('on')
	               projectFilter.removeClass('btn-danger')
	               projectFilter.addClass('btn-primary')
	               pfHtml = projectFilter.html()
	               projectFilter.html(pfHtml.replace(/Clear Filter/,'Filter'))
	           }
	       };
	   </r:script>

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
                            <g:select from="${Project.ProjectType }" name="filterByType"  optionKey="key" noSelection="['':'-Project Type-']"  value="${params.filterByType }" onchange="toggleFilterOn();" />
                            <g:select from="${Project.ProjectStatus }" name="filterByStatus"  optionKey="key" noSelection="['':'-Project Status-']"  value="${params.filterByStatus }" onchange="toggleFilterOn();" />
                            <g:select from="${Party.listEnterpriseArchitects }" name="filterByArchitect"  optionKey="id" optionValue="name" noSelection="['':'-Architect-']"  value="${params.filterByArchitect }" onchange="toggleFilterOn();" />
                            <g:textField name="filterByName"  onchange="toggleFilterOn();"  placeholder="filter by text in name of project" value="${params.filterByName }"/>
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

				<table class="table">
					<thead>
						<tr>

							<g:sortableColumn action="list" property="id" params="${jparams }" title="${message(code: 'project.id.label', default: 'ACID')}" />

							<g:sortableColumn action="list" property="name" params="${jparams }" title="${message(code: 'project.name.label', default: 'Name')}" />

							<g:sortableColumn action="list" property="type" params="${jparams }" title="${message(code: 'project.type.label', default: 'Type')}" />

							<g:sortableColumn action="list" property="status" params="${jparams }" title="${message(code: 'project.status.label', default: 'Status')}" />

							<th class="header"><g:message code="project.architect.label" default="Architect" /></th>

							<th class="header"><g:message code="project.projectManager.label" default="Project Manager" /></th>
							
							<th class="header">Last Updated</th>

							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:logMsg level="debug">= start looping through projectInstanceList =</g:logMsg>
					<g:logMsg level="debug">= projectInstanceList: ${projectInstanceList } =</g:logMsg>
					<g:logMsg level="debug">= projectInstanceTotal: ${projectInstanceTotal } =</g:logMsg>

					<g:each in="${projectInstanceList}" var="projectInstance">
						    <g:logMsg level="debug">= project ${projectInstance.id } =</g:logMsg>
						    <g:if test="${(projectInstance.status != ProjectStatus.CLOSED) && ((new Date()) - projectInstance.lastUpdated) > 90}">
						       <tr class="error">
	                        </g:if>
	                        <g:elseif test="${(projectInstance.status != ProjectStatus.CLOSED) && ((new Date()) - projectInstance.lastUpdated) > 30}">
	                            <tr class="warning">
	                        </g:elseif>
	                        <g:else>
	                            <tr>
	                        </g:else>
							<td>
							     <g:if test="${projectInstance.notes }">
                                     <%
									    def note = projectInstance.notes.last()
                                        def noteId = note.id
                                        String fullNoteText = "<b><small>${sdf.format(note.lastUpdated)}</small></b><br>${note.text.trim()}"
                                      %>
                                     <g:link action="show" id="${projectInstance.id }" elementId="${projectInstance.id }"  rel="tooltip" data-content="${fullNoteText }" >
                                         <span class="label label-warning"><f:display bean="${projectInstance }" property="id" /></span>
                                     </g:link>
                                     <script>
                                        $(function() {
                                            $('#${projectInstance.id}').popover({trigger: 'hover', placement: 'top', title: 'Most Recent Note', html: true})
                                        });
                                    </script>
                                 </g:if>
                                 <g:else>
                                     <g:link action="show" id="${projectInstance.id }">
                                         <span><f:display bean="${projectInstance }" property="id" /></span>
                                     </g:link>
                                 </g:else>
                            </td>

							<td><f:display bean="${projectInstance }" property="name" /></td>

							<td><f:display bean="${projectInstance }" property="type" /></td>

							<td><f:display bean="${projectInstance }" property="status" /></td>

							<td><f:display bean="${projectInstance }" property="architects"  /></td>

							<td><f:display bean="${projectInstance }" property="projectManager.name" /></td>
							
							<td>
							     <%
						              Date today = new Date()
						              if ((today - projectInstance.lastUpdated) > 90) {
						                  out << "> 90 days"
						              } else if ((today - projectInstance.lastUpdated) > 30) {
						                  out << "> 30 days"
						              } else {
						                  out << "< 30 days"
						              }
						           %>
							</td>

							<td class="link">
							    <g:if test="${(projectInstance.status == ProjectStatus.CLOSED) }">
                                    <g:link class="btn btn-small" action="edit" id="${projectInstance?.id}">
                                        <i class="icon-edit"></i>
                                        <g:message code="default.button.edit.label" default="Edit" />
                                    </g:link>
							    </g:if>
							    <g:else>
							        <g:link class="btn btn-small" action="close" id="${projectInstance?.id}">
                                        <i class="icon-check"></i>
                                        <g:message code="default.button.close.label" default="Close" />
                                    </g:link>
                                </g:else>
							</td>
						</tr>
					</g:each>
					<g:logMsg level="debug">= done looping through projects =</g:logMsg>
					</tbody>
				</table>
				<div class="pagination">
				    <%
					params['projectInstanceTotal'] = projectInstanceTotal
					 %>
					<bootstrap:paginate total="${projectInstanceTotal}" params="${params }"/>
				</div>
			</div>

		</div>
	</body>
</html>
