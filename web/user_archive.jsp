<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Client's archive</title>
    <style>
        <%@ include file="css/table.css"%>
    </style>
</head>
<body>
<jsp:include page="header_client.jsp" />

<br>
<table align="center" class="table_blur" >

    <tr >
        <td>${order_id}</td>
        <td>${order_master_login}</td>
        <td>${order_master_name}</td>
        <td>${order_master_surname}</td>
        <td>${order_date}</td>
        <td>${order_name}</td>
        <td>${order_desc}</td>
        <td>${order_price}</td>
        <td>${order_status}</td>
        <td>${feedback}</td>
        <td>${stars}</td>
    </tr>

    <c:forEach var="entry" items="${archiveList}">
    <tr >
        <td>${entry.key.id}</td>
        <td>${entry.key.client_login}</td>
        <td>${entry.key.client_name}</td>
        <td>${entry.key.client_surname}</td>
        <td>${entry.key.date}</td>
        <td>${entry.key.name}</td>
        <td>${entry.key.description}</td>
        <td>${entry.key.price}</td>
        <td>${entry.key.status_name}</td>
        <td>${entry.value.text}</td>
        <td>${entry.value.stars}</td>
    </tr>
    </c:forEach>
</table>
<hr>

    <br>
<div align="center">
    <h3>Изменить отзыв</h3>
    <form method="post">
        <p>Идентификатор заказа: </p><input  type="number" name="ident" placeholder="Enter id" value="0" />
        <p>Текст: </p><input  type="text" name="textb" placeholder="Enter text"  />
        <p>Колличество звезд: </p>
        <input type="radio" name="answer" value="1">1<Br>
        <input type="radio" name="answer" value="2">2<Br>
        <input type="radio" name="answer" value="3">3<Br>
        <input type="radio" name="answer" value="4">4<Br>
        <input type="radio" name="answer" value="5">5<Br>
        <input  type="submit" name="Изменить" />
    </form>
</div>

<jsp:include page="footer.jsp" />
</body>
</html>
