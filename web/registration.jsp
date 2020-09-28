<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 28.09.2020
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <form method="get" action="register" >
        Логин: <input id="loginField" type="text" name="login" placeholder="Enter email" />
        Пароль: <input id="passwordField" type="text" name="password" placeholder="Enter password" />
        Повторите пароль: <input id="secPasField" type="text" name="password2" placeholder="Enter password again" />
        Фамилия: <input id="surnameField" type="text" name="surname" placeholder="Enter surname" />
        Регистрация как: <select name="role_id">
                            <option value="1">Менеджер</option>
                            <option value="2">Мастер</option>
                            <option value="3">Пользователь</option>
                        </select>
        <input id="button" type="submit" name="Закончить регистрацию" />
    </form>

</body>
</html>
