<%@ page defaultCodec="html" %>
<div class="control-group ${invalid ? 'error' : ''}">
    <label class="control-label" for="${property}">${label}</label>
    <div class="controls">
        <g:field type="text" name="${property }" type="hidden" value="${value }" />
    </div>
</div>