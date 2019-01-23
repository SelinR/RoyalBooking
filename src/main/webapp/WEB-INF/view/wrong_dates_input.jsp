<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Wrong dates input</title>
</head>
<body>

<h3>Wrong dates input</h3>
<form method="get" action="<c:url value="/order_creation"/>">
    <input type="hidden" name="roomToBookId" value="${order.bookedRoomID}">
    <button>Back order creation</button>
</form>
</body>
</html>
