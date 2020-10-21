<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Master's archive requests</title>
    <%--    <link rel="stylesheet" href="css/table.css">--%>
    <style>
        <%@ include file="css/table.css"%>
    </style>
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
    </tr>
    <c:forEach items="${list}" var="element">
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

    <h3 align="center" >Отзывы</h3>
    <table align="center" class="table_blur">
        <tr>
            <td>Отзыв</td>
            <td>Звезды</td>
        </tr>
        <tr>
            <c:forEach items="${feedbacks}" var="element">
                <c:choose>
                    <c:when test="${element.stars==0}">
                        <td><c:out value="Текст отзыва еще не поставлен"/></td>
                        <td><c:out value="Звезды отзыва еще не поставлены"/></td>
                    </c:when>
                    <c:when test="${element.stars>0}">
                        <td>${element.text}</td>
                        <td>${element.stars}</td>
                    </c:when>
                </c:choose>
        </tr>
        </c:forEach>

        </tr>
    </table>

<div align="center" >
    <form method="get">
        <c:forEach var="i" begin="1" end="${pages}" step="1" varStatus ="status">
            <div><input name = "numPage" value="${i}" type="submit"></div>
        </c:forEach>
    </form>
</div>

<jsp:include page="footer.jsp" />
</body>
</html>