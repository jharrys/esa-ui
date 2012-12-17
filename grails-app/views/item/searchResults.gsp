
<!doctype html>
<html>
	<head>
		<meta name="layout" content="general">
	</head>
	<body>
		<div class="row-fluid">

			<div class="span9">

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:each in="${foundItems }">
				    <p>Name: ${it.name }</p>
				    <p>Description: ${it.description }</p>
				    <p>Comments: ${it.comments }</p>
				</g:each>

			</div>

		</div>
	</body>
</html>
