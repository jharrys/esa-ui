<%@ page import="org.ihc.esa.domain.Document" %>
<%@ page import="org.ihc.esa.domain.Form" %>
<%@ page import="org.ihc.esa.domain.FormField" %>
<%@ page import="org.ihc.esa.domain.LookupList" %>
<%@ page import="org.ihc.esa.domain.LookupElement" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'document.label', default: 'Document')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-document" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li>Section ${section }</li>
			</ul>
		</div>
		<div id="create-document" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${documentInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${documentInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
            <%-- need to wrap g:form action in an if statement so that if sectionStack.empty() == true then change action to save() --%>
			<g:form action="create_next" params="${[document: documentInstance, formid: formid, sectionStack: sectionStack] }" >
				<fieldset class="form">
					<g:render template="/form/form"/>
				</fieldset>
				<fieldset class="buttons">
				    <%-- need to wrap g:submitButton action in an if statement so that if sectionStack.empty() == true then change action to save() --%>
					<g:submitButton name="create_next" class="next" value="${message(code: 'default.button.next.label', default: 'Next')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
