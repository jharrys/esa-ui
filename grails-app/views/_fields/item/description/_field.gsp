<%@ page defaultCodec="html" %>
<div class="control-group ${invalid ? 'error' : ''}">
    <label class="control-label" for="${property}">${label}</label>
    <div class="controls">
        <g:textArea type="text" name="${property }" value="${value }" class="input-xlarge" rows="3" required="required" />
    </div>
</div>
