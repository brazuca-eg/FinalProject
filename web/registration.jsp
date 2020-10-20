
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
<%--    <link rel="stylesheet" href="css/registration.css">--%>
    <style>
        <%@ include file="css/registration.css"%>
    </style>
</head>
<body size="10" face="Arial" >
    <jsp:include page="header.jsp" />
    <br>
    <div class="center" >
        <form method="post" action="register"  >
                <tr>
                    <td>Email:</td>
                    <th><input id="emailField" type="text" name="email" placeholder="Enter email" /></th>
                </tr>
                <br>
                <tr>
                    <td>Логин:</td>
                    <th><input id="loginField" type="text" name="login" placeholder="Enter login" /></th>
                </tr>
                <br>
                <tr>
                    <td>Пароль:</td>
                    <td><input id="passwordField" type="text" name="password" placeholder="Enter password" /></td>
                </tr>
                <br>
                <tr>
                    <td>Повторите пароль:</td>
                    <td><input id="secPasField" type="text" name="password2" placeholder="Enter password again" /></td>
                </tr>
                <br>
                <tr>
                    <td>Имя: </td>
                    <td><input id="nameField" type="text" name="name" placeholder="Enter name" /></td>
                </tr>
                <br>
                <tr>
                    <td>Фамилия: </td>
                    <td><input id="surnameField" type="text" name="surname" placeholder="Enter surname" /></td>
                </tr>
                <br>
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
