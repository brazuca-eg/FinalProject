<%@ page import="java.io.PrintWriter" %>
<%@ page import="ua.kharkov.repairagency.db.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserFirst</title>
</head>
<body>

    <%
        User user = null;
        if(session.getAttribute("userLogin") == null){
            response.sendRedirect("login.jsp");
        }else{
            user = (User) request.getSession().getAttribute("current_user");
        }
    %>
    <jsp:include page="header.jsp" />
    <b>Hello customer, ${userLogin}</b><br>

    <p>Фамилия <%= user.getSurname() %></p>

    <form action="logout">
        <input type="submit" value="Logout">
    </form>

    <jsp:include page="footer.jsp" />

</body>
</html>
