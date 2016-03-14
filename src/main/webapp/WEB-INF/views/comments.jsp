<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Bulgarian JUG</title>
</head>
<body>
    <h1>Bulgarian JUG guest book</h1>
    <table>
        <tr>
            <th>Title</th>
            <th>Content</th>
            <th>Author</th>
        </tr>

        <c:forEach items="${comments}" var="comment">
            <tr>
                <td><c:out value="${comment.title}"/></td>
                <td><c:out value="${comment.content}"/></td>
                <td><c:out value="${comment.byUser.firstName}"/>
                    <c:out value="${comment.byUser.lastName}"/></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
