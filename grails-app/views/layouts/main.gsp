<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title><g:layoutTitle default="EISA" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
<r:require modules="blueprint" />
<g:layoutHead />
<r:layoutResources />
</head>
<body>

	<!-- begin blueprint container -->
	<div class="container">

		<div id="header" class="header" role="banner">
			<a href="http://eisa-repository.co.ihc.com"><img src="${resource(dir: 'images', file: 'eisa_logo.png')}" alt="EISA" /></a>
		</div>

		<div id="content" class="content">
			<g:layoutBody />
		</div>

		<div id="footer" class="footer" role="contentinfo">
			(c) 2012
		</div>

		<div id="spinner" class="spinner" style="display: none;">
			<g:message code="spinner.alt" default="Loading&hellip;" />
		</div>
		
	</div>
	<!-- end blueprint container -->
	
	<g:javascript library="application" />
	<r:layoutResources />
</body>
</html>