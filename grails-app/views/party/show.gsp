
<%@ page import="org.ihc.esa.Party" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="general">
		<g:set var="entityName" value="${message(code: 'party.label', default: 'Party')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
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
					<h1><g:message code="default.show.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<dl>
				
					<g:if test="${partyInstance?.externalId}">
						<dt><g:message code="party.externalId.label" default="External Id" /></dt>
						
							<dd><g:fieldValue bean="${partyInstance}" field="externalId"/></dd>
						
					</g:if>
				
					<g:if test="${partyInstance?.type}">
						<dt><g:message code="party.type.label" default="Type" /></dt>
						
							<dd><g:fieldValue bean="${partyInstance}" field="type"/></dd>
						
					</g:if>
				
					<g:if test="${partyInstance?.name}">
						<dt><g:message code="party.name.label" default="Name" /></dt>
						
							<dd><g:fieldValue bean="${partyInstance}" field="name"/></dd>
						
					</g:if>
				
					<g:if test="${partyInstance?.emailAddress}">
						<dt><g:message code="party.emailAddress.label" default="Email Address" /></dt>
						
							<dd><g:fieldValue bean="${partyInstance}" field="emailAddress"/></dd>
						
					</g:if>
				
					<g:if test="${partyInstance?.mobilePhoneNumber}">
						<dt><g:message code="party.mobilePhoneNumber.label" default="Mobile Phone Number" /></dt>
						
							<dd><g:fieldValue bean="${partyInstance}" field="mobilePhoneNumber"/></dd>
						
					</g:if>
				
					<g:if test="${partyInstance?.workPhoneNumber}">
						<dt><g:message code="party.workPhoneNumber.label" default="Work Phone Number" /></dt>
						
							<dd><g:fieldValue bean="${partyInstance}" field="workPhoneNumber"/></dd>
						
					</g:if>
				
					<g:if test="${partyInstance?.homePhoneNumber}">
						<dt><g:message code="party.homePhoneNumber.label" default="Home Phone Number" /></dt>
						
							<dd><g:fieldValue bean="${partyInstance}" field="homePhoneNumber"/></dd>
						
					</g:if>
				
					<g:if test="${partyInstance?.webSiteUrl}">
						<dt><g:message code="party.webSiteUrl.label" default="Web Site Url" /></dt>
						
							<dd><g:fieldValue bean="${partyInstance}" field="webSiteUrl"/></dd>
						
					</g:if>
				
					<g:if test="${partyInstance?.dateCreated}">
						<dt><g:message code="party.dateCreated.label" default="Date Created" /></dt>
						
							<dd><g:formatDate format="MM-dd-yyyy" date="${partyInstance?.dateCreated}" /></dd>
						
					</g:if>
				
					<g:if test="${partyInstance?.createdBy}">
						<dt><g:message code="party.createdBy.label" default="Created By" /></dt>
						
							<dd><g:fieldValue bean="${partyInstance}" field="createdBy"/></dd>
						
					</g:if>
				
					<g:if test="${partyInstance?.lastUpdated}">
						<dt><g:message code="party.lastUpdated.label" default="Last Updated" /></dt>
						
							<dd><g:formatDate format="MM-dd-yyyy" date="${partyInstance?.lastUpdated}" /></dd>
						
					</g:if>
				
					<g:if test="${partyInstance?.updatedBy}">
						<dt><g:message code="party.updatedBy.label" default="Updated By" /></dt>
						
							<dd><g:fieldValue bean="${partyInstance}" field="updatedBy"/></dd>
						
					</g:if>
				
					<g:if test="${partyInstance?.addresses}">
						<dt><g:message code="party.addresses.label" default="Addresses" /></dt>
						
							<g:each in="${partyInstance.addresses}" var="a">
							<dd><g:link controller="address" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
					<g:if test="${partyInstance?.esaUsers}">
						<dt><g:message code="party.esaUsers.label" default="Esa Users" /></dt>
						
							<g:each in="${partyInstance.esaUsers}" var="e">
							<dd><g:link controller="user" action="edit" id="${e.id}">${e?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
					<g:if test="${partyInstance?.items}">
						<dt><g:message code="party.items.label" default="Items" /></dt>
						
							<g:each in="${partyInstance.items}" var="i">
							<dd><g:link controller="item" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
					<g:if test="${partyInstance?.partyRelationshipParty}">
						<dt><g:message code="party.partyRelationshipParty.label" default="Party Relationship Party" /></dt>
						
							<g:each in="${partyInstance.partyRelationshipParty}" var="p">
							<dd><g:link controller="partyRelationship" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
					<g:if test="${partyInstance?.partyRelationshipParty1}">
						<dt><g:message code="party.partyRelationshipParty1.label" default="Party Relationship Party1" /></dt>
						
							<g:each in="${partyInstance.partyRelationshipParty1}" var="p">
							<dd><g:link controller="partyRelationship" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${partyInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${partyInstance?.id}">
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
