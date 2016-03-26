<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bulgarian JUG</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body>
<div class="register-form">
	<div class="app-title">
				<h1>New guestbook user</h1>
	</div>
	<form id="guestbook-register-form" action="register" method="post">
	
	    <div class="control-group">
			<input type="text" class="register-field" value="" placeholder="username" id="userName" name="userName">
		</div>
	
		<div class="control-group">
			<input type="password" class="register-field" value="" placeholder="password" id="password" name="password">
		</div>
		
		<div class="control-group">
			<input type="password" class="register-field" value="" placeholder="reenter password" id="reenterPassword" name="reenterPassword">
		</div>
		
		<div class="control-group">
			<input type="text" class="register-field" value="" placeholder="first name" id="firstName" name="firstName">
		</div>
	
		<div class="control-group">
			<input type="text" class="register-field" value="" placeholder="last name" id="lastName" name="lastName">
		</div>
			
		<a onclick="document.getElementById('guestbook-register-form').submit();" class="btn btn-primary btn-large btn-block">Register</a>
	</form>
</div>
</body>
</html>
