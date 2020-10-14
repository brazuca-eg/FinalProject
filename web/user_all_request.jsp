<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>My requests</title>
    <link rel="stylesheet" href="css/userReqs.css">
</head>
<body>
<jsp:include page="headers/header_client.jsp" />

<h3 align="center">Все заказы</h3>
<table border="1" align="center" >
    <tr>
        <td>Идентификатор заказа</td>
        <td>Логин мастера</td>
        <td>Имя мастера</td>
        <td>Фамилия мастера</td>
        <td>Дата</td>
        <td>Название заказа</td>
        <td>Описание заказа</td>
        <td>Цена</td>
        <td>Статус</td>
    </tr>
    <c:forEach items="${requestList}" var="element">
        <td>${element.id}</td>
        <td>${element.masterLogin}</td>
        <td>${element.masterName}</td>
        <td>${element.masterSurname}</td>
        <td>${element.date}</td>
        <td>${element.name}</td>
        <td>${element.description}</td>
        <td>${element.price}</td>
        <td>${element.statusName}</td>
        </tr>
    </c:forEach>

</table>



<jsp:include page="footer.jsp" />

</body>
</html>
