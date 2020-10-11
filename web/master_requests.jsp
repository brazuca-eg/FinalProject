<%@ page import="ua.kharkov.repairagency.db.entity.Status" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp" />

<h3 align="center">Текущие заявки на ремонт</h3>
<table border="1" align="center" >
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
        <td>Изменить статус</td>
    </tr>
    <c:forEach items="${masterList}" var="element">
        <td>${element.id}</td>
        <td>${element.client_login}</td>
        <td>${element.client_name}</td>
        <td>${element.client_surname}</td>
        <td>${element.date}</td>
        <td>${element.name}</td>
        <td>${element.description}</td>
        <td>${element.price}</td>
        <td>${element.status_name}</td>
        <td>
            <c:choose>
                <c:when test="${element.status_name == status_paid}">
                    <form method="post">
                        <button name = "start" value="${element.id}">Start work</button>
                    </form>
                </c:when>
                <c:when test="${element.status_name == status_work}">
                    <form method="post">
                        <button name = "finish" value="${element.id}">Finish work</button>
                    </form>
                </c:when>
            </c:choose>
        </td>
        </tr>
    </c:forEach>

</table>

<a href="${link}">Архив</a>

<jsp:include page="footer.jsp" />



</body>
</html>
