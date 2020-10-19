
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="headers/header_master.jsp" />
<br>
<h3 align="center">${current}</h3>
<table border="1" align="center" >
    <tr>
        <td>${order_id}</td>
        <td>${client_login}</td>
        <td>${client_name}</td>
        <td>${client_surname}</td>
        <td>${order_date}</td>
        <td>${order_name}</td>
        <td>${order_desc}</td>
        <td>${order_price}</td>
        <td>${order_status}</td>
        <td>${change_status}</td>


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
                        <button name = "start" value="${element.id}">${start}</button>
                    </form>
                </c:when>
                <c:when test="${element.status_name == status_work}">
                    <form method="post">
                        <button name = "finish" value="${element.id}">${finish}</button>
                    </form>
                </c:when>
            </c:choose>
        </td>
        </tr>
    </c:forEach>

</table>

<%--<a href="${link}">${archive}</a>--%>

<jsp:include page="footer.jsp" />



</body>
</html>
