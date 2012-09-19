
<%@ page import="org.ihc.esa.Document"%>
<!doctype html>
<html>
<head>
<meta name="layout" content="bootstrap">
<g:set var="entityName" value="Exception" />
<title>Exception</title>
</head>
<body>

	<div>
		<h1>EISA Exception</h1>
		<g:if test="${flash.message}">
			<div class="alert alert-block">
				${flash.message}
			</div>
		</g:if>
		
		<ol class="property-list">

			<g:if test="${documentInstance?.form}">
				<li class="fieldcontain"><span id="form-label" class="property-label"><g:message code="document.form.label" default="Form" /></span> <span
					class="property-value" aria-labelledby="form-label"
				><g:link controller="form" action="show" id="${documentInstance?.form?.id}">
							${documentInstance?.form?.encodeAsHTML()}
						</g:link></span></li>
			</g:if>

			<g:if test="${documentInstance?.sirpId}">
				<li class="fieldcontain"><span id="sirpId-label" class="property-label"><g:message code="document.sirpId.label" default="Sirp Id" /></span> <span
					class="property-value" aria-labelledby="sirpId-label"
				><g:fieldValue bean="${documentInstance}" field="sirpId" /></span></li>
			</g:if>

			<g:if test="${documentInstance?.requestor}">
				<li class="fieldcontain"><span id="requestor-label" class="property-label"><g:message code="document.requestor.label" default="Requestor" /></span> <span
					class="property-value" aria-labelledby="requestor-label"
				><g:fieldValue bean="${documentInstance}" field="requestor" /></span></li>
			</g:if>

			<g:if test="${documentInstance?.requestorEmail}">
				<li class="fieldcontain"><span id="requestorEmail-label" class="property-label"><g:message code="document.requestorEmail.label"
							default="Requestor Email"
						/></span> <span class="property-value" aria-labelledby="requestorEmail-label"><g:fieldValue bean="${documentInstance}" field="requestorEmail" /></span></li>
			</g:if>

			<g:if test="${documentInstance?.owner}">
				<li class="fieldcontain"><span id="owner-label" class="property-label"><g:message code="document.owner.label" default="Owner" /></span> <span
					class="property-value" aria-labelledby="owner-label"
				><g:fieldValue bean="${documentInstance}" field="owner" /></span></li>
			</g:if>

			<g:if test="${documentInstance?.ownerEmail}">
				<li class="fieldcontain"><span id="ownerEmail-label" class="property-label"><g:message code="document.ownerEmail.label" default="Owner Email" /></span>

					<span class="property-value" aria-labelledby="ownerEmail-label"><g:fieldValue bean="${documentInstance}" field="ownerEmail" /></span></li>
			</g:if>

			<g:if test="${documentInstance?.justification}">
				<li class="fieldcontain"><span id="justification-label" class="property-label"><g:message code="document.justification.label"
							default="Justification"
						/></span> <span class="property-value" aria-labelledby="justification-label"><g:fieldValue bean="${documentInstance}" field="justification" /></span></li>
			</g:if>

			<g:if test="${documentInstance?.vendorRepresentativeParty}">
				<li class="fieldcontain"><span id="vendorRepresentativeParty-label" class="property-label"><g:message
							code="document.vendorRepresentativeParty.label" default="Vendor Representative Party"
						/></span> <span class="property-value" aria-labelledby="vendorRepresentativeParty-label"><g:link controller="party" action="show"
							id="${documentInstance?.vendorRepresentativeParty?.id}"
						>
							${documentInstance?.vendorRepresentativeParty?.encodeAsHTML()}
						</g:link></span></li>
			</g:if>

			<g:if test="${documentInstance?.createdBy}">
				<li class="fieldcontain"><span id="createdBy-label" class="property-label"><g:message code="document.createdBy.label" default="Created By" /></span> <span
					class="property-value" aria-labelledby="createdBy-label"
				><g:fieldValue bean="${documentInstance}" field="createdBy" /></span></li>
			</g:if>

			<g:if test="${documentInstance?.updatedBy}">
				<li class="fieldcontain"><span id="updatedBy-label" class="property-label"><g:message code="document.updatedBy.label" default="Updated By" /></span> <span
					class="property-value" aria-labelledby="updatedBy-label"
				><g:fieldValue bean="${documentInstance}" field="updatedBy" /></span></li>
			</g:if>

			<g:if test="${documentInstance?.dateCreated}">
				<li class="fieldcontain"><span id="dateCreated-label" class="property-label"><g:message code="document.dateCreated.label" default="Date Created" /></span>

					<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${documentInstance?.dateCreated}" /></span></li>
			</g:if>

			<g:if test="${documentInstance?.items}">
				<li class="fieldcontain"><span id="items-label" class="property-label"><g:message code="document.items.label" default="Items" /></span> <g:each
						in="${documentInstance.items}" var="i"
					>
						<span class="property-value" aria-labelledby="items-label"><g:link controller="item" action="show" id="${i.id}">
								${i?.encodeAsHTML()}
							</g:link></span>
					</g:each></li>
			</g:if>

			<g:if test="${documentInstance?.lastUpdated}">
				<li class="fieldcontain"><span id="lastUpdated-label" class="property-label"><g:message code="document.lastUpdated.label" default="Last Updated" /></span>

					<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${documentInstance?.lastUpdated}" /></span></li>
			</g:if>

			<g:if test="${documentInstance?.responses}">
				<li class="fieldcontain"><span id="responses-label" class="property-label"><g:message code="document.responses.label" default="Responses" /></span> <g:each
						in="${documentInstance.responses}" var="r"
					>
						<span class="property-value" aria-labelledby="responses-label"><g:link controller="questionResponse" action="show" id="${r.id}">
								${r?.encodeAsHTML()}
							</g:link></span>
					</g:each></li>
			</g:if>

		</ol>
		
		<g:form>
			<fieldset class="buttons">
				<g:hiddenField name="id" value="${documentInstance?.id}" />
				<g:link class="edit" action="edit" id="${documentInstance?.id}">
					<g:message code="default.button.edit.label" default="Edit" />
				</g:link>
				<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}"
					onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"
				/>
			</fieldset>
		</g:form>

	</div>
</body>
</html>
