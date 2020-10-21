<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome client</title>
</head>
<body>
    <jsp:include page="header_client.jsp" />


    <h3>Мой профиль</h3>
    <p>Логин: ${found.login}</p>
    <p>Почта: ${found.email}</p>
    <p>Имя: ${found.name}</p>
    <p>Фамилия: ${found.surname}</p>
    <p>Текущий баланс: ${balance.balance}</p>
    <br>${first}


    <jsp:include page="footer.jsp" />
</body>
</html>
