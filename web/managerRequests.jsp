<%@ page import="ua.kharkov.repairagency.db.entity.User" %>
<%@ page import="ua.kharkov.repairagency.db.DAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>See requests</title>
    <link rel="stylesheet" href="css/managerReqs.css">
</head>
<body>
<jsp:include page="header.jsp" />
    <br>
    <div>
        <div style="width: 50%; float:left">
            <h3>Сортировать заявки</h3>
            <form method="get" action="">
                <select name = "select_sort">
                    <option value=" date ASC" >По дате по возрастанию</option>
                    <option value=" date DESC" >По дате по снижению</option>
                    <option value=" status.name ASC" >По статусу по возрастанию</option>
                    <option value=" status.name DESC" >По статусу по снижению</option>
                    <option value=" price ASC" >По цене по возрастанию</option>
                    <option value=" price DESC" >По цене по снижению</option>
                </select>
                <input  type="submit" name="sort" value="Сортировать"/>
            </form>
        </div>


        <div style="width: 50%; float:left" align="right">
                <h3>Фильтр по статусу</h3>
                <form method="get">
                    <select name = "filter_status1">
                        <c:forEach items="${statuses}" var="element">
                            <option value="${element.id}" >${element.name}</option>
                        </c:forEach>
                        <input  type="submit" name="filter1" value="Фильтровать по статусу"/>
                    </select>
                </form>
                <h3>Фильтр по мастеру</h3>
                <form method="get">
                    <select name = "filter_status2">
                        <c:forEach items="${masters}" var="element">
                            <option value="${element.id}" >${element.name} ${element.surname}</option>
                        </c:forEach>
                        <input  type="submit" name="filter2" value="Фильтровать по мастеру"/>
                    </select>
                </form>
            </div>



    </div>

    <h3 align="center">Заявки на ремонт</h3>
    <table border="1" align="center" >
        <tr>
            <td>Идентификатор заказа</td>
            <td>Логин пользователя</td>
            <td>Дата</td>
            <td>Название</td>
            <td>Описание</td>
            <td>Цена</td>
            <td>Статус</td>
            <td>Имя мастера</td>
            <td>Фамилия мастера</td>
        </tr>
        <c:forEach items="${list}" var="element">

                <td>${element.id}</td>
                <td>${element.userlogin}</td>
                <td>${element.date}</td>
                <td>${element.name}</td>
                <td>${element.description}</td>
                <c:choose>
                    <c:when test="${element.price==0}">
                        <td><c:out value="Ждет установки цены"/></td>
                    </c:when>
                    <c:when test="${element.price!=0}">
                        <td>${element.price}</td>
                    </c:when>
                </c:choose>
                <td>${element.status_name}</td>
                <td>${element.master_name} </td>
                <td>${element.master_surname}</td>

<%--                <c:choose>--%>
<%--                    <c:when test="${element.master_id==4}">--%>
<%--                        <td><c:out value="Пока не назначен"/></td>--%>
<%--                    </c:when>--%>
<%--                    <c:when test="${element.master_id!=4}">--%>
<%--                        <% User master = DAO.getInstance().findUserId(request.getAttribute());--%>
<%--                            String name = master.getName() + " " + master.getSurname();%>--%>
<%--                        <td><% name.toString(); %></td>--%>
<%--                    </c:when>--%>
<%--                </c:choose>--%>
            </tr>
        </c:forEach>
    </table>

    <hr align="center" width="100%" color="Red" /><br>
    <h3 align="center">Действия</h3>


    <div style="width: 100%">
        <div style="width: 33% ; float:left">
            <h3>Назначить цену и мастера для заказа</h3>
            <form method="post">
                <p>Введите идентификатор:</p><input type="number" size="40" name="ident">
                <p>Введите цену за заказ:</p><input type="number" size="40" name="price">
                <p>Выберите мастера:</p>
                <select name = "select_master">
                    <c:forEach items="${masters}" var="element">
                        <option value="${element.id}" >${element.name} ${element.surname}</option>
                    </c:forEach>
                </select>
                <input id="button" type="submit" name="send" value="Send"/>
            </form>
        </div>



    </div>







<jsp:include page="footer.jsp" />
</body>
</html>
