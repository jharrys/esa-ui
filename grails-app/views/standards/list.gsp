<%@ page import="org.ihc.esa.Document" %>
<!doctype html>
<html>
	<head>
		<g:set var="entityName" value="${message(code: 'standard.label', default: 'Standard')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
	    <script>
		    $(document).ready(function() {
		        $('#itemListTable').on('click', 'tbody tr', function() {
			        var itemId = this.cells[0].innerText;
			        setEditItemLink(itemId);
			        setDeleteItemLink(itemId);
			        $(this).addClass('alert').siblings().removeClass('alert');
			    });
			});
	    </script>
		<div class="span10">

			<div class="page-header">
				<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			</div>

			<g:if test="${flash.message}">
			    <%
				    String alertType = 'alert-info'
				    String[] alertMessage = flash.message.split(":")
					if (alertMessage.length > 1) {
						alertType = alertMessage[0]
						flash.message = alertMessage[1]
					}
				 %>
                <bootstrap:alert class="${alertType }">${flash.message }</bootstrap:alert>
			</g:if>

			<table id="itemListTable" class="table table-condensed table-bordered">
				<thead>
					<tr>

						<g:sortableColumn property="id" title="id" hidden="hidden" />

						<g:sortableColumn property="name" title="${message(code: 'item.name.label', default: 'Item')}" />

						<g:sortableColumn property="description" title="${message(code: 'item.description.label', default: 'Description')}" />

						<g:sortableColumn property="comments" title="${message(code: 'item.comments.label', default: 'Comments')}" />

						<th></th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${itemInstanceList}" var="itemInstance">
					<tr>

						<td hidden="hidden">${fieldValue(bean: itemInstance, field: "id")}</td>

						<td>${fieldValue(bean: itemInstance, field: "name")}</td>

						<td><div style="word-wrap: break-word">${fieldValue(bean: itemInstance, field: "description")}</div></td>

						<td>${fieldValue(bean: itemInstance, field: "comments")}</td>

						<td class="link">
							<g:link action="show" id="${itemInstance.id}" class="btn btn-small">Show &raquo;</g:link>
						</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<bootstrap:paginate total="${itemInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
