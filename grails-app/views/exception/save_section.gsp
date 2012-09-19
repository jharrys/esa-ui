<%@ page import="org.ihc.esa.Document"%>
<%@ page import="org.ihc.esa.Form"%>
<%@ page import="org.ihc.esa.FormField"%>
<%@ page import="org.ihc.esa.LookupList"%>
<%@ page import="org.ihc.esa.LookupElement"%>
<!doctype html>
<html>
<head>
<meta name="layout" content="bootstrap">
<title>New Exception</title>
</head>
<body>
	<div class="row-fluid">
	
		<div class="span-24">
			<hr class="clear" />
			<h3>
				Section
				${section }
				<%
				    max = sectionStack.max{a,b->
						if (!(a instanceof BigDecimal)) { 
				            a.toBigDecimal().equals(b.toBigDecimal())? 0: a.toBigDecimal()<b.toBigDecimal()? -1: 1
						} else {
						  a.equals(b) ? 0: a<b ? -1: 1
						}
				    }
			%>
				<% if (max != null) { %>
			<%= "of  ${max}" %>
			<% } %>
			</h3>
			<g:if test="${flash.message}">
				<div class="message" role="status">
					${flash.message}
				</div>
			</g:if>
		</div>

		<div id="form" class="content scaffold-create span-24" role="main">
			<%-- need to wrap g:form action in an if statement so that if sectionStack.empty() == true then change action to save() --%>
			<g:form action="save_section" params="${[document: documentInstance, formid: formid, sectionStack: sectionStack] }">
				<table summary="Two column table with labels and inputs" border="0" cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<th class="span-10"></th>
							<th class="span-14 last"></th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="2">
								<!-- begin content.form.buttons -->
								<fieldset class="buttons">
									<%-- need to wrap g:submitButton action in an if statement so that if sectionStack.empty() == true then change action to save() --%>
									<g:submitButton name="save" class="save" value="${message(code: 'default.button.next.label', default: 'Next')}" />
								</fieldset> <!-- end content.form.buttons -->
							</td>
						</tr>
					</tfoot>
					<tbody>
						<!-- begin content.form.questions -->
						<g:render template="/form/form" />
						<!-- end content.form.questions -->
					</tbody>
				</table>
			</g:form>
		</div>

	</div>
</body>
</html>
