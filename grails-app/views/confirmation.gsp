<!doctype html>
<html>
<head>
<title>Confirmation</title>
<meta name="layout" content="bootstrap">
</head>
<body>
	<div class="row-fluid">

		<section id="main" class="span9">
			<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">
					${flash.message}
				</bootstrap:alert>
			</g:if>
			<g:renderException exception="${exception}" />
			
		</section>
	</div>
</body>
</html>