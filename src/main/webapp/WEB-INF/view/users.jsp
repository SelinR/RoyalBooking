<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta content="text/html" charset="UTF-8"/>
    <title>Users</title>
</head>

<body bgcolor="#fff8dc">
<h2> All users </h2>
<c:forEach var="user" items="${requestScope.users}">
    <ul>
        <li> Id: <c:out value="${user.id}"/></li>
        <li> Name: <c:out value="${user.name}"/></li>
        <li> Surname: <c:out value="${user.surname}"/></li>
        <li> Country: <c:out value="${user.country}"/></li>
        <li> Birthday: <c:out value="${user.birthday}"/></li>
        <li> Phone: <c:out value="${user.phone}"/></li>
        <li> Email: <c:out value="${user.email}"/></li>
        <li> User Type: <c:out value="${user.userType}"/></li>
    </ul>
</c:forEach>

<h2> Add new user</h2>
<form method="post" action="<c:url value="/view/users"/>">
    <label>
        Name<br>
        <input type="text" name="name">
    </label><br>
    <label>
        Surname<br>
        <input type="text" name="surname">
    </label><br>
    <label>
        Country<br>
        <input type="text" name="country">
    </label><br>
    <label>
        Birthday<br>
        <input type="text" name="birthday">
    </label><br>
    <label>
        Phone<br>
        <input type="text" name="phone">
    </label><br>
    <label>
        Email<br>
        <input type="text" name="email">
    </label><br>
    <label> User type<br>
        <input type="radio" name="userType" value="USER" checked> User
        <input type="radio" name="userType" value="ADMIN"> Admin
    </label><br>
    <input type="submit" value="OK" name="Ok">
</form>
</body>
</html>
