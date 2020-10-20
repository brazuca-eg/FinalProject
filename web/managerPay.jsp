<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
    <head>
        <title>Pay balance for clients</title>
        <link rel="stylesheet" href="css/managerPay.css">
        <style>
            <%@ include file="css/managerPay.css"%>
        </style>
    </head>

    <body >
        <jsp:include page="headers/header_manager.jsp" />
        <br>

        <form method="get" action="" >
            <table border="0" align="center">
                <tr>
                    <td>Найти пользователя по уникальному идентификатору: </td>
                    <th><input id="identField" type="number" name="ident" placeholder="Enter id" /></th>
                    <th><input  type="submit" name="Найти пользователя" /></th>
                </tr>
            </table>
        </form>

        <% if(request.getAttribute("found")!=null ){%>

            <div align="center">
                <h3>Найденный пользователь</h3>
                <p>Логин: ${found.login}</p>
                <p>Почта: ${found.email}</p>
                <p>Имя: ${found.name}</p>
                <p>Фамилия: ${found.surname}</p>
                <p>Текущий баланс: ${balance.balance}</p>
                <% if(request.getAttribute("balance")!=null ){ %>
                <form method="post" action="" >
                    <table border="0" >
                        <tr>
                            <td>Пополнить счет: </td>
                            <th><input id="summa" type="number" name="summa" placeholder="Enter sum" value="0" /></th>
                        </tr>
                    </table>

                    <input  type="submit" name="Пополнить" />
                </form>

                <%}%>
            </div>
        <%}%>

        <table border="1" align="center" >

            <tr >
                <td>Уникальный идентификатор</td>
                <td>Логин пользователя</td>
                <td>Имя пользователя</td>
                <td>Фамилия пользователя</td>
                <td>Email</td>
                <td>Баланс</td>
            </tr>

            <c:forEach var="entry" items="${clients}">
                <tr >
                    <td>${entry.value.id}</td>
                    <td>${entry.value.login}</td>
                    <td>${entry.value.name}</td>
                    <td>${entry.value.surname}</td>
                    <td>${entry.value.email}</td>
                    <td>${entry.key.balance}</td>
                </tr>
            </c:forEach>


        </table>



        <jsp:include page="footer.jsp" />
    </body>
</html>
