<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="for" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common_style.css"/>
</head>
<body>

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
            <td><a href="<c:url value='user/${user.id}'/>"> <c:out value="${user.id}"/> </a></td>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.surname}"/></td>
            <td><c:out value="${user.country}"/></td>
            <td><c:out value="${user.birthday}"/></td>
            <td><c:out value="${user.phone}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.userType}"/></td>
            <td><a href="<c:url value='/admin/user/${user.id}'/>">Edit</a></td>
            <td><a href="<c:url value='/admin/users/delete/${user.id}'/>">Delete</a></td>
        </tr>
    </c:forEach>
</table>

<h2> Add new user</h2>
<c:url var="addAction" value="/admin/users/add"/>

<form:form method="POST" action="${addAction}" modelAttribute="user">
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
            <td><form:label path="password">Password</form:label></td>
            <td><form:input path="password"/></td>
        </tr>
        <tr>
            <td><form:label path="userType">User Type</form:label></td>
            <td>
                <form:radiobutton path="userType" value="USER"/>User
                <form:radiobutton path="userType" value="ADMIN"/>Admin
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>
