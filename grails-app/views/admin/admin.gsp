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
					<li><g:link action="lookupLists">Manage Lookup Lists</g:link></li>
					<li><g:link action="lookupElements">Manage Lookup Elements</g:link></li>
					<li><g:link action="documents">Manage Documents</g:link></li>
					<li><g:link action="items">Manage Items</g:link></li>
					<li><g:link action="parties">Manage Vendors</g:link></li>
					<li><g:link action="addresses">Manage Addresses</g:link></li>
					<li><g:link action="exceptions">Manage Exception Documents</g:link></li>
					<li><a href="<g:createLink controller='user' action='search'/>" target="_blank">Manage Users</a></li>
				</ul>
			</div>

		</aside>

		<section id="main" class="span9">

			<div class="row-fluid">

				<div class="span4">
					<h4>List 1</h4>
					<ul id="nav nav-list">
						<li>sub-list 1</li>
						<li>sub-list 2</li>
						<li>sub-list 3</li>
						<li>sub-list 4</li>
					</ul>
				</div>

				<div class="span4">
					<h4>List 2</h4>
					<ul>
						<li>sub-list 1</li>
                        <li>sub-list 2</li>
                        <li>sub-list 3</li>
                        <li>sub-list 4</li>
					</ul>
				</div>

			</div>
			
			<div class="row-fluid">

                <div class="span4">
                    <h4>List 3</h4>
                    <ul id="nav nav-list">
                        <li>sub-list 1</li>
                        <li>sub-list 2</li>
                        <li>sub-list 3</li>
                        <li>sub-list 4</li>
                    </ul>
                </div>

                <div class="span4">
                    <h4>List 4</h4>
                    <ul>
                        <li>sub-list 1</li>
                        <li>sub-list 2</li>
                        <li>sub-list 3</li>
                        <li>sub-list 4</li>
                    </ul>
                </div>

            </div>
			
		</section>

	</div>

</body>
</html>
