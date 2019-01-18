<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration page</title>
</head>
<body bgcolor="#fff8dc">
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
            <input type="text" name="birthday" required>
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
        <input type="submit" value="OK" name="Ok">
        </form>
</body>
</html>
