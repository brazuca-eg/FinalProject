<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 05.10.2020
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Error
<c:forEach items="${errors}" var="element">
        <td>${element}</td>
</c:forEach>

<button><a href="${path}">Назад</a></button>

</body>
</html>
