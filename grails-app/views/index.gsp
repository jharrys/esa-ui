<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Enterprise Information System Architecture</title>
		<style type="text/css" media="screen">
			#status {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				width: 12em;
				float: left;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
			}

			.ie6 #status {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#status ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}
            
			#status li {
				line-height: 1.3;
			}

			#status h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}

			#page-body {
				margin: 2em 1em 1.25em 18em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
<%--				line-height: 1.5; TODO Remove --%>
				margin: 0.25em 0;
			}
			
			ul {
				padding-left: 1.5em;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}
		</style>
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="status" role="complementary">
			<h1>EISA</h1>
			<ul>
				<li>Query Standards Database</li>
				<li>Exception Request Status</li>
				<!-- 
				<li>Groovy version: ${org.codehaus.groovy.runtime.InvokerHelper.getVersion()}</li>
				<li>JVM version: ${System.getProperty('java.version')}</li>
				<li>Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</li>
				<li>Controllers: ${grailsApplication.controllerClasses.size()}</li>
				<li>Domains: ${grailsApplication.domainClasses.size()}</li>
				<li>Services: ${grailsApplication.serviceClasses.size()}</li>
				<li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>
				-->
			</ul>
			<h1>Installed Plugins</h1>
			<ul>
				<g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
					<li>${plugin.name} - ${plugin.version}</li>
				</g:each>
			</ul>
		</div>
		<div id="page-body" role="main">
			<h1>Enterprise Information Systems Architecture</h1>
			<p>
				The EISA web site is a central knowledge and content store for Information Technology architecture. The architectural
				content and processes are specific to Intermountain Healthcare and SelectHealth. IT at Intermountain Healthcare has a long-standing
				tradition for developing world class systems. We want to continue this tradition, but with shorter release cycles. This means we will
				not have all the features we want, but we will deliver one or two features every couple of weeks. So please visit back often.
			</p>
			<br/>
			<p>
				<b>Features available today:</b>
				<ul id="indented-list">
					<li>Query for existing software standards</li>
					<li>Query for existing hardware standards (including clinical hardware)</li>
					<li>Browse or query for Enterprise Architecture Review Board meetings and notes</li>
					<li>Read and learn about IT architectural processes at Intermountain Healthcare and SelectHealth</li>
				</ul>
			</p>
			<br/>
			<p>
				<b>Features we want to release soon:</b>
				<ul>
					<li>Automate Exception work flow process</li>
					<li>Find status of an Exception</li>
					<li>Find status of a Standard</li>
					<li>Submit standard recommendations to the EARB</li>
					<li>Innovation Lab</li>
				</ul>
			</p>
			<br/>
			<p>
				Idea: add 1st year anniversary of ESA, Enterprise Solutions Architecture (do some marketing)
			</p>
			<br>
			<%--
			<div id="controller-list" role="navigation">
				<h2>Available Controllers:</h2>
				<ul>
					<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
						<li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
					</g:each>
				</ul>
			</div>
			--%>
		</div>
	</body>
</html>
