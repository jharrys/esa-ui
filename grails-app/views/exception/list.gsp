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

							<g:sortableColumn property="form" title="Exception Number" />

							<g:sortableColumn property="title" title="${message(code: 'document.title.label', default: 'Title')}" />
							
							<g:sortableColumn property="createdBy" title="${message(code: 'document.createdBy.label', default: 'Created By')}" />

							<g:sortableColumn property="updatedBy" title="${message(code: 'document.updatedBy.label', default: 'Updated By')}" />

                            <th></th>

						</tr>
					</thead>
					<tbody>
						<g:each in="${documentInstanceList}" status="i" var="documentInstance">
							<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

								<td><g:link action="show" id="${documentInstance.id}">
										${fieldValue(bean: documentInstance, field: "id")}
									</g:link>
								</td>
								
								<td>
								    ${fieldValue(bean: documentInstance, field: "title") }
								</td>

								<td>
									${fieldValue(bean: documentInstance, field: "createdBy")}
								</td>

								<td>
									${fieldValue(bean: documentInstance, field: "updatedBy")}
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
