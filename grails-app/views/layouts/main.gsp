<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en">
<!--<![endif]-->
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title><g:layoutTitle default="EISA" /></title>

<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">

<r:require modules="bootstrap" />

<g:layoutHead />
<r:layoutResources />

</head>
<body>

	<div class="container">

		<div id="header" class="header" role="banner">
			<a href="http://eisa-repository.co.ihc.com"><img src="${resource(dir: 'images', file: 'eisa_logo.png')}" alt="EISA" /></a>
		</div>

		<div id="content" class="content">
			<g:layoutBody />
		</div>

		<div id="footer" class="footer" role="contentinfo">
			&copy; 2012
			 Intermountain Healthcare. All Rights Reserved.
		</div>

		<div id="spinner" class="spinner" style="display: none;">
			<g:message code="spinner.alt" default="Loading&hellip;" />
		</div>
		
	</div>
	
	<g:javascript library="application" />
	<r:layoutResources />
</body>
</html>