<%@ page import="org.ihc.esa.Document"%>
<!doctype html>
<html>
<head>
<meta name="layout" content="bootstrap">
<g:set var="entityName" value="${message(code: 'exception.label', default: 'Exception')}" />
<title>Exception List</title>
</head>
<body>
	<div class="row-fluid">

		<aside id="application-status" class="span2">

			<div class="well sidebar-nav">
				<h3>Exception Menu</h3>
				<ul class="nav nav-pills nav-stacked">
					<li class="active"><g:link action="list">List Exceptions</g:link></li>
					<li><g:link action="create">
							<g:message code="default.new.label" args="[entityName]" />
						</g:link></li>
					<li><g:link action="search">Search Exceptions</g:link></li>
				</ul>
			</div>

		</aside>

		<section id="main" class="span9">

			<div class="hero-unit">
				<h2>
					<g:message code="default.list.label" args="[entityName]" />
				</h2>
				<g:if test="${flash.message}">
					<div class="alert alert-block">
						${flash.message}
					</div>
				</g:if>
				
				<table class="table table-striped table-bordered">
					<thead>
						<tr>

							<g:sortableColumn property="form" title="Exception Name" />

							<g:sortableColumn property="owner" title="${message(code: 'document.owner.label', default: 'Owner')}" />

							<g:sortableColumn property="requestor" title="${message(code: 'document.requestor.label', default: 'Requestor')}" />

							<g:sortableColumn property="sirpId" title="${message(code: 'document.sirpId.label', default: 'Security Incident Response Id')}" />

                            <th></th>

						</tr>
					</thead>
					<tbody>
						<g:each in="${documentInstanceList}" status="i" var="documentInstance">
							<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

								<td><g:link action="show" id="${documentInstance.id}">
										${fieldValue(bean: documentInstance, field: "title")}
									</g:link></td>

								<td>
									${fieldValue(bean: documentInstance, field: "owner")}
								</td>

								<td>
									${fieldValue(bean: documentInstance, field: "requestor")}
								</td>

								<td>
								    <g:if test="${(documentInstance.sirpId != null) && (!documentInstance.sirpId.empty) }">
									   ${fieldValue(bean: documentInstance, field: "sirpId")}
								    </g:if>
								    <g:else>
								        <span class="muted">SIRP will be provided by ISSA when their review is complete</span>
									</g:else>
								</td>
								
								<td class="link"><g:link action="show" id="${documentInstance.id}" class="btn btn-small">Show &raquo;</g:link></td>

							</tr>
						</g:each>
					</tbody>
				</table>
				
				<div class="pagination">
					<g:paginate total="${documentInstanceTotal}" />
				</div>
				
			</div>

		</section>

	</div>
</body>
</html>
