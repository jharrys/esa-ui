<%@ page import="org.ihc.esa.EsaUser"%>
<%@ page defaultCodec="html" %>
<div class="control-group ${invalid ? 'error' : ''}">
    <label class="control-label" for="${property}">${label}</label>
    <div class="controls">
        <g:select name="${property }" from="${EsaUser.findAll(sort: 'username') }" optionKey="id" optionValue="username" noSelection="['':'-None Selected-']"  value="${value?.id }" multiple="false"/>
        <g:if test="${invalid}"><span class="help-inline">${errors.join('<br>')}</span></g:if>
    </div>
</div>