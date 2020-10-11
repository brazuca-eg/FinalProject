
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <jsp:include page="header.jsp" />
    <h3>Мой профиль</h3>
    <p>Логин: ${found.login}</p>
    <p>Почта: ${found.email}</p>
    <p>Имя: ${found.name}</p>
    <p>Фамилия: ${found.surname}</p>
    <br>
    <p><a href="http://localhost:8080/Epam_Project_war_exploded/seeRequests">Все заявки</a></p>
    <p><a href="http://localhost:8080/Epam_Project_war_exploded/find">Пополнить баланс пользователя</a></p>
    <jsp:include page="footer.jsp" />

</body>
</html>