
<%@ page import="org.ihc.esa.Item"%>
<!doctype html>
<html>
<head>
<meta name="layout" content="bootstrap">
<g:set var="entityName" value="${message(code: 'item.label', default: 'Item')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
	<div class="row-fluid">

		<div class="span2">
			<div class="well">
				<ul class="nav nav-list">
					<li class="nav-header">
						${entityName}
					</li>
					<li class="active"><g:link action="list">
							<i class="icon-list icon-white"></i>
							<g:message code="default.list.label" args="[entityName]" />
						</g:link></li>
					<li><g:link action="create">
							<i class="icon-plus"></i>
							<g:message code="default.create.label" args="[entityName]" />
						</g:link></li>
				</ul>
			</div>
		</div>

		<div class="span9">

			<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">
					${flash.message}
				</bootstrap:alert>
			</g:if>

			<table class="table table-striped">
				<thead>
					<tr>

						<g:sortableColumn property="name" title="${message(code: 'item.name.label', default: 'Name')}" />

						<g:sortableColumn property="description" title="${message(code: 'item.description.label', default: 'Description')}" />

						<g:sortableColumn property="sourceSystem" title="${message(code: 'item.sourceSystem.label', default: 'Source System')}" />

						<g:sortableColumn property="standard" title="${message(code: 'item.standard.label', default: 'Standard')}" />

						<th class="header"><g:message code="item.document.label" default="Document" /></th>

						<g:sortableColumn property="intermountainItemNumber" title="${message(code: 'item.intermountainItemNumber.label', default: 'Intermountain Item Number')}" />

						<th></th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${itemInstanceList}" var="itemInstance">
						<g:set var="isStandard" value="${itemInstance.standard == 'Y' ? true:false }"></g:set>
						<tr class="${isStandard }">

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
							
							    <g:if test="${isStandard }">
							     Yes
							     </g:if> 
							     <g:else>
									<span class="label label-important">No</span>
								</g:else>
							
							</td>

							<td>
								${itemInstance.document?.title}
							</td>

							<td>
								${fieldValue(bean: itemInstance, field: "intermountainItemNumber")}
							</td>


							<td class="link"><g:link action="show" id="${itemInstance.id}" class="btn btn-small">Show &raquo;</g:link></td>
						</tr>
					</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<bootstrap:paginate total="${itemInstanceTotal}" />
			</div>
		</div>

	</div>
</body>
</html>
