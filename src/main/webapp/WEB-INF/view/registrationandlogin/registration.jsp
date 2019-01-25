<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="label.registration_page"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login-form.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/3_buttons.css"/>

</head>
<body>
<c:import url="/WEB-INF/view/header/header.jsp"/>

<div class="registration">
    <c:if test="${param.error != null}">
        <p style='color:red'>
            <spring:message code="label.bad_password"/>
        </p>
    </c:if>
    <form method="post" action="<c:url value="/registration"/>">
        <div style="display: flex">
            <div>

                <label>
                    <spring:message code="label.user_name"/><br>
                    <input class="input_login" type="text" name="name" value="${param.get("name")}" required>
                </label><br>
                <label>
                    <spring:message code="label.user_surname"/><br>
                    <input class="input_login" type="text" name="surname" value="${param.get("surname")}" required>
                </label><br>
                <label>
                    <spring:message code="label.user_country"/><br>
                    <input class="input_login" type="text" name="country" value="${param.get("country")}" required>
                </label><br>
                <label>
                    <spring:message code="label.user_birthday"/><br>
                    <input class="input_login" type="date" name="birthday" value="${param.get("birthday")}" required>
                </label><br>
            </div>
            <div>

                <label>
                    <spring:message code="label.user_phone"/><br>
                    <input class="input_login" type="text" name="phone" value="${param.get("phone")}" required>
                </label><br>
                <label>
                    <spring:message code="label.user_email"/><br>
                    <input class="input_login" type="text" name="email" value="${param.get("email")}" required>
                </label><br>
                <label>
                    <spring:message code="label.user_password"/><br>
                    <input class="input_login" type="password" name="password" required>
                </label><br>
                <label>
                    <spring:message code="label.repeat_password"/><br>
                    <input class="input_login" type="password" name="passwordValidation" required>
                </label>

            </div>
        </div>
        <input type="submit" value="OK" name="Ok">
        <input type="reset">
    </form>


</div>
</body>
</html>
