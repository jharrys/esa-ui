<%@ page import="org.ihc.esa.Category" %>



<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'parentCategory', 'error')} required">
	<label for="parentCategory">
		<g:message code="category.parentCategory.label" default="Parent Category" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="parentCategory" name="parentCategory.id" from="${org.ihc.esa.Category.list()}" optionKey="id" required="" value="${categoryInstance?.parentCategory?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'parentCategoryPath', 'error')} required">
	<label for="parentCategoryPath">
		<g:message code="category.parentCategoryPath.label" default="Parent Category Path" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="parentCategoryPath" cols="40" rows="5" maxlength="4000" required="" value="${categoryInstance?.parentCategoryPath}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="category.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="128" required="" value="${categoryInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'createdBy', 'error')} ">
	<label for="createdBy">
		<g:message code="category.createdBy.label" default="Created By" />
		
	</label>
	<g:textField name="createdBy" maxlength="40" value="${categoryInstance?.createdBy}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'updatedBy', 'error')} ">
	<label for="updatedBy">
		<g:message code="category.updatedBy.label" default="Updated By" />
		
	</label>
	<g:textField name="updatedBy" maxlength="40" value="${categoryInstance?.updatedBy}"/>
</div>

