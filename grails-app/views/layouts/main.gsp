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
<%--<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">--%>
<%--<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">--%>
<r:require modules="blueprint" />
<g:layoutHead />
<r:layoutResources />
</head>
<body>

	<!-- begin blueprint container -->
	<div class="container">

		<!-- begin header row 24 columns wide -->
		<div id="header" class="span-24" role="banner">
			<a href="http://eisa-repository.co.ihc.com"><img src="${resource(dir: 'images', file: 'eisa_logo.png')}" alt="EISA" /></a>
		</div>
		<!-- end header row -->

		<!-- begin content row 24 columns wide -->
		<div id="content" class="span-24">
			<g:layoutBody />
		</div>
		<!-- end content row 24 columns wide -->

		<!-- begin footer row 24 columns wide -->
		<div id="footer" class="footer span-24" role="contentinfo">
			(c) 2012
		</div>
		<!-- end footer row 24 columns wide -->

		<!-- begin spinner row 24 columns wide -->
		<div id="spinner" class="spinner span-24" style="display: none;">
			<g:message code="spinner.alt" default="Loading&hellip;" />
		</div>
		<!-- end spinner row 24 columns wide -->
		
	</div>
	<!-- end blueprint container -->
	
	<g:javascript library="application" />
	<r:layoutResources />
</body>
</html>