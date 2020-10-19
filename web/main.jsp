<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>

<c:choose>
    <c:when test="${role==1}">
        <jsp:include page="headers/header_manager.jsp" />
    </c:when>
    <c:when test="${role==2}">
        <jsp:include page="headers/header_master.jsp" />
    </c:when>
    <c:when test="${role==3}">
        <jsp:include page="header_client.jsp" />
    </c:when>
    <c:otherwise>
        <jsp:include page="header.jsp" />
    </c:otherwise>
</c:choose>
<h3 align="center">Сайт ремонтного агентства</h3><br>
<p align="center">
    <img src="https://lh3.googleusercontent.com/proxy/Qi2tPIgcnpUpRGKHVjlDv2QQZZUf3hLjrXeH-D-UM1sLfbbOaJ1hKlSGlGMJdE2dalFxqT6Oi7WwZYl81MC-yIwYkl6w56VeEDUQRCTbxqvEjuVFsJ_FGX2hCzmi7vCsAuIi_kFizV1k" />
</p>







<jsp:include page="footer.jsp" />
</body>
</html>
