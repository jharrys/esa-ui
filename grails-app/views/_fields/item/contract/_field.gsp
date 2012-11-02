<%@ page import="org.ihc.esa.Contract"%>
<%@ page defaultCodec="html" %>
<div class="control-group ${invalid ? 'error' : ''}">
    <label class="control-label" for="${property}">${label}</label>
    <div class="controls">
        <g:select disabled="disabled" name="${property }" from="${Contract.list(sort: contractNumber) }" optionKey="id" optionValue="contractNumber" />
        <g:if test="${invalid}"><span class="help-inline">${errors.join('<br>')}</span></g:if>
    </div>
</div>