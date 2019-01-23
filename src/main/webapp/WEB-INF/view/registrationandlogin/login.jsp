<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="label.login"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login-form.css"/>
</head>

<body>

<div class="login">
    <c:if test="${param.error != null}">
        <p style='color:red'>
            <spring:message code="label.wrong_password"/>
        </p>
    </c:if>
    <form method="post" action="<c:url value="/login"/>">
        <label>
            <spring:message code="label.user_email"/><br>
            <input type="email" name="username">
        </label><br>
        <label>
            <spring:message code="label.user_password"/><br>
            <input type="password" name="password">
        </label><br>
        <br>
        <input type="submit" value="OK" name="Ok">
        <input type="reset" value="Reset">
    </form>
</div>

</body>
</html>
