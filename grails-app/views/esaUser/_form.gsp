<%@ page import="org.ihc.esa.EsaUser" %>



<div class="fieldcontain ${hasErrors(bean: esaUserInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="esaUser.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${esaUserInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: esaUserInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="esaUser.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:passwordField name="password"/>
</div>

<div class="fieldcontain ${hasErrors(bean: esaUserInstance, field: 'accountExpired', 'error')} ">
	<label for="accountExpired">
		<g:message code="esaUser.accountExpired.label" default="Account Expired" />
		
	</label>
	<g:checkBox name="accountExpired" value="${esaUserInstance?.accountExpired}" />
</div>

<div class="fieldcontain ${hasErrors(bean: esaUserInstance, field: 'accountLocked', 'error')} ">
	<label for="accountLocked">
		<g:message code="esaUser.accountLocked.label" default="Account Locked" />
		
	</label>
	<g:checkBox name="accountLocked" value="${esaUserInstance?.accountLocked}" />
</div>

<div class="fieldcontain ${hasErrors(bean: esaUserInstance, field: 'enabled', 'error')} ">
	<label for="enabled">
		<g:message code="esaUser.enabled.label" default="Enabled" />
		
	</label>
	<g:checkBox name="enabled" value="${esaUserInstance?.enabled}" />
</div>

<div class="fieldcontain ${hasErrors(bean: esaUserInstance, field: 'passwordExpired', 'error')} ">
	<label for="passwordExpired">
		<g:message code="esaUser.passwordExpired.label" default="Password Expired" />
		
	</label>
	<g:checkBox name="passwordExpired" value="${esaUserInstance?.passwordExpired}" />
</div>

<div class="fieldcontain ${hasErrors(bean: esaUserInstance, field: 'authorities', 'error')}">
    <label for="authorities">
        <g:message code="esaUser.authorities.label" default="Authority" />
    </label>
    <g:select name="authorities" from="esaUserInstance.getAuthorities()" optionKey="id" optionValue="authority" multiple="true" />
</div>

