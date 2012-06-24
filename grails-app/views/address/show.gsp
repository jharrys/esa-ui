
<%@ page import="org.ihc.esa.domain.Address" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'address.label', default: 'Address')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-address" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-address" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list address">
			
				<g:if test="${addressInstance?.address_line1}">
				<li class="fieldcontain">
					<span id="address_line1-label" class="property-label"><g:message code="address.address_line1.label" default="Addressline1" /></span>
					
						<span class="property-value" aria-labelledby="address_line1-label"><g:fieldValue bean="${addressInstance}" field="address_line1"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${addressInstance?.address_line2}">
				<li class="fieldcontain">
					<span id="address_line2-label" class="property-label"><g:message code="address.address_line2.label" default="Addressline2" /></span>
					
						<span class="property-value" aria-labelledby="address_line2-label"><g:fieldValue bean="${addressInstance}" field="address_line2"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${addressInstance?.address_line3}">
				<li class="fieldcontain">
					<span id="address_line3-label" class="property-label"><g:message code="address.address_line3.label" default="Addressline3" /></span>
					
						<span class="property-value" aria-labelledby="address_line3-label"><g:fieldValue bean="${addressInstance}" field="address_line3"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${addressInstance?.address_line4}">
				<li class="fieldcontain">
					<span id="address_line4-label" class="property-label"><g:message code="address.address_line4.label" default="Addressline4" /></span>
					
						<span class="property-value" aria-labelledby="address_line4-label"><g:fieldValue bean="${addressInstance}" field="address_line4"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${addressInstance?.city}">
				<li class="fieldcontain">
					<span id="city-label" class="property-label"><g:message code="address.city.label" default="City" /></span>
					
						<span class="property-value" aria-labelledby="city-label"><g:fieldValue bean="${addressInstance}" field="city"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${addressInstance?.state}">
				<li class="fieldcontain">
					<span id="state-label" class="property-label"><g:message code="address.state.label" default="State" /></span>
					
						<span class="property-value" aria-labelledby="state-label"><g:fieldValue bean="${addressInstance}" field="state"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${addressInstance?.zip}">
				<li class="fieldcontain">
					<span id="zip-label" class="property-label"><g:message code="address.zip.label" default="Zip" /></span>
					
						<span class="property-value" aria-labelledby="zip-label"><g:fieldValue bean="${addressInstance}" field="zip"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${addressInstance?.created_by}">
				<li class="fieldcontain">
					<span id="created_by-label" class="property-label"><g:message code="address.created_by.label" default="Createdby" /></span>
					
						<span class="property-value" aria-labelledby="created_by-label"><g:fieldValue bean="${addressInstance}" field="created_by"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${addressInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="address.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${addressInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${addressInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="address.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${addressInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${addressInstance?.parties}">
				<li class="fieldcontain">
					<span id="parties-label" class="property-label"><g:message code="address.parties.label" default="Parties" /></span>
					
						<g:each in="${addressInstance.parties}" var="p">
						<span class="property-value" aria-labelledby="parties-label"><g:link controller="party" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${addressInstance?.updated_by}">
				<li class="fieldcontain">
					<span id="updated_by-label" class="property-label"><g:message code="address.updated_by.label" default="Updatedby" /></span>
					
						<span class="property-value" aria-labelledby="updated_by-label"><g:fieldValue bean="${addressInstance}" field="updated_by"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${addressInstance?.id}" />
					<g:link class="edit" action="edit" id="${addressInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
