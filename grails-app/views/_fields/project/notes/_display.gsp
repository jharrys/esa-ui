<%@ page defaultCodec="html" %>

<g:logMsg level="debug">== rendering project/notes/_display ==</g:logMsg>
<g:logMsg level="debug">== bean: ${bean }; property: ${property }; value: ${value } ==</g:logMsg>

<g:logMsg level="debug">== start looping through notes ==</g:logMsg>
<g:each in="${bean.notes }" var="note">
    <g:logMsg level="debug">== note ${note.id }</g:logMsg>
    ${note.text.trim()}
</g:each>

<g:logMsg level="debug">== done rendering project/notes/_display ==</g:logMsg>