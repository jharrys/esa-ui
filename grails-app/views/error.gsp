<!doctype html>
<html>
<head>
<title>Runtime Exception</title>
<meta name="layout" content="bootstrap">
<link rel="stylesheet" href="${resource(dir: 'css', file: 'errors.css')}" type="text/css">
</head>
<body>
	<div class="row-fluid">

		<section id="main" class="span9">
			<g:if test="${flash.message}">
				<bootstrap:alert class="alert-error">
					${flash.message}
				</bootstrap:alert>
			</g:if>
			<g:renderException exception="${exception}" />
			
			<fieldset>
            <g:form class="form-horizontal" action="error" params="${[exception: exception] }">
                <fieldset>
                    <div class="control-group ${invalid ? 'error' : ''}">
                        <div class="form-actions">
                            <button type="submit" class="btn btn-primary">
                              <i class="icon-ok icon-white"></i>
                              Notify ESA Team
                            </button>
                        </div>
                    </div>
                </fieldset>
            </g:form>
        </fieldset>
			
			
		</section>
	</div>
</body>
</html>