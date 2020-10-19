<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Master's archive requests</title>
    <link rel="stylesheet" href="css/table.css">
</head>
<body>


<jsp:include page="headers/header_master.jsp" />
<br>
<h3 align="center">Архив работ</h3>
<table  align="center" class="table_blur">
    <tr>
        <td>Идентификатор заказа</td>
        <td>Логин заказчика</td>
        <td>Имя заказчика</td>
        <td>Фамилия заказчика</td>
        <td>Дата</td>
        <td>Название заказа</td>
        <td>Описание заказа</td>
        <td>Цена</td>
        <td>Статус</td>
        <td>Отзыв</td>
    </tr>
    <c:forEach items="${archiveList}" var="element">
        <td>${element.id}</td>
        <td>${element.client_login}</td>
        <td>${element.client_name}</td>
        <td>${element.client_surname}</td>
        <td>${element.date}</td>
        <td>${element.name}</td>
        <td>${element.description}</td>
        <td>${element.price}</td>
        <td>${element.status_name}</td>
        </tr>
    </c:forEach>

</table>



<jsp:include page="footer.jsp" />
</body>
</html>
