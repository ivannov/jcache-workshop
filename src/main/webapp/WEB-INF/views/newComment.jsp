<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bulgarian JUG</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body id="new-comment">
	<div id="comment-form">
		<div style="color: red">${messagesBean.message}</div>
		<div class="app-title">
			<h1>New comment</h1>
		</div>
			
		<div class="newcomment-form">
			<form action="newcomment" method="post">
			
			     <div class="control-group">
					<input type="text" class="login-field" value="" placeholder="title" id="title" name="title">
				</div>
				
				<div class="control-group">
					<textarea id="content" name="content" placeholder="content"></textarea><br>
				</div>
				<button class="btn" type="submit">Add</button>			
			</form>
		</div>
	</div>
</body>
</html>
