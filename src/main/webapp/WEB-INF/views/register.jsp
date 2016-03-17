<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bulgarian JUG</title>
</head>
<body>
Register:
<form action="register" method="post">
    <label for="userName">User name:</label><input type="text" id="userName" name="userName"><br>
    <label for="password">Password:</label><input type="password" id="password" name="password"><br>
    <label for="reenterPassword">Reenter password:</label><input type="password" id="reenterPassword" name="reenterPassword"><br>
    <label for="firstName">First name:</label><input type="text" id="firstName" name="firstName"><br>
    <label for="lastName">Last name:</label><input type="text" id="lastName" name="lastName"><br>
    <input type="submit" value="Register">
</form>
</body>
</html>
