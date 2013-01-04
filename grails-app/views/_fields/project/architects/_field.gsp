<%@ page import="org.ihc.esa.Party"%>
<%@ page defaultCodec="html" %>

<g:logMsg level="debug">== rendering project/architects/_field ==</g:logMsg>
<g:logMsg level="debug">== bean: ${bean }; property: ${property }; value: ${value } ==</g:logMsg>

<div class="control-group ${invalid ? 'error' : ''}">
    <label class="control-label" for="${property}">${label}</label>
    <div class="controls">
        <g:select name="${property }" multiple="true" from="${Party.listArchitects.list(sort: 'name') }" optionKey="id" optionValue="name" noSelection="['':'-None Selected-']"  value="${value?.id }"/>
        <g:if test="${invalid}"><span class="help-inline">${errors.join('<br>')}</span></g:if>
    </div>
</div>

<g:logMsg level="debug">== done rendering project/architects/_field ==</g:logMsg>