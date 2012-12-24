<%@ page import="org.ihc.esa.Project" %>



<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="project.name.label" default="Name" />
		
	</label>
	<g:textArea name="name" cols="40" rows="5" maxlength="256" value="${projectInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'type', 'error')} ">
	<label for="type">
		<g:message code="project.type.label" default="Type" />
		
	</label>
	<g:select name="type" from="${org.ihc.esa.Project$ProjectType?.values()}" keys="${org.ihc.esa.Project$ProjectType.values()*.name()}" value="${projectInstance?.type?.name()}" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="project.status.label" default="Status" />
		
	</label>
	<g:select name="status" from="${org.ihc.esa.Project$ProjectStatus?.values()}" keys="${org.ihc.esa.Project$ProjectStatus.values()*.name()}" value="${projectInstance?.status?.name()}" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'externalProjectNumber', 'error')} ">
	<label for="externalProjectNumber">
		<g:message code="project.externalProjectNumber.label" default="External Project Number" />
		
	</label>
	<g:textField name="externalProjectNumber" maxlength="64" value="${projectInstance?.externalProjectNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'architect', 'error')} ">
	<label for="architect">
		<g:message code="project.architect.label" default="Architect" />
		
	</label>
	<g:select id="architect" name="architect.id" from="${org.ihc.esa.Party.list()}" optionKey="id" value="${projectInstance?.architect?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'projectManager', 'error')} ">
	<label for="projectManager">
		<g:message code="project.projectManager.label" default="Project Manager" />
		
	</label>
	<g:select id="projectManager" name="projectManager.id" from="${org.ihc.esa.Party.list()}" optionKey="id" value="${projectInstance?.projectManager?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'dateStart', 'error')} ">
	<label for="dateStart">
		<g:message code="project.dateStart.label" default="Date Start" />
		
	</label>
	<g:datePicker name="dateStart" format="yyyy-MM-dd" precision="day"  value="${projectInstance?.dateStart}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'dateCompleted', 'error')} ">
	<label for="dateCompleted">
		<g:message code="project.dateCompleted.label" default="Date Completed" />
		
	</label>
	<g:datePicker name="dateCompleted" format="yyyy-MM-dd" precision="day"  value="${projectInstance?.dateCompleted}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'createdBy', 'error')} ">
	<label for="createdBy">
		<g:message code="project.createdBy.label" default="Created By" />
		
	</label>
	<g:textField name="createdBy" maxlength="40" value="${projectInstance?.createdBy}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'updatedBy', 'error')} ">
	<label for="updatedBy">
		<g:message code="project.updatedBy.label" default="Updated By" />
		
	</label>
	<g:textField name="updatedBy" maxlength="40" value="${projectInstance?.updatedBy}"/>
</div>

