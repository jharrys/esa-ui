<%@page import="org.codehaus.groovy.grails.commons.ApplicationHolder"%>
<%@ page import="org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title><g:layoutTitle default="EISA" /></title>
<meta name="description" content="Enterprise Information System Architecture">
<meta name="author" content="John Harris">

<meta name="viewport" content="initial-scale = 1.0">

<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
<!--[if lt IE 9]>
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->

<link rel="stylesheet" href="${resource(dir: 'css', file: 'datepicker.css')}" type="text/css">

<r:require modules="scaffolding" />

<!-- Le fav and touch icons -->
<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">

<g:layoutHead />
<r:layoutResources />
</head>

<body>
    <!--
        bootstrap twitter doesn't include one, so I'm using this one http://www.eyecon.ro/bootstrap-datepicker/ 
        FIXME: place this resource in the appropriate configuration files 
    -->
	<g:javascript src='bootstrap-datepicker.js' />
	
	<%
	       def versionService = grailsApplication.mainContext.getBean("versionService")
	 %>

	<nav class="navbar navbar-fixed-top navbar-inverse">
		<div class="navbar-inner">
			<div class="container-fluid">

				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"
				></span>
				</a>

				<a class="brand" href="${createLink(uri: '/')}">Enterprise Architecture</a>

				<div class="nav-collapse">
					<ul class="nav">
						<li <%= request.forwardURI == "${createLink(uri: '/')}" ? ' class="active"' : '' %>><a href="${createLink(uri: '/')}">Home</a></li>
						<li <%= request.forwardURI == "${createLink(uri: '/standards/index')}" ? ' class="active"' : '' %>><g:link controller="standards">Standards</g:link></li>
						<li <%= request.forwardURI == "${createLink(uri: '/exception/index')}" ? ' class="active"' : '' %>><g:link controller="exception">Exceptions</g:link></li>
						<li <%= request.forwardURI == "${createLink(uri: '/earb')}" ? ' class="disabled"' : 'class="disabled"' %>><g:link controller="earb">EARB</g:link></li>
						<li <%= request.forwardURI == "${createLink(uri: '/admin/admin')}" ? ' class="active"' : '' %>><g:link controller="admin">Administration</g:link></li>
						<li>
						  <%
						      def aboutString = "Application: " + versionService.getApplicationVersion()
						      aboutString = aboutString + "<br /> Database: " + versionService.getDatabaseVersion()
						   %>
						  <a href="#" id="version" rel="popover" data-content="${aboutString }" data-original-title="Version">About</a>
						  <script>
						    $(function() {
						    	  $('#version').popover({trigger: 'hover', placement: 'bottom'})
						    });
						  </script>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<g:layoutBody />

		<hr>

		<footer>
			<p>&copy; Intermountain Healthcare 2012. All Rights Reserved.</p>
			<p><small>build: <g:render template="/git" /></small></p>
		</footer>
	</div>

	<r:layoutResources />

</body>
</html>