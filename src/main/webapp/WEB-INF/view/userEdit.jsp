<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
    <style>
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

<h2> Edit User ${user.id}</h2>
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
    </tr>
    <tr>
        <td><a href="<c:url value='/view/users/user/${user.id}'/>"> <c:out value="${user.id}"/> </a></td>
        <td><c:out value="${user.name}"/></td>
        <td><c:out value="${user.surname}"/></td>
        <td><c:out value="${user.country}"/></td>
        <td><c:out value="${user.birthday}"/></td>
        <td><c:out value="${user.phone}"/></td>
        <td><c:out value="${user.email}"/></td>
        <td><c:out value="${user.userType}"/></td>
    </tr>
</table>
<br/>
<br/>

<form:form method="POST" action="/view/users/edit/${user.id}" modelAttribute="user">
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
</body>
</html>
