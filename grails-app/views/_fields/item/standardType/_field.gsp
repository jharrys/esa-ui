<%@ page defaultCodec="html" %>
<%@ page import="org.ihc.esa.Item" %>
<div class="control-group ${invalid ? 'error' : ''}">
    <label class="control-label" for="${property}">${label}</label>
    <div class="controls">
        <g:select name="${property }" from="${Item.StandardType }" value="${value?.key }" noSelection="${['null':'-Select One-'] }" optionKey="key" />
        <span class="help-inline">(e.g., Enterprise, Intermountain, SelectHealth, Financial, Clinical, Regional, Department)</span>
    </div>
</div>
