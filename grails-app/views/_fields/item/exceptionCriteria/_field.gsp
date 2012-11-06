<%@ page defaultCodec="html" %>
<div class="control-group ${invalid ? 'error' : ''}">
    <label class="control-label" for="${property}">${label}</label>
    <div class="controls">
        <g:textArea class="input-xlarge" type="text" name="${property }" value="${value }" rows="3" />
    </div>
</div>
