
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="css/registration.css">
</head>
<body size="10" face="Arial" >
    <jsp:include page="header.jsp" />
    <div class="center" >
        <form method="post" action="register" >
            <table border="0" align="center">
                <tr>
                    <td>Логин:</td>
                    <th><input id="loginField" type="text" name="login" placeholder="Enter email" /></th>
                </tr>
                <tr>
                    <td>Пароль:</td>
                    <td><input id="passwordField" type="text" name="password" placeholder="Enter password" /></td>
                </tr>
                <tr>
                    <td>Повторите пароль:</td>
                    <td><input id="secPasField" type="text" name="password2" placeholder="Enter password again" /></td>
                </tr>
                <tr>
                    <td>Фамилия: </td>
                    <td><input id="surnameField" type="text" name="surname" placeholder="Enter surname" /></td>
                </tr>
                <tr>
                    <td>Регистрация как: </td>
                    <td>
                        <select name="role_id">
                            <option value="1">Менеджер</option>
                            <option value="2">Мастер</option>
                            <option value="3">Пользователь</option>
                        </select>
                    </td>
                </tr>
            </table>

            <input id="button" class="green" type="submit" name="Закончить регистрацию" />
        </form>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>
