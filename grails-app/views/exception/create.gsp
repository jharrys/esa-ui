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

		<div>
			<g:form action="save">
				<legend>Application for Exception</legend>
				<label for="requestor">Requester:</label>
				<input name="requestor" type="text" placeholder="The person entering this form...">
				<label for="requestorEmail">Requester Email Address:</label>
				<input name="requestorEmail" type="text" placeholder="Email">
				<label for="owner">Business Owner:</label>
				<input name="owner" type="text" placeholder="Business Owner">
				<label for="ownerEmail">Business Owner Email Address:</label>
				<input name="ownerEmail" type="text" placeholder="Business Owner Email">
				<label for="justification">Justification:
				<textarea name="justification" rows="3"></textarea>
				</label>
				<button type="submit" class="btn">Start Application Process...</button>
			</g:form>
		</div>

	</div>
</body>
</html>
