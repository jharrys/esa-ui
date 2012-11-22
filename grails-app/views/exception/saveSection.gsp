<%@ page import="org.ihc.esa.Document"%>
<%@ page import="org.ihc.esa.Form"%>
<%@ page import="org.ihc.esa.FormField"%>
<%@ page import="org.ihc.esa.LookupList"%>
<%@ page import="org.ihc.esa.LookupElement"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!doctype html>
<html>
	<head>
	    <meta name="layout" content="bootstrap">
	    <title>New Exception</title>
	</head>
	<body>

		<g:logMsg level="debug">===========================================================================</g:logMsg>
		<g:logMsg level="debug">Rendering inside saveSection.gsp</g:logMsg>
		<g:logMsg level="debug">===========================================================================</g:logMsg>
		<g:logMsg level="debug">Params are: ${params }
		</g:logMsg>

		<% SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat) %>

		<div class="row-fluid">

			<div class="span12">

				<div class="page-header">
					<h1>Application for Exception</h1>
				</div>

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
					(${sectionTitle })
				</h3>

				<g:if test="${flash.message}">
					<bootstrap:alert class="alert-info">
						${flash.message}
					</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${documentInstance}">
					<bootstrap:alert class="alert-error">
						<ul>
							<g:eachError bean="${documentInstance}" var="error">
								<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
							</g:eachError>
						</ul>
					</bootstrap:alert>
				</g:hasErrors>

				<fieldset>
					<g:form action="saveSection" params="${[sectionStack: sectionStack] }">
						<g:hiddenField name="id" value="${documentInstance?.id}" />
						<g:hiddenField name="formid" value="${formid}" />

						<g:render template="form" />

						<div class="form-actions">
							<button type="submit" class="btn btn-primary">
								<i class="icon-ok icon-white"></i> ${max ? "Next Section" : "Complete Exception" }
							</button>
							<button type="submit" class="btn btn-warning" name="finishLater" value="true">
								<i class="icon-ok icon-white"></i> ${max ? "Save (Finish Later)" : "Hide this button" }
							</button>
							<button type="submit" class="btn btn-danger" name="cancel" value="true">
								<i class="icon-remove icon-white"></i> Cancel
							</button>
						</div>

					</g:form>
				</fieldset>

			</div>

		</div>

	</body>
</html>
