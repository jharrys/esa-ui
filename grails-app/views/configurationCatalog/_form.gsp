<%@ page import="org.ihc.esa.ConfigurationCatalog" %>



<div class="fieldcontain ${hasErrors(bean: configurationCatalogInstance, field: 'parentItem', 'error')} required">
	<label for="parentItem">
		<g:message code="configurationCatalog.parentItem.label" default="Parent Item" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="parentItem" name="parentItem.id" from="${org.ihc.esa.Item.list()}" optionKey="id" required="" value="${configurationCatalogInstance?.parentItem?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: configurationCatalogInstance, field: 'parentItemVersion', 'error')} ">
	<label for="parentItemVersion">
		<g:message code="configurationCatalog.parentItemVersion.label" default="Parent Item Version" />
		
	</label>
	<g:select id="parentItemVersion" name="parentItemVersion.id" from="${org.ihc.esa.ItemVersion.list()}" optionKey="id" value="${configurationCatalogInstance?.parentItemVersion?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: configurationCatalogInstance, field: 'elementItem', 'error')} required">
	<label for="elementItem">
		<g:message code="configurationCatalog.elementItem.label" default="Element Item" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="elementItem" name="elementItem.id" from="${org.ihc.esa.Item.list()}" optionKey="id" required="" value="${configurationCatalogInstance?.elementItem?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: configurationCatalogInstance, field: 'elementItemVersion', 'error')} ">
	<label for="elementItemVersion">
		<g:message code="configurationCatalog.elementItemVersion.label" default="Element Item Version" />
		
	</label>
	<g:select id="elementItemVersion" name="elementItemVersion.id" from="${org.ihc.esa.ItemVersion.list()}" optionKey="id" value="${configurationCatalogInstance?.elementItemVersion?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: configurationCatalogInstance, field: 'createdBy', 'error')} ">
	<label for="createdBy">
		<g:message code="configurationCatalog.createdBy.label" default="Created By" />
		
	</label>
	<g:textField name="createdBy" value="${configurationCatalogInstance?.createdBy}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: configurationCatalogInstance, field: 'updatedBy', 'error')} ">
	<label for="updatedBy">
		<g:message code="configurationCatalog.updatedBy.label" default="Updated By" />
		
	</label>
	<g:textField name="updatedBy" value="${configurationCatalogInstance?.updatedBy}"/>
</div>

