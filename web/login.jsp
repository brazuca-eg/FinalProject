<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
  <head>
    <title>Login page</title>
  </head>
  <body>
    <jsp:include page="header.jsp" />

    <form method="post" >
      <select name="localeForm">
        <option value="ru">RU</option>
        <option value="en">EN</option>
      </select>
      <input type="submit" value="Submit">
    </form>

    <form method="get" action="" >

      <p>${one}: </p>
      <input id="loginField" type="text" name="login" placeholder="Enter email" />
      <p>${two}: </p>
      <input id="passwordField" type="password" name="password" placeholder="Enter password" /><br>
      <input id="button" type="submit" name="log" value="Login"/>
    </form>


    <br>

    <h3>
      ${thr}
    </h3


    <form method="get" >
      <button>
        <a href="${path}/register">Регистрация</a>
      </button>
    </form>


    <jsp:include page="footer.jsp" />

  </body>
</html>
