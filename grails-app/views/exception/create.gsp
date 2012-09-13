<%@ page import="org.ihc.esa.Document"%>
<%@ page import="org.ihc.esa.Form"%>
<%@ page import="org.ihc.esa.FormField"%>
<%@ page import="org.ihc.esa.LookupList"%>
<%@ page import="org.ihc.esa.LookupElement"%>
<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<title>Submit Exception</title>
</head>
<body>
	<!-- begin content.navigation row 24 columns wide -->
	<div class="nav" role="navigation">
		<a href="#create-document" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;" /></a>
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label" /></a></li>
			<li><g:link class="list" action="list">
					Exception List
				</g:link></li>
		</ul>
	</div>
	<!-- end content.navigation row 24 columns wide -->
	<!-- begin content.section-header row 24 columns wide -->
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
	<!-- end content.section-header row 24 columns wide -->
	<!-- begin content.form row 24 columns wide -->
	<div id="form" class="content scaffold-create span-24" role="main">
		<%-- need to wrap g:form action in an if statement so that if sectionStack.empty() == true then change action to save() --%>
		<g:form action="create_next" params="${[document: documentInstance, formid: formid, sectionStack: sectionStack] }">
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
								<g:submitButton name="create_next" class="save" value="${message(code: 'default.button.next.label', default: 'Next')}" />
							</fieldset>
							<!-- end content.form.buttons -->
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
	<!-- end content.form row 24 columns wide -->
</body>
</html>
