<%@ page defaultCodec="html" %>
<div class="control-group ${invalid ? 'error' : ''}">
	<label class="control-label" for="${property}">${label}</label>
	<div class="controls">
		<%= widget %>
		<g:if test="${invalid}"><span class="help-inline">${errors.join('<br>')}</span></g:if>
	</div>
</div>
<script>
    $(function() {
        $('#${property}').on( "change", function() {
            if (this.value == 'CLOSED') {
	            var today = new Date()
	            var formattedDate = (today.getMonth() + 1) + "/" + today.getDate() + "/" + today.getFullYear()
	            $('#dateCompleted').val(formattedDate);
            }
            });
    });
</script>