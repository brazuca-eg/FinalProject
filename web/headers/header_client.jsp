<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" href="css/header.css">
</head>
<ul>
    <li><a href="#">Главная</a></li>
    <li><a href="${path}/clientWelcome">Мой кабинет</a></li>
    <li><a href="${path}/request">Сделать заказ</a></li>
    <li><a href="${path}/userPayRequests">Оплатить заказы</a></li>
    <li><a href="${path}/allUserRequests">Все заказы</a></li>
    <li><a href="${path}/userArchive">Архив</a></li>
    <li><a href="${path}/logout">Выйти</a></li>
    <li>
        <form method="get" action="/locale">
            <select name="locale">
                <option value="ru">RU</option>
                <option value="en" selected>EN</option>
            </select>
            <input type="submit" value="Submit">
        </form>
    </li>
</ul>
