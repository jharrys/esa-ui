<%@ page import="org.ihc.esa.Party" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'party.label', default: 'Party')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">

			<div class="span2">
				<div class="well">
					<ul class="nav nav-pills nav-stacked">
						<li class="nav-header">${entityName}</li>
						<li>
							<g:link action="list">
								<g:message code="default.list.label" args="[entityName]" />
							</g:link>
						</li>
						<li>
							<g:link action="create">
								<g:message code="default.create.label" args="[entityName]" />
							</g:link>
						</li>
						<li>
                            <g:form class="form-inline" controller="party" action="search">
                                <input name="term" type="text" class="input-small search-query" placeholder="id or name">
                                <button type="submit" class="btn btn-small">Search</button>
                            </g:form>
                        </li>
					</ul>
				</div>
			</div>
			
			<div class="span9">

				<div class="page-header">
					<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${partyInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${partyInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

				<fieldset>
					<g:form class="form-horizontal" action="edit" id="${partyInstance?.id}" >
						<g:hiddenField name="version" value="${partyInstance?.version}" />
						<fieldset>
							<f:all bean="partyInstance" except="externalId,webSiteUrl,items,partyRelationshipParty,partyRelationshipParty1"/>
							<div class="form-actions">
								<button type="submit" class="btn btn-primary">
									<i class="icon-ok icon-white"></i>
									<g:message code="default.button.update.label" default="Update" />
								</button>
								<button type="submit" class="btn btn-danger" name="_action_delete" formnovalidate>
									<i class="icon-trash icon-white"></i>
									<g:message code="default.button.delete.label" default="Delete" />
								</button>
							</div>
						</fieldset>
					</g:form>
				</fieldset>

			</div>

		</div>
	</body>
</html>
