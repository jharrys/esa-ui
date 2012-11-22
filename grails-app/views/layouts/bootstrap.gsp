<!doctype html>
<html lang="en">
    <head>
		<!-- HTML5 shim, for IE6-8 support of HTML elements -->
		<!--[if lt IE 9]>
					<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->

        <!-- meta data -->
		<meta charset="utf-8">
		<meta name="description" content="Enterprise Information System Architecture">
		<meta name="author" content="John Harris">
		<meta name="viewport" content="initial-scale = 1.0">

        <!-- use gsp title, if not then use default -->
		<title><g:layoutTitle default="Enterprise Architecture" /></title>


		<!-- required resources -->
		<r:require module="application" />

		<!-- add gsp header stuff here -->
		<g:layoutHead />

		<!-- layoutResources needs to come after everything else -->
		<r:layoutResources />

		<!--
            padding as required by twitter bootstrap for .navbar-fixed-top; needs to come after the core Bootstrap CSS and
            before the optional responsive CSS
        -->
		<style>
		    body { padding-top: 50px; }
		    @media screen and (max-width: 768px) {
		        body { padding-top: 0px; }
		    }
		</style>
    </head>

	<body>

		<g:render template="/layouts/header" />

		<div class="container-fluid">

			<div class="row-fluid">

			  <div class="span12">

					<g:layoutBody />

					<hr>

					<g:render template="/layouts/footer" />

				</div>

			</div>

		</div>

		<r:layoutResources />

	</body>
</html>