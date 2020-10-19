
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error page</title>
</head>
<body>
<p align="center">
<img src="https://cmates.blob.core.windows.net/cmmaterial/material_18_4_25_rqyps.jpeg"  align="center">
</p>

<h3 align="center">${error}</h3>


<button ><a href="${path}">Назад</a></button>
<jsp:include page="footer.jsp" />
</body>
</html>
