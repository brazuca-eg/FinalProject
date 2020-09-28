<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 28.09.2020
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserFirst</title>
</head>
<body>

    <%
        if(session.getAttribute("userLogin") == null){
            response.sendRedirect("login.jsp");
        }
    %>
    <b>Hello customer, ${userLogin}</b><br>

    <form action="logout">
        <input type="submit" value="Logout">
    </form>

</body>
</html>
