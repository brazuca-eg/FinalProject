<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Login page</title>
  </head>
  <body>
    <jsp:include page="header.jsp" />
    <form method="get" action="" >
      <p>Логин: </p>
      <input id="loginField" type="text" name="login" placeholder="Enter email" />
      <p>Пароль: </p>
      <input id="passwordField" type="password" name="password" placeholder="Enter password" /><br>
      <input id="button" type="submit" name="Login" value="Login"/>
    </form>

    <p>${errors.cant_find}</p>
    <p>${errors.validate}</p>

    <h3>У вас еще нету аккаунта?</h3>
    <form method="post" action="" >
      <input  type="submit" name="Регистрация" value="Регистрация" />
    </form>

    <jsp:include page="footer.jsp" />

  </body>
</html>
