
<%@ page import="org.ihc.esa.Item"%>
<!doctype html>
<html>
<head>
<meta name="layout" content="bootstrap">
<title>Catalog Items</title>
</head>
<body>

	<div class="row-fluid">

		<aside id="application-status" class="span2">

			<div class="well sidebar-nav">
				<h3>Catalog Menu</h3>
				<ul class="nav nav-pills nav-stacked">
					<li class="active"><g:link action="list">List Items</g:link></li>
					<li><g:link action="create">
							Create New Items
						</g:link></li>
					<li>
                        <g:form class="form-search" action="itemSearch" >
                            <div class="input-append">
                                <input type="text" class="span7 search-query" name="q" id="q">
                                <button type="submit" value="Search" class="btn">Find</button>
                            </div>
                        </g:form>
                    </li>
				</ul>
			</div>

		</aside>

		<section id="main" class="span9">

			<div class="hero-unit">
				<h2>Catalog Items</h2>
				<g:if test="${flash.message}">
					<div class="alert alert-block">
						${flash.message}
					</div>
				</g:if>

				<table class="table table-striped table-bordered">
					<thead>
						<tr>

							<g:sortableColumn property="name" title="${message(code: 'item.name.label', default: 'Category / Name')}" />

							<g:sortableColumn property="description" title="${message(code: 'item.description.label', default: 'Description')}" />

							<g:sortableColumn property="sourceSystem" title="${message(code: 'item.sourceSystem.label', default: 'Source System')}" />

							<g:sortableColumn property="standard" title="${message(code: 'item.standard.label', default: 'IH Standard')}" />

							<th><g:message code="item.document.label" default="Linked Document" /></th>

							<g:sortableColumn property="intermountainItemNumber" title="${message(code: 'item.intermountainItemNumber.label', default: 'IH Item #')}" />

							<th><g:message code="item.vendorParty.label" default="Vendor Party" /></th>

						</tr>
					</thead>
					<tbody>
						<g:each in="${itemInstanceList}" status="i" var="itemInstance">
							<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

								<td>
									${fieldValue(bean: itemInstance, field: "name")}
								</td>

								<td>
									${fieldValue(bean: itemInstance, field: "description")}
								</td>

								<td>
									${fieldValue(bean: itemInstance, field: "sourceSystem")}
								</td>

								<td>
									${fieldValue(bean: itemInstance, field: "standard")}
								</td>

								<td>
									${fieldValue(bean: itemInstance, field: "document")}
								</td>

								<td>
									${fieldValue(bean: itemInstance, field: "intermountainItemNumber")}
								</td>

								<td>
									${fieldValue(bean: itemInstance, field: "vendorParty.name")}
								</td>



							</tr>
						</g:each>
					</tbody>
				</table>

				<div class="pagination">
					<g:paginate total="${itemInstanceTotal}" />
				</div>

			</div>

		</section>

	</div>
</body>
</html>
