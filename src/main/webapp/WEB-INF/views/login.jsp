<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bulgarian JUG</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body>
	<div class="app-title">
				<h1>Guestbook</h1>
	</div>
	<div class="login-form">
	    <form id="guestbook-login-form" action="login" method="post">
        	
        	<div class="control-group">
				<input type="text" class="login-field" value="" placeholder="username" id="userName" name="userName">
			</div>

			<div class="control-group">
				<input type="password" class="login-field" value="" placeholder="password" id="password" name="password">
			</div>
			<a onclick="document.getElementById('guestbook-login-form').submit();" class="btn btn-primary btn-large btn-block">Login</a>
		    <a class="btn btn-primary btn-large btn-block" href="register">Register</a>
    	</form>
   	</div>
</body>

</html>

