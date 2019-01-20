<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<a href="<c:url value='..'/>">Back to previous page</a>
<h1> Profile details </h1>
<ul class="tg">
    <li>Name: <c:out value="${user.name}"/></li>
    <li>Surname: <c:out value="${user.surname}"/></li>
    <li>Country: <c:out value="${user.country}"/></li>
    <li>Birthday: <c:out value="${user.birthday}"/></li>
    <li>Phone: <c:out value="${user.phone}"/></li>
    <li>Email: <c:out value="${user.email}"/></li>
</ul>
<button onclick="location.href='profile${user.id}/edit'">Edit</button>
</body>
</html>
