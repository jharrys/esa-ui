<%@ page import="org.ihc.esa.domain.Document"%>
<%@ page import="org.ihc.esa.domain.Form"%>
<%@ page import="org.ihc.esa.domain.FormField"%>
<%@ page import="org.ihc.esa.domain.LookupList"%>
<%@ page import="org.ihc.esa.domain.LookupElement"%>
<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<title>Submit Exception</title>
</head>
<body>

	<!-- begin content.navigation row 24 columns wide -->
	<div class="span-24">
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
		<h1>
			Section
			${section }
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
	</div>
	<!-- end content.section-header row 24 columns wide -->

	<!-- begin content.form row 24 columns wide -->
<%--	<div id="form" class="content scaffold-create span-24" role="main">--%>
	<div class="span-24">

		<%-- need to wrap g:form action in an if statement so that if sectionStack.empty() == true then change action to save() --%>
		<g:form action="create_next" params="${[document: documentInstance, formid: formid, sectionStack: sectionStack] }">

			<!-- begin content.form.questions -->
			<div class="span-24">
				<fieldset class="form">
					<g:render template="/form/form" />
				</fieldset>
			</div>
			<!-- end content.form.questions -->


			<!-- begin content.form.buttons -->
			<div class="span-24">
				<fieldset class="buttons">
					<%-- need to wrap g:submitButton action in an if statement so that if sectionStack.empty() == true then change action to save() --%>
					<g:submitButton name="create_next" class="save" value="${message(code: 'default.button.next.label', default: 'Next')}" />
				</fieldset>
			</div>
			<!-- end content.form.buttons -->

		</g:form>
	</div>
	<!-- end content.form row 24 columns wide -->
</body>
</html>
