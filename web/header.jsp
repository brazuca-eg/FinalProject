<%@ page import="java.io.PrintWriter" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" href="css/header.css">
</head>
<ul>
    <%
        String usMenu1 = "";
        String usMenu2="";
        String usMenu3="";
        String usMenu4="";
        Object d = request.getSession().getAttribute("role");
        if(d == null){
            usMenu1 = "Главная";
            usMenu2 = "Войти";
            usMenu3 = "Регистрация";
        }else{
            int role = (int) request.getSession().getAttribute("role");
            if(role == 1){
                usMenu1 = "Главная";
                usMenu2 = "Мой кабинет";
                usMenu3 = "Заявки клиентов";
                usMenu4 = "Пополнить баланс";
            }
            else if(role == 2){
                usMenu1 = "Главная";
                usMenu2 = "Мой кабинет";
                usMenu3 = "Заявки";
                usMenu4 = "Завершенные";
            }
            else if(role == 3){
                usMenu1 = "Главная";
                usMenu2 = "Мой кабинет";
                usMenu3 = "Подать заявку ";
            }
        }
    %>
    <li><a href="#"><%= usMenu1 %></a></li>
    <li><a href="#"><%= usMenu2 %></a></li>
    <li><a href="#"><%= usMenu3 %></a></li>
    <li><a href="#"><%= usMenu4 %></a></li>
</ul>
