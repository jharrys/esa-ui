<!doctype html>
<html>
<head>
<meta name="layout" content="bootstrap" />
<title>EISA Site Administration</title>
</head>
<body>
	<div class="row-fluid">

		<aside id="application-status" class="span3">

			<div class="well sidebar-nav">
				<h3>Screens</h3>
				<ul class="nav nav-pills nav-stacked">
					<li><g:link action="updateIndices">Update All Lucene Indexes</g:link></li>
					<li><a href="<g:createLink controller='user' action='search'/>" target="_blank">Manage Users</a></li>
					<li><g:link action="parties">Manage Vendors, Parties and User metadata</g:link></li>
					<li><g:link action="exceptions">Manage Exception Documents</g:link></li>
					<li class="disabled"><g:link action="lookupLists">Manage Lookup Lists</g:link></li>
					<li class="disabled"><g:link action="lookupElements">Manage Lookup Elements</g:link></li>
					<li class="disabled"><g:link action="documents">Manage Documents</g:link></li>
					<li class="disabled"><g:link action="items">Manage Items</g:link></li>
					<li class="disabled"><g:link action="addresses">Manage Addresses</g:link></li>
				</ul>
			</div>

		</aside>

		<section id="main" class="span9">

			<div class="row-fluid">

				<div class="span8">
				    <div id="message">
						<g:if test="${flash.message}">
		                    <bootstrap:alert class="alert-info">
		                        ${flash.message}
		                    </bootstrap:alert>
		                </g:if>
	                </div>

	                <div id="help">

	                </div>
				</div>

			</div> <!-- end row-fluid -->

		</section>

	</div>

</body>
</html>
