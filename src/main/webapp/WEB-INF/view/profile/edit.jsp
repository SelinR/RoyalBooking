<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
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
    </style>
</head>
<body>
<a href="<c:url value='..'/>">Back to previous page</a>
<h1> User details </h1>
<table class="tg">
    <tr>
        <th width="100">Name</th>
        <th width="100">Surname</th>
        <th width="100">Country</th>
        <th width="100">Birthday</th>
        <th width="120">Phone</th>
        <th width="140">Email</th>
    </tr>
    <tr>
        <td><c:out value="${user.name}"/></td>
        <td><c:out value="${user.surname}"/></td>
        <td><c:out value="${user.country}"/></td>
        <td><c:out value="${user.birthday}"/></td>
        <td><c:out value="${user.phone}"/></td>
        <td><c:out value="${user.email}"/></td>
    </tr>
</table>

<h2> Edit user </h2>

<c:url var="editAction" value="/profile/edit/submit"/>

<form:form method="POST" action="${editAction}" modelAttribute="user">
    <table>
        <tr>
            <td><form:hidden path="id"/></td>
        </tr>
        <tr>
            <td><form:label path="name">Name</form:label></td>
            <td width="200"><form:input path="name"/></td>
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
            <td><form:hidden path="password"/></td>
        </tr>
        <tr>
            <td><form:hidden path="userType"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
<button onclick="location.href='delete'">Delete your profile</button>
</body>
</html>
