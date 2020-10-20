<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
<%--    <link rel="stylesheet" href="css/header.css">--%>
    <style>
        <%@ include file="css/header.css"%>
    </style>
</head>
<ul>
    <li><a  href="${path}/main.jsp">${h1}</a></li>
    <li><a href="${path}/clientWelcome">${cabinet}</a></li>
    <li><a href="${path}/request">${c_two}</a></li>
    <li><a href="${path}/myRequests">${с_six}</a></li>
    <li><a href="${path}/allUserRequests">${с_th}</a></li>
    <li><a href="${path}/userArchive">${с_fo}</a></li>
    <li><a href="${path}/logout">${с_fiv}</a></li>
</ul>
