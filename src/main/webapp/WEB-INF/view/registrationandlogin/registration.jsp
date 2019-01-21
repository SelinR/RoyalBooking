<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login-form.css"/>
</head>
<body>
    <div class="login">
        <c:if test="${param.error != null}">
            <p style='color:red'>
                Bad password verification or such email is exists.
            </p>
        </c:if>
        <form method="post" action="<c:url value="/registration"/>">
            <label>
                Name<br>
                <input type="text" name="name" value="${param.get("name")}" required>
            </label><br>
            <label>
                Surname<br>
                <input type="text" name="surname" value="${param.get("surname")}" required>
            </label><br>
            <label>
                Country<br>
                <input type="text" name="country" value="${param.get("country")}" required>
            </label><br>
            <label>
                Birthday<br>
                <input type="date" name="birthday" value="${param.get("birthday")}" required>
            </label><br>
            <label>
                Phone<br>
                <input type="text" name="phone" value="${param.get("phone")}" required>
            </label><br>
            <label>
                Email<br>
                <input type="text" name="email" value="${param.get("email")}" required>
            </label><br>
            <label>
                Password<br>
                <input type="password" name="password" required>
            </label>
            <label>
                Repeat password
                <input type="password" name="passwordValidation" required>
            </label>
            <br>
            <input type="submit" value="OK" name="Ok">
            <input type="reset">
        </form>
    </div>
</body>
</html>
