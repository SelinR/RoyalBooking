<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>
<a href="<c:url value='../'/>">Back to previous page</a>

<h2> All users </h2>
<table class="tg">
    <tr>
        <th width="40">ID</th>
        <th width="100">Name</th>
        <th width="100">Surname</th>
        <th width="100">Country</th>
        <th width="100">Birthday</th>
        <th width="120">Phone</th>
        <th width="140">Email</th>
        <th width="60">User Type</th>
        <th width="60">Edit</th>
        <th width="60">Delete</th>
    </tr>
    <c:forEach var="user" items="${requestScope.users}">
        <tr>
            <td><a href="<c:url value='/view/users/user/${user.id}'/>"> <c:out value="${user.id}"/> </a></td>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.surname}"/></td>
            <td><c:out value="${user.country}"/></td>
            <td><c:out value="${user.birthday}"/></td>
            <td><c:out value="${user.phone}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.userType}"/></td>
            <td><a href="<c:url value='/view/users/edit/${user.id}'/>">Edit</a></td>
            <td><a href="<c:url value='/view/users/delete/${user.id}'/>">Delete</a></td>
        </tr>
    </c:forEach>
</table>

<h2> Add new user</h2>

<form:form method="POST" action="/view/users/add/" modelAttribute="user">
    <table>
        <tr>
            <td><form:label path="name">Name</form:label></td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td><form:label path="surname">Surname</form:label></td>
            <td><form:input path="surname"/></td>
        </tr>
        <tr>
            <td><form:label path="country">Country</form:label></td>
            <td><form:input path="country"/></td>
        </tr>
        <tr>
            <td><form:label path="birthday">Birthday</form:label></td>
            <td><form:input path="birthday"/></td>
        </tr>
        <tr>
            <td><form:label path="phone">Phone</form:label></td>
            <td><form:input path="phone"/></td>
        </tr>
        <tr>
            <td><form:label path="email">Email</form:label></td>
            <td><form:input path="email"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/> </td>
        </tr>
    </table>
</form:form>

<%--
<form method="post" action="<c:url value="/view/users/add"/>">
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
--%>
</body>
</html>
