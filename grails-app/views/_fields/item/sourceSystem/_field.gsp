<%@ page defaultCodec="html" %>
<div class="control-group ${invalid ? 'error' : ''}">
    <label class="control-label" for="${property}">${label}</label>
    <div class="controls">
        <g:field type="text" name="${property }" value="${value }" class="input-xxlarge" required="required" />
	    <span class="help-inline"><small>Source from which this information was acquired (e.g., excel spreadsheet, SCO)</small></span>
    </div>
</div>
