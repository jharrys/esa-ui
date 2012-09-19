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
				<ul class="nav nav-pills nav-stacked">
					<li><g:link controller="standards" action="index">Browse Standards</g:link></li>
					<li><g:link controller="exception" action="list">Exception Requests</g:link></li>
					<li><g:link controller="standards" action="definitions">Definitions</g:link></li>
				</ul>
				<h3>Administrative</h3>
				<ul class="nav nav-pills nav-stacked">
					<li><g:link controller="item" action="list">Manage Items</g:link></li>
					<li><g:link controller="itemVersion" action="list">Manage Item Versions</g:link></li>
					<li><g:link controller="lookupList" action="list">Manage Lookup Lists</g:link></li>
					<li><g:link controller="document" action="list">Manage Documents</g:link></li>
					<li><a href="<g:createLink controller='user' action='search'/>" target="_blank">Manage Users</a></li>
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
				</div>

			</div>
		</section>

	</div>

</body>
</html>
