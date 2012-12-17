
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

				<fieldset>
                    <g:form class="form-horizontal" action="search" >
                        <fieldset>
                            <g:textField name="keywords" />
                            <div class="form-actions">
                                <button type="submit" class="btn btn-primary">
                                    <i class="icon-search icon-white"></i>
                                    <g:message code="default.button.search.label" default="Search" />
                                </button>
                                <button type="cancel" class="btn btn-danger" formnovalidate>
                                    <i class="icon-ban-circleblue icon-white"></i>
                                    <g:message code="default.button.cancel.label" default="Cancel" />
                                </button>
                            </div>
                        </fieldset>
                    </g:form>
                </fieldset>

			</div>

		</div>
	</body>
</html>
