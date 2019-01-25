<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="for" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring-tag" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        <spring-tag:message code="label.all_users"/>
    </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/3_buttons.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common_style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-tables.css"/>
    <link rel='stylesheet' href='https://fonts.googleapis.com/icon?family=Material+Icons'>

</head>
<body>
<c:import url="/WEB-INF/view/header/header.jsp"/>

<h2>
    <spring-tag:message code="label.all_users"/>
</h2>
<table class="tg">
    <tr>
        <th width="40">
            <spring-tag:message code="label.id"/>
        </th>
        <th width="100">
            <spring-tag:message code="label.user_name"/>
        </th>
        <th width="100">
            <spring-tag:message code="label.user_surname"/>
        </th>
        <th width="100">
            <spring-tag:message code="label.user_country"/>
        </th>
        <th width="100">
            <spring-tag:message code="label.user_birthday"/>
        </th>
        <th width="120">
            <spring-tag:message code="label.user_phone"/>
        </th>
        <th width="140">
            <spring-tag:message code="label.user_email"/>
        </th>
        <th width="60">
            <spring-tag:message code="label.user_type"/>
        </th>
        <th width="60">
            <spring-tag:message code="label.edit"/>
        </th>
        <th width="60">
            <spring-tag:message code="label.delete"/>
        </th>
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
            <td><a href="<c:url value='/admin/user/${user.id}'/>">
                <spring-tag:message code="label.edit"/>
            </a></td>
            <td><a href="<c:url value='/admin/users/delete/${user.id}'/>">
                <spring-tag:message code="label.delete"/>
            </a></td>
        </tr>
    </c:forEach>
</table>

<h2> <spring-tag:message code="label.add_new_user"/> </h2>
<c:url var="addAction" value="/admin/users/add"/>
<form:form method="POST" action="${addAction}" modelAttribute="user">
    <div>
    <table>
        <tr>
            <td><form:label path="name">
                <spring-tag:message code="label.user_name"/>
            </form:label></td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td><form:label path="surname">
                <spring-tag:message code="label.user_surname"/>
            </form:label></td>
            <td><form:input path="surname"/></td>
        </tr>
        <tr>
            <td><form:label path="country">
                <spring-tag:message code="label.user_country"/>
            </form:label></td>
            <td><form:input path="country"/></td>
        </tr>
        <tr>
            <td><form:label path="birthday">
                <spring-tag:message code="label.user_birthday"/>
            </form:label></td>
            <td><form:input path="birthday"/></td>
        </tr>
        <tr>
            <td><form:label path="phone">
                <spring-tag:message code="label.user_phone"/>
            </form:label></td>
            <td><form:input path="phone"/></td>
        </tr>
        <tr>
            <td><form:label path="email">
                <spring-tag:message code="label.user_email"/>
            </form:label></td>
            <td><form:input path="email"/></td>
        </tr>
        <tr>
            <td><form:label path="password">
                <spring-tag:message code="label.user_password"/>
            </form:label></td>
            <td><form:input path="password"/></td>
        </tr>
        <tr>
            <td><form:label path="userType">
                <spring-tag:message code="label.user_type"/>
            </form:label></td>
            <td>
                <form:radiobutton path="userType" value="USER"/>
                <spring-tag:message code="label.title_user"/> <br>
                <form:radiobutton path="userType" value="ADMIN"/>
                <spring-tag:message code="label.admin"/> <br>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="<spring-tag:message code="label.submit"/>"/></td>
        </tr>
    </table>
</form:form>
    </div>
</body>
</html>
