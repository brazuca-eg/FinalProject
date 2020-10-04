<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My requests</title>
</head>
<body>
<jsp:include page="header.jsp" />
<h3 align="center">Мои заказы</h3>
<br>
<table border="1" align="center" >
    <tr>
        <td>Идентификатор</td>
        <td>Статус</td>
        <td>Название</td>
        <td>Дата</td>
        <td>Описание</td>
    </tr>
    <c:forEach items="${list}" var="element">
        <tr>
            <td>${element.id}</td>
            <td>${element.status_id}</td>
            <td>${element.name}</td>
            <td>${element.date}</td>
            <td>${element.description}</td>
        </tr>
    </c:forEach>
</table>



<jsp:include page="footer.jsp" />

</body>
</html>
