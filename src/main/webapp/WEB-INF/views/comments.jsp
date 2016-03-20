<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Bulgarian JUG</title>
</head>
<body>
    <h1>Bulgarian JUG guest book</h1>
    Hello, <c:out value="${user.firstName}"/>
    <a href="newcomment">Add comment</a>
    <table>
        <tr>
            <th>Title</th>
            <th>Content</th>
            <th>Author</th>
            <c:if test="${user.admin}"><th>Admin</th></c:if>
        </tr>

        <c:forEach items="${comments}" var="comment">
            <tr>
                <td><c:out value="${comment.title}"/></td>
                <td><c:out value="${comment.content}"/></td>
                <td><c:out value="${comment.byUser.firstName}"/>
                    <c:out value="${comment.byUser.lastName}"/></td>
                <c:if test="${user.admin}"><td><a href="comment/delete?commentId=${comment.id}">Delete</a></td></c:if>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
