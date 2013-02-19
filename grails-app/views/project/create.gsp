<%@ page import="org.ihc.esa.Project" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'project.label', default: 'Project')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">

			<div class="span2">
                <div class="well">
                    <ul class="nav nav-pills nav-stacked">
                        <li class="nav-header">${entityName}</li>
                        <li <%= request.forwardURI == "${createLink(uri: '/project/list')}" ? ' class="active"' : '' %>>
                            <g:link action="list">
                                <g:message code="default.list.label" args="[entityName]" />
                            </g:link>
                        </li>
                        <li <%= request.forwardURI.contains("filter") ? ' class="active"' : '' %>>
                            <g:link action="list" params="[mine: 'true']">
                                My Project List
                            </g:link>
                        </li>
                        <li <%= request.forwardURI == "${createLink(uri: '/project/create')}" ? ' class="active"' : '' %>>
                            <g:link action="create">
                                <g:message code="default.create.label" args="[entityName]" />
                            </g:link>
                        </li>
                        <li>
                            <form class="form-inline" action="show">
                                <input name="id" type="text" class="input-small search-query" placeholder="acid #">
                                <button type="submit" class="btn btn-small">Search</button>
                            </form>
                        </li>
                    </ul>
                </div>
            </div>

			<div class="span9">

				<div class="page-header">
					<h1><g:message code="default.create.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${projectInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${projectInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

				<fieldset>
					<g:form class="form-horizontal" action="create" >
						<fieldset>
							<f:all bean="projectInstance" />
							<div class="form-actions">
								<button type="submit" class="btn btn-primary">
									<i class="icon-ok icon-white"></i>
									<g:message code="default.button.create.label" default="Create" />
								</button>
							</div>
						</fieldset>
					</g:form>
				</fieldset>

			</div>

		</div>
	</body>
</html>
