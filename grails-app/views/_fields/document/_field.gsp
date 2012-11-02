<%@ page import="org.ihc.esa.Document"%>
<%@ page defaultCodec="html" %>
<div class="control-group ${invalid ? 'error' : ''}">
    <label class="control-label" for="${property}">${label}</label>
    <div class="controls">
        <g:select name="${property }" from="${Document.list().sort() }" optionKey="id" optionValue="title" />
        <g:if test="${invalid}"><span class="help-inline">${errors.join('<br>')}</span></g:if>
    </div>
</div>