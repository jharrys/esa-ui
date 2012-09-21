
<%@ page import="org.ihc.esa.Document"%>
<!doctype html>
<html>
<head>
<meta name="layout" content="bootstrap">
<g:set var="entityName" value="${message(code: 'exception.label', default: 'Exception')}" />
<title>
	${exceptionInstance.title }
</title>
</head>
<body>
	<div class="row-fluid">

		<div class="span2">
			<div class="well">
				<ul class="nav nav-list">
					<li class="nav-header">
						${entityName}
					</li>
					<li><g:link action="list">
							<i class="icon-list"></i>
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

			<div class="page-header">
				<h1>
					${exceptionInstance.title }
				</h1>
			</div>

			<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">
					${flash.message}
				</bootstrap:alert>
			</g:if>

			<dl>

				<g:if test="${exceptionInstance?.form}">
					<dt>
						<g:message code="exception.form.label" default="Form Type" />
					</dt>

					<dd>
						<g:fieldValue bean="${exceptionInstance}" field="form" />
					</dd>

				</g:if>

				<g:if test="${exceptionInstance?.sirpId}">
					<dt>
						<g:message code="exception.sirpId.label" default="SIRP ID" />
					</dt>

					<dd>
						<g:fieldValue bean="${exceptionInstance}" field="sirpId" />
					</dd>

				</g:if>

				<g:if test="${exceptionInstance?.requestor}">
					<dt>
						<g:message code="exception.requestor.label" default="Requester" />
					</dt>

                    <dd>
					    <g:fieldValue bean="${exceptionInstance}" field="requestor" />
					</dd>

				</g:if>

				<g:if test="${exceptionInstance?.requestorEmail}">
					<dt>
						<g:message code="exception.requestorEmail.label" default="Requester Email" />
					</dt>

					<dd>
						<g:fieldValue bean="${exceptionInstance}" field="requestorEmail" />
					</dd>

				</g:if>

				<g:if test="${exceptionInstance?.owner}">
					<dt>
						<g:message code="exception.owner.label" default="Business Owner" />
					</dt>

					<dd>
						<g:fieldValue bean="${exceptionInstance}" field="owner" />
					</dd>

				</g:if>

				<g:if test="${exceptionInstance?.ownerEmail}">
					<dt>
						<g:message code="exception.ownerEmail.label" default="Business Owner Email" />
					</dt>

					<dd>
						<g:fieldValue bean="${exceptionInstance}" field="ownerEmail" />
					</dd>

				</g:if>

				<g:if test="${exceptionInstance?.justification}">
					<dt>
						<g:message code="exception.justification.label" default="Justification" />
					</dt>

					<dd>
						<g:fieldValue bean="${exceptionInstance}" field="justification" />
					</dd>

				</g:if>

				<g:if test="${exceptionInstance?.vendorRepresentativeParty}">
					<dt>
						<g:message code="exception.vendorRepresentativeParty.label" default="Vendor" />
					</dt>

					<dd>
						<g:link controller="party" action="show" id="${exceptionInstance?.vendorRepresentativeParty?.id}">
                            ${exceptionInstance?.vendorRepresentativeParty?.encodeAsHTML()}
                        </g:link>
					</dd>

				</g:if>

				<g:if test="${exceptionInstance?.createdBy}">
					<dt>
						<g:message code="exception.createdBy.label" default="Created By" />
					</dt>

					<dd>
						<g:fieldValue bean="${exceptionInstance}" field="createdBy" />
					</dd>

				</g:if>

				<g:if test="${exceptionInstance?.updatedBy}">
					<dt>
						<g:message code="exception.updatedBy.label" default="Updated By" />
					</dt>

					<dd>
						<g:fieldValue bean="${exceptionInstance}" field="updatedBy" />
					</dd>

				</g:if>

				<g:if test="${exceptionInstance?.dateCreated}">
					<dt>
						<g:message code="exception.dateCreated.label" default="Date Created" />
					</dt>

					<dd>
						<g:formatDate date="${exceptionInstance?.dateCreated}" />
					</dd>

				</g:if>

				<g:if test="${exceptionInstance?.lastUpdated}">
					<dt>
						<g:message code="exception.lastUpdated.label" default="Last Updated" />
					</dt>

					<dd>
						<g:formatDate date="${exceptionInstance?.lastUpdated}" />
					</dd>

				</g:if>

			</dl>

			<g:form>
				<g:hiddenField name="id" value="${exceptionInstance?.id}" />
				<div class="form-actions">
					<g:link class="btn" action="edit" id="${exceptionInstance?.id}">
						<i class="icon-pencil"></i>
						<g:message code="default.button.edit.label" default="Edit" />
					</g:link>
					<button class="btn btn-danger" type="submit" name="_action_delete">
						<i class="icon-trash icon-white"></i>
						<g:message code="default.button.delete.label" default="Delete" />
					</button>
				</div>
			</g:form>

		</div>

	</div>
</body>
</html>
