<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<jsp:useBean id="status" class="ua.kharkov.repairagency.db.entity.Status"/>--%>
<html>
<head>
    <title>My requests</title>
    <link rel="stylesheet" href="css/userReqs.css">
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

            <c:choose>
                <c:when test="${element.status_id==1}">
                    <td><c:out value="Ждет оплаты"/></td>
                </c:when>
                <c:when test="${element.status_id==2}">
                    <td><c:out value="Оплачено"/></td>
                </c:when>
                <c:when test="${element.status_id==3}">
                    <td><c:out value="Отменено"/></td>
                </c:when>
                <c:when test="${element.status_id==4}">
                    <td><c:out value="В работе"/></td>
                </c:when>
                <c:otherwise>
                    <td><c:out value="Исполнено"/></td>
                </c:otherwise>
            </c:choose>

            <td>${element.name}</td>
            <td>${element.date}</td>
            <td>${element.description}</td>
        </tr>
    </c:forEach>
</table>



<jsp:include page="footer.jsp" />

</body>
</html>
