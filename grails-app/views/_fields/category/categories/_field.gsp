<%@ page defaultCodec="html" %>
<g:if test="${bean.categories }" >
    <%
	   def sorted_categories = bean.categories.sort(false)
	   sorted_categories = sorted_categories.minus(bean)
	 %>
	<div class="control-group ${invalid ? 'error' : ''}">
		<label class="control-label" for="${property}">${label}</label>
		<div class="controls">
			<g:select id="categories" name="categories" from="${sorted_categories}" multiple="true" optionKey="id" optionValue="name" disabled="true"
	            class="many-to-one" />
		</div>
	</div>
</g:if>