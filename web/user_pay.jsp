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


<h3>My balance: ${myBalance}</h3>
<h3 align="center">${wait}</h3>
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
        <td>${pay}</td>
    </tr>
    <c:forEach items="${waitingPaymentList}" var="element">
        <td>${element.id}</td>
        <td>${element.masterLogin}</td>
        <td>${element.masterName}</td>
        <td>${element.masterSurname}</td>
        <td>${element.date}</td>
        <td>${element.name}</td>
        <td>${element.description}</td>
        <td>${element.price}</td>
        <td>${element.statusName}</td>
        <td>
            <form method="post">
                <button name = "pay" value="${element.id}">${make_pay}</button>
            </form>
        </td>
        </tr>
    </c:forEach>

</table>

${paid}


<jsp:include page="footer.jsp" />

</body>
</html>
