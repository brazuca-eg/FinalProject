
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <jsp:include page="headers/header_manager.jsp" />
    <h3>Мой профиль</h3>
    <p>Логин: ${found.login}</p>
    <p>Почта: ${found.email}</p>
    <p>Имя: ${found.name}</p>
    <p>Фамилия: ${found.surname}</p>
    <br>

    <jsp:include page="footer.jsp" />

</body>
</html>