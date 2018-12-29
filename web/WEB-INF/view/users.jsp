<%--
  Created by IntelliJ IDEA.
  User: Tifider
  Date: 29.12.2018
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>

<h1> Users </h1>
<h2> All users </h2>
<c:forEach var="user" items="${requestScope.users}">
    <ul>
        <li> Имя: <c:out value="${user.name}"/></li>
        <li> Возраст: <c:out value="${user.age}"/></li>
    </ul>
</c:forEach>

<h2> Add new user</h2>
<form method="post" action="">
    <label> <input type="text" name="name"> </label>
    <label> <input type="number" name="age"> </label>
    <input type="submit" value="OK" name="Ok">
</form>
</body>
</html>
