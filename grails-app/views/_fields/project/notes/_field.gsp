<%@ page import="org.ihc.esa.Party"%>
<%@ page defaultCodec="html" %>

<g:logMsg level="debug">== rendering project/notes/_field ==</g:logMsg>
<g:logMsg level="debug">== bean: ${bean }; property: ${property }; value: ${value } ==</g:logMsg>

<g:each in="${bean.notes }" var="note">
	<div class="control-group ${invalid ? 'error' : ''}">
	    <label class="control-label" for="note_${note.id}">Note from <g:formatDate date="${note.lastUpdated }" format="MMM dd, yyyy" /></label>
	    <div class="controls">
	        <g:textArea name="note_${note.id }">${note.text.trim() }</g:textArea>
	        <g:if test="${invalid}"><span class="help-inline">${errors.join('<br>')}</span></g:if>
	    </div>
	</div>
</g:each>

<div class="control-group ${invalid ? 'error' : ''}">
    <label class="control-label" for="newNote">Add Note</label>
    <div class="controls">
        <g:textArea name="newNote"></g:textArea>
    </div>
</div>

<g:logMsg level="debug">== done rendering project/notes/_field ==</g:logMsg>