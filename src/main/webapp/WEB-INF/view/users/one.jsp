<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        <spring:message code="label.title_user"/>
    </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common_style.css"/>
</head>
<body>
<div class="loginContainer">
    <span style="float: right">
        <a href="?lang=en">en</a>    |    <a href="?lang=ru">ru</a>
    </span>
</div>



<a href="<c:url value='..'/>">
    <spring:message code="label.back_to_previous_page"/>
</a>
<h1>
    <spring:message code="label.user_details"/></h1>
<table class="tg">
    <tr>
        <th width="40">
            <spring:message code="label.id"/>
        </th>
        <th width="100">
            <spring:message code="label.user_name"/>
        </th>
        <th width="100">
            <spring:message code="label.user_surname"/>
        </th>
        <th width="100">
            <spring:message code="label.user_country"/>
        </th>
        <th width="100">
            <spring:message code="label.user_birthday"/>
        </th>
        <th width="120">
            <spring:message code="label.user_phone"/>
        </th>
        <th width="140">
            <spring:message code="label.user_email"/>
        </th>
        <th width="60">
            <spring:message code="label.user_type"/>
        </th>
        <th width="60">
            <spring:message code="label.delete"/>
        </th>
    </tr>
    <tr>
        <td><c:out value="${user.id}"/></td>
        <td><c:out value="${user.name}"/></td>
        <td><c:out value="${user.surname}"/></td>
        <td><c:out value="${user.country}"/></td>
        <td><c:out value="${user.birthday}"/></td>
        <td><c:out value="${user.phone}"/></td>
        <td><c:out value="${user.email}"/></td>
        <td><c:out value="${user.userType}"/></td>
        <td><a href="<c:url value='/users/delete/${user.id}'/>">Delete</a></td>
    </tr>
</table>

<h2> <spring:message code="label.edit"/> </h2>

<c:url var="editAction" value="/user/edit"/>

<form:form method="POST" action="${editAction}" modelAttribute="user">
    <table>
        <tr>
            <td><form:label path="id">
                <spring:message code="label.id"/>
            </form:label></td>
            <td><form:input path="id" readonly="true"/></td>
        </tr>
        <tr>
            <td><form:label path="name">
                <spring:message code="label.user_name"/>
            </form:label></td>
            <td width="200"><form:input path="name"/></td>
        </tr>
        <tr>
            <td><form:label path="surname">
                <spring:message code="label.user_surname"/>
            </form:label></td>
            <td><form:input path="surname"/></td>
        </tr>
        <tr>
            <td><form:label path="country">
                <spring:message code="label.user_country"/>
            </form:label></td>
            <td><form:input path="country"/></td>
        </tr>
        <tr>
            <td><form:label path="birthday">
                <spring:message code="label.user_birthday"/>
            </form:label></td>
            <td><form:input path="birthday"/></td>
        </tr>
        <tr>
            <td><form:label path="phone">
                <spring:message code="label.user_phone"/>
            </form:label></td>
            <td><form:input path="phone"/></td>
        </tr>
        <tr>
            <td><form:label path="email">
                <spring:message code="label.user_email"/>
            </form:label></td>
            <td><form:input path="email"/></td>
        </tr>
        <tr>
            <td><form:label path="password">
                <spring:message code="label.user_password"/>
            </form:label></td>
            <td><form:input path="password" readonly="true"/></td>
        </tr>
        <tr>
            <td><form:label path="userType">
                <spring:message code="label.user_type"/>
            </form:label></td>
            <td>
                <form:radiobutton path="userType" value="USER"/>
                <spring:message code="label.title_user"/>
                <form:radiobutton path="userType" value="ADMIN"/>
                <spring:message code="label.admin"/>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>
