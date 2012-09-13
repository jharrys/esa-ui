<%@ page import="org.ihc.esa.*"%>
<!doctype html>
<html>
<head>
<link href="/esa-ui/static/plugins/blueprint-1.0.2/css/blueprint/screen.css" type="text/css" rel="stylesheet" media="screen, projection" />
</head>
<body>
<%--	<g:each in="${rootList }" var="element">--%>
<%--		root: ${element }<br>--%>
<%--	</g:each>--%>
	<%
 def subs = ConfigurationCatalog.findAllByParentItemInList(rootList, [sort: "id", order: "asc"])
  %>
	<g:each in="${subs }" var="sub">
		<h2>pk: ${sub.id } |element: ${sub.elementItem.id } |name: ${sub.elementItem.name } |parent: ${sub.parentItem.name }</h2>
		children:<br> ${ConfigurationCatalog.findAllByParentItem(sub.elementItem).each { println it.elementItem.name; println "<br>" } }<br>
		
	</g:each>
</body>
</html>