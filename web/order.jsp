<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MakeOrder</title>
</head>
<body>
<jsp:include page="header_client.jsp" />

<form method="post" action="" >
        <p>Название заказа: </p><br>
        <input id="nameField" type="text" name="name" placeholder="Enter name" />
        <p>Описание заказа: </p><br>
        <input id="descField" type="text" name="desc" placeholder="Enter description" /><br>
        <input id="button" type="submit" name="send" value="Send"/>
    </form>
    <br>
    ${know}

<jsp:include page="footer.jsp" />
</body>
</html>
