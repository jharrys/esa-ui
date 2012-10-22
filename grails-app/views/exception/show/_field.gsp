<%@page import="java.util.Hashtable.ValueCollection"%>
<%@page import="org.ihc.esa.LookupElement"%>
<%@ page defaultCodec="html"%>

<g:if test="${bean }">

	<%
	   if (property.equals("form")) { 
		   value = bean.form.name.toLowerCase().capitalize() 
	   }
	 %>

	<dt>
	    <g:message code="exception.${property }.label" default="${label }" />
	</dt>
	
	<dd>
	   <g:if test="${property.equals("responses") }" >
	       <dl>
	           <%
	               List list = bean.responses.sort { a,b ->
	                   a.id <=> b.id
	               }
	            %>
	           <g:each in="${list }" var="response">
	               <g:if test="${!response.formField.dataType.equalsIgnoreCase("Title") }" >
		               <dt>${response.formField.question } </dt>
		               <g:if test="${response.formField.dataType.equalsIgnoreCase("DATE_VALUE") }" >
		                  <dd>
		                      <g:formatDate format="MM-dd-yyyy" date="${response?.dateValue}" />
		                  </dd>
		               </g:if>
		               
	                   <g:elseif test="${response.formField.lookupList }" >
			               <dd>
			                 <%
							     String displayString = ""
								 response.value.split(",").each { r ->
									 String val = LookupElement.get(Long.valueOf(r)).display
									 displayString = displayString.isEmpty() ? val : (displayString + "<br /> " + val)
								 }
							  %>
							  ${displayString }
			               </dd>
	                   </g:elseif>
	                   
	                   <g:else>
			               <dd>${response.value }</dd>
		               </g:else>
	               </g:if>
	           </g:each>
	       </dl>
	   </g:if>
	   
	   <g:else>
	       ${value }
	   </g:else>
	</dd>

</g:if>