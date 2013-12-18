
<%@ page import="org.ihc.esa.Party" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="general">
		<g:set var="entityName" value="${message(code: 'party.label', default: 'Party')}" />
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
							<g:link action="create">
								<g:message code="default.create.label" args="[entityName]" />
							</g:link>
						</li>
						 <li>
                            <g:form class="form-inline" controller="party" action="search">
                                <input name="term" type="text" class="input-small search-query" placeholder="id or name">
                                <button type="submit" class="btn btn-small">Search</button>
                            </g:form>
                        </li>
					</ul>
				</div>
			</div>

			<div class="span9">
				
				<div class="page-header">
					<h1><g:message code="default.list.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				
				<table class="table table-striped">
					<thead>
						<tr>
						
							<g:sortableColumn property="id" title="${message(code: 'party.id.label', default: 'Id')}" />
						
							<g:sortableColumn property="type" title="${message(code: 'party.type.label', default: 'Type')}" />
						
							<g:sortableColumn property="name" title="${message(code: 'party.name.label', default: 'Name')}" />
						
							<g:sortableColumn property="emailAddress" title="${message(code: 'party.emailAddress.label', default: 'Email Address')}" />
						
							<g:sortableColumn property="workPhoneNumber" title="${message(code: 'party.workPhoneNumber.label', default: 'Work Phone Number')}" />
							
                            <g:sortableColumn property="dateCreated" title="${message(code: 'party.dateCreated.label', default: 'Date Created')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:logMsg level="debug">partyInstanceList: < ${partyInstanceList } ></g:logMsg>
					<g:each in="${partyInstanceList}" var="partyInstance">
						<tr>
						
							<td>${partyInstance.id.encodeAsHTML()}</td>
						
							<td>${fieldValue(bean: partyInstance, field: "type")}</td>
						
							<td>${fieldValue(bean: partyInstance, field: "name")}</td>
						
							<td>${fieldValue(bean: partyInstance, field: "emailAddress")}</td>
						
							<td>${fieldValue(bean: partyInstance, field: "workPhoneNumber")}</td>
							
							<td><g:formatDate date="${partyInstance.dateCreated}" format="yyyy-MM-dd'"/></td>
							
							<td class="link">
								<g:link action="show" id="${partyInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${partyInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
