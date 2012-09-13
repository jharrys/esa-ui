<!doctype html>
<html>
<head>
<meta name="layout" content="bootstrap" />
<title>Enterprise Information System Architecture</title>
</head>
<body>
	<div class="row-fluid">

		<aside id="application-status" class="span3">

			<div class="well sidebar-nav">
				<h3>EISA</h3>
				<ul>
					<li>Standards Database</li>
					<li><g:link class="manage" controller="exception" action="list">Exception Requests</g:link></li>
				</ul>
				<h3>Administrative</h3>
				<ul>
					<li><g:link class="manage" controller="item" action="list">Manage Items</g:link></li>
					<li><g:link class="manage" controller="itemVersion" action="list">Manage Item Versions</g:link></li>
					<li><g:link class="manage" controller="lookupList" action="list">Manage Lookup Lists</g:link></li>
					<li><g:link class="manage" controller="document" action="list">Manage Documents</g:link></li>
					<li><a href="<g:createLink controller='user' action='search'/>" target="_blank">Manage Users</a></li>
					<li><g:link class="manage" controller="esaRole" action="list">Manage Roles</g:link></li>
				</ul>
			</div>

		</aside>

		<section id="main" class="span9">

			<div class="hero-unit">
				<h2>Enterprise Information Systems Architecture</h2>
				<p>The EISA web site is a central knowledge and content store for Information Technology architecture. The architectural content and processes are
					specific to Intermountain Healthcare and SelectHealth. Information Technology at Intermountain Healthcare has a long-standing tradition for developing
					world class systems. We want to continue this tradition, but with shorter release cycles. This means we will not have all the features we want, but we will
					deliver one or two features every couple of weeks. So please visit back often.</p>
			</div>

			<div class="row-fluid">

				<div class="span4">
					<h4>Features available today:</h4>
					<ul id="nav nav-list">
						<li>Query for existing software standards</li>
						<li>Query for existing hardware standards (including clinical hardware)</li>
						<li>Browse or query for Enterprise Architecture Review Board meetings and notes</li>
						<li>Read and learn about IT architectural processes at Intermountain Healthcare and SelectHealth</li>
					</ul>
				</div>

				<div class="span4">
					<h4>Features we want to release soon:</h4>
					<ul>
						<li>Automate Exception work flow process</li>
						<li>Find status of an Exception</li>
						<li>Find status of a Standard</li>
						<li>Submit standard recommendations to the EARB</li>
						<li>Innovation Lab</li>
					</ul>
					<p>Idea: add 1st year anniversary of ESA, Enterprise Solutions Architecture (do some marketing)</p>
				</div>

				<div class="span4">
					<ul class="nav nav-list">
						<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
							<li><g:link controller="${c.logicalPropertyName}">
									${c.naturalName}
								</g:link></li>
						</g:each>
					</ul>
				</div>

			</div>
		</section>

	</div>

</body>
</html>
