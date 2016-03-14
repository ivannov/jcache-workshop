<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bulgarian JUG</title>
</head>
<body>
<div style="color: red">${messagesBean.message}</div>
Submit your comment:
<form action="submit" method="post">
    <label for="title">Title:</label><input type="text" id="title" name="title"><br>
    <label for="content">Content:</label><textarea id="content" name="content"></textarea><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
