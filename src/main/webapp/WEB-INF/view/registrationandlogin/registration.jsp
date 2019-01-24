<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="label.registration_page"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login-form.css"/>
</head>
<body>
<c:import url="/WEB-INF/view/header/header.jsp"/>

    <div class="login">
        <c:if test="${param.error != null}">
            <p style='color:red'>
                <spring:message code="label.bad_password"/>
            </p>
        </c:if>
        <form method="post" action="<c:url value="/registration"/>">
            <label>
                <spring:message code="label.user_name"/><br>
                <input type="text" name="name" value="${param.get("name")}" required>
            </label><br>
            <label>
                <spring:message code="label.user_surname"/><br>
                <input type="text" name="surname" value="${param.get("surname")}" required>
            </label><br>
            <label>
                <spring:message code="label.user_country"/><br>
                <input type="text" name="country" value="${param.get("country")}" required>
            </label><br>
            <label>
                <spring:message code="label.user_birthday"/><br>
                <input type="date" name="birthday" value="${param.get("birthday")}" required>
            </label><br>
            <label>
                <spring:message code="label.user_phone"/><br>
                <input type="text" name="phone" value="${param.get("phone")}" required>
            </label><br>
            <label>
                <spring:message code="label.user_email"/><br>
                <input type="text" name="email" value="${param.get("email")}" required>
            </label><br>
            <label>
                <spring:message code="label.user_password"/><br>
                <input type="password" name="password" required>
            </label>
            <label>
                <spring:message code="label.repeat_password"/>
                <input type="password" name="passwordValidation" required>
            </label>
            <br>
            <input type="submit" value="OK" name="Ok">
            <input type="reset">
        </form>
    </div>
</body>
</html>
