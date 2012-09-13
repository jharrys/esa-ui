<%@ page import="org.ihc.esa.EsaRole" %>



<div class="fieldcontain ${hasErrors(bean: esaRoleInstance, field: 'authority', 'error')} required">
	<label for="authority">
		<g:message code="esaRole.authority.label" default="Authority" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="authority" required="" value="${esaRoleInstance?.authority}"/>
</div>

