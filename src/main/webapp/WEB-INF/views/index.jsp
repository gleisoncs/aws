<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="gleisoncs">
<link rel="icon" href="../../favicon.ico">

<title>AWS</title>

<!-- Bootstrap Core CSS -->
<link
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Signin CSS -->
<link href="${pageContext.request.contextPath}/bootstrap/css/signin.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="${pageContext.request.contextPath}/bootstrap/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

</head>

<body>

	<div class="container">

		<form name="urlForm" class="form-signin"
			action="${pageContext.request.contextPath}/processUrl" method="POST">
			<h2 class="form-signin-heading">Enter a valid URL</h2>
			<input type="text" name="url" class="form-control"
				placeholder="http://www.google.com" required> <br>
			<button class="btn btn-success" type="submit">Submit</button>
		</form>

	</div>
	<!-- /container -->

	<div class="panel-body">
		<table class="table table-bordered table-hover table-striped">
			<tbody>
				<tr>
					<td>URL</td>
					<td>${url}</td>
				</tr>
				<tr>
					<td>What HTML version has the document?</td>
					<td>${result.htmlVersion}</td>
				</tr>
				<tr>
					<td>What is the page title?</td>
					<td>${result.pageTitle}</td>
				</tr>
				<tr>
					<td>How many headings of what level are in the document?</td>
					<td>${result.headings}</td>
				</tr>
				<tr>
					<td>How many internal and external links are in the document?</td>
					<td>${result.links}</td>
				</tr>
				<tr>
					<td>Are there any inaccessible links and how many?</td>
					<td>${result.linksWorking}</td>
				</tr>
				<tr>
					<td>Did the page contain a login-form?</td>
					<td>${result.login}</td>
				</tr>
				<tr>
					<td>Status Message</td>
					<td>${result.statusCode}- ${result.statusMessage}</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>
