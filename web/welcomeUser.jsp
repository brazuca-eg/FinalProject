<%@ page import="java.io.PrintWriter" %>
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
    <jsp:include page="header.jsp" />
    <b>Hello customer, ${userLogin}</b><br>

    <form action="logout">
        <input type="submit" value="Logout">
    </form>

    <jsp:include page="footer.jsp" />

</body>
</html>
