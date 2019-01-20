<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body bgcolor="#fff8dc">

<form method="post" action="<c:url value="/login"/>">
    <label>
        Email<br>
        <input type="email" name="username">
    </label><br>
    <label>
        Password<br>
        <input type="password" name="password">
    </label><br>
    <input type="submit" value="OK" name="Ok">
    <input type="reset" value="Reset">
</form>
</body>
</html>
