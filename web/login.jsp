<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 26.09.2020
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Login page</title>
  </head>
  <body>
    <jsp:include page="header.jsp" />
    <form method="get" action="login" >
      <p>Логин: </p><br>
      <input id="loginField" type="text" name="login" placeholder="Enter email" />
      <p>Пароль: </p><br>
      <input id="passwordField" type="text" name="password" placeholder="Enter password" /><br>
      <input id="button" type="submit" name="Login" />

<%--      <input id="but" type="submit" name="logout" />--%>
    </form>

    <jsp:include page="footer.jsp" />

  </body>
</html>
