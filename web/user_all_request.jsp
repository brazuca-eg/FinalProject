<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>My requests</title>
<%--    <link rel="stylesheet" href="css/userReqs.css">--%>
    <style>
        <%@ include file="css/userReqs.css"%>
    </style>
</head>
<body>
<jsp:include page="header_client.jsp" />

<form method="post" >
    <select name="localeForm">
        <option value="ru">RU</option>
        <option value="en">EN</option>
    </select>
    <input type="submit" value="Submit">
</form>

<h3 align="center">${all_order_field}</h3>
<table border="1" align="center" >
    <tr>
        <td>${order_id}</td>
        <td>${order_master_login}</td>
        <td>${order_master_name}</td>
        <td>${order_master_surname}</td>
        <td>${order_date}</td>
        <td>${order_name}</td>
        <td>${order_desc}</td>
        <td>${order_price}</td>
        <td>${order_status}</td>


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
