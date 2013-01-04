<%@ page defaultCodec="html" %>

<g:logMsg level="debug">== rendering project/architects/_display ==</g:logMsg>
<g:logMsg level="debug">== bean: ${bean }; property: ${property }; value: ${value } ==</g:logMsg>

<% int index=1 %>
<g:logMsg level="debug">== start looping through architects ==</g:logMsg>
<g:each in="${bean.architects }" var="architect">
    <g:logMsg level="debug">== architect ${architect.id }</g:logMsg>
    ${(index > 1) ? ", " + architect.name.trim() : architect.name.trim()}
    <%++index %>
</g:each>

<g:logMsg level="debug">== done rendering project/architects/_display ==</g:logMsg>