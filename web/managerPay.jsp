<%@ page import="ua.kharkov.repairagency.db.entity.User" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Pay balance for clients</title>
    </head>

    <body>
        <jsp:include page="header.jsp" />


        <p>Найти пользователя по уникальному идентификатору: </p>
        <form method="post" action="" >
            <table border="0" align="center">
                <tr>
                    <td>Найти пользователя по уникальному идентификатору: </td>
                    <th><input id="identField" type="number" name="ident" placeholder="Enter id" /></th>
                </tr>
            </table>

            <input  type="submit" name="Найти пользователя" />
        </form>

<%--        <%  if(request.getAttribute("found") == null){ %>--%>
<%--                <p>${customers}</p>--%>
<%--        <%  }else {  %>--%>
<%--                <p>${found}</p>--%>
<%--        <%  }%>--%>
<%--        <p>${found}</p>&ndash;%&gt;--%>
        <p>${found.login}</p>
        <p>${found.surname}</p>
        <p>${balance.balance}</p>

        <% if(request.getAttribute("balance")!=null ){ %>
        <form method="post" action="" >
            <table border="0" align="center">
                <tr>
                    <td>Пополнить счет: </td>
                    <th><input id="summa" type="number" name="summa" placeholder="Enter sum" /></th>
                </tr>
            </table>

            <input  type="submit" name="Пополнить" />
        </form>

        <%}%>




        <jsp:include page="footer.jsp" />
    </body>
</html>
