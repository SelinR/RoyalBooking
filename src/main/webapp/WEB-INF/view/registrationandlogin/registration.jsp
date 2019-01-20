<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                Bad password verification.
            </p>
        </c:if>
        <form method="post" action="<c:url value="/registration"/>">
            <label>
                Name<br>
                <input type="text" name="name" required>
            </label><br>
            <label>
                Surname<br>
                <input type="text" name="surname" required>
            </label><br>
            <label>
                Country<br>
                <input type="text" name="country" required>
            </label><br>
            <label>
                Birthday<br>
                <input type="date" name="birthday" required>
            </label><br>
            <label>
                Phone<br>
                <input type="text" name="phone" required>
            </label><br>
            <label>
                Email<br>
                <input type="text" name="email" required>
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
