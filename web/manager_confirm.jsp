<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="headers/header_manager.jsp" />
<h3 align="center">Заявки на ремонт</h3>
<table border="1" align="center" >
    <tr>
        <td>Идентификатор заказа</td>
        <td>Логин пользователя</td>
        <td>Дата</td>
        <td>Название</td>
        <td>Описание</td>
        <td>Цена</td>
        <td>Статус</td>
        <td>Имя мастера</td>
        <td>Фамилия мастера</td>
        <td>Действие</td>
    </tr>
    <c:forEach items="${requests}" var="element">
        <td>${element.id}</td>
        <td>${element.userlogin}</td>
        <td>${element.date}</td>
        <td>${element.name}</td>
        <td>${element.description}</td>
        <td>${element.price}</td>
        <td>${element.status_name}</td>
        <td>${element.master_name} </td>
        <td>${element.master_surname}</td>
        <td>
            <form method="post">
                <button name = "paid" value="${element.id}">Оплачено</button>
            </form>

            <form method="post">
                <button name = "cancel" value="${element.id}">Отменить</button>
            </form>
        </td>
        </tr>
    </c:forEach>


    <jsp:include page="footer.jsp" />
</table>
</body>
</html>
