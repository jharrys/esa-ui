<%@page import="org.codehaus.groovy.grails.commons.ApplicationHolder"%>
<%@page import="org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes"%>
<%
       def versionService = grailsApplication.mainContext.getBean("versionService")
%>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container-fluid">

			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
			</a>

			<a class="brand" href="${createLink(uri: '/')}">Enterprise Architecture</a>

			<div class="nav-collapse">
				<ul class="nav">
					<li <%= request.forwardURI == "${createLink(uri: '/')}" ? ' class="active"' : '' %>><a href="${createLink(uri: '/')}">Home</a></li>
					<li <%= request.forwardURI == "${createLink(uri: '/project/index')}" ? ' class="active"' : '' %>><g:link controller="project">Projects</g:link></li>
					<li <%= request.forwardURI == "${createLink(uri: '/standards/index')}" ? ' class="active"' : '' %>><g:link controller="standards">Standards</g:link></li>
					<li <%= request.forwardURI == "${createLink(uri: '/exception/index')}" ? ' class="active"' : '' %>><g:link controller="exception">Exceptions</g:link></li>
					<li <%= request.forwardURI == "${createLink(uri: '/earb')}" ? ' class="disabled"' : 'class="disabled"' %>><g:link controller="earb">EARB</g:link></li>
					<li>
						<%
							def aboutString = "Application: " + versionService.getApplicationVersion()
							aboutString = aboutString + "<br>Database: " + versionService.getDatabaseVersion()
						%>
						<a href="#" id="version" rel="popover" data-content="${aboutString }" data-original-title="Version">About</a>
						<script>
							$(function() {
							    $('#version').popover({trigger: 'hover', placement: 'bottom', html: true})
							});
						</script>
					</li>
				</ul>
				<ul class="nav pull-right">

				    <li>
						<sec:ifLoggedIn>
				    		<g:link controller='logout'>Logout (<sec:username/>)</g:link>
				        </sec:ifLoggedIn>

						<sec:ifNotLoggedIn>
				            <g:link controller='login'>Login</g:link>
						</sec:ifNotLoggedIn>

						<sec:ifSwitched>
							<a href='${request.contextPath}/j_spring_security_exit_user'>
				                Resume as <sec:switchedUserOriginalUsername/>
							</a>
						</sec:ifSwitched>
				    </li>

				</ul>
			</div>
		</div>
	</div>
</div>