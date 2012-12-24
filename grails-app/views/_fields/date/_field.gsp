<%@ page import="java.text.SimpleDateFormat"%>
<%@ page defaultCodec="html" %>
<%
    SimpleDateFormat dateFormatter = new SimpleDateFormat('MM/dd/yyyy')
	if (value) value = dateFormatter.format(value)
%>
<div class="control-group ${invalid ? 'error' : ''}">
    <label class="control-label" for="${property}">${label}</label>
    <div class="controls">
	    <input id="${property }" name="${property }" size="16" type="text" value="${value }">
	    <script>
	       $(function() {
	           $('#${property }').datepicker({format: 'mm/dd/yyyy'});
	           $('.datepicker').css('z-index', 9999);  // the 'dropdown-menu' class sets z-index lower than modal box, so datepicker was hidden
	           });
	    </script>
    </div>
</div>
