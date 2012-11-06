<%@ page defaultCodec="html" %>
<div class="control-group ${invalid ? 'error' : ''}">
    <label class="control-label" for="${property}">${label}</label>
    <div class="controls">
        <%-- FIXME: The pick list should come from the database, but for now this will have to do --%>
        <%
		  map = ["": "-None Selected-", "Enterprise": "Enterprise", "Intermountain": "Intermountain", "SelectHealth": "SelectHealth", "Financial": "Financial", "Clinical": "Clinical",
		      "Regional": "Regional", "Department": "Department"]
		 %>
        <g:select name="${property }" from="${map }" value="${value }" optionKey="${{it.key } }" 
           optionValue="${{it.value } }" />
        <span class="help-inline">(e.g., Enterprise, Intermountain, SelectHealth, Financial, Clinical, Regional, Department)</span>
    </div>
</div>
