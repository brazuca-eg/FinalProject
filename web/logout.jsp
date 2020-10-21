<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logout page</title>
</head>
<body>
<jsp:include page="headers/header_manager.jsp" />

<h3 align="center">Вы точно хотите выйти?</h3>
<form method="post">
    <input name = "yes" value="Yes" type="submit">
    <input name = "no" value="No" type="submit">
</form>


<jsp:include page="footer.jsp" />
</body>
</html>
