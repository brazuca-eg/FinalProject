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
      <input id="button" type="submit" name="log" value="Login"/>
    </form>

    <p>${errors.cant_find}</p>
    <p>${errors.validate}</p>
    <br>
    <h3>У вас еще нету аккаунта?</h3>

    <form method="get" >
      <input  type="submit" name="reg" value="Регистрация" />
    </form>

    <jsp:include page="footer.jsp" />

  </body>
</html>
