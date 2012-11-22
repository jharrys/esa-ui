<%@ page import="org.ihc.esa.Document"%>
<%@ page import="org.ihc.esa.Form"%>
<%@ page import="org.ihc.esa.FormField"%>
<%@ page import="org.ihc.esa.LookupList"%>
<%@ page import="org.ihc.esa.LookupElement"%>
<!doctype html>
<html>
	<head>
	    <meta name="layout" content="bootstrap">
	    <title>New Exception</title>
	</head>
	<body>

		<div class="row-fluid">

			<g:if test="${flash.message}">
				<bootstrap:alert class="alert-warning">
					${flash.message}
				</bootstrap:alert>
			</g:if>

			<fieldset>
				<g:form class="form-horizontal" action="save">
					<fieldset>
						<legend>Application for Exception</legend>
						<div class="control-group ${invalid ? 'error' : ''}">
							<label class="control-label" for="title"> Requester: <span class="required-indicator">*</span>
							</label>
							<div class="controls">
								<g:field id="title" name="title" type="text" placeholder="Title For Exception" required="true" />
							</div>
							<div class="form-actions">
								<button type="submit" class="btn btn-primary">
									<i class="icon-ok icon-white"></i> Start Application Process...
								</button>
							</div>
						</div>
					</fieldset>
				</g:form>
			</fieldset>

		</div>
	</body>
</html>
