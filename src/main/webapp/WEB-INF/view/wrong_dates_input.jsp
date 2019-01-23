<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="label.wrong_dates_input"/></title>
</head>
<body>
<c:import url="/WEB-INF/view/header/header.jsp"/>
<h3><spring:message code="label.wrong_dates_input"/></h3>
<form method="get" action="<c:url value="/order_creation"/>">
    <input type="hidden" name="roomToBookId" value="${order.bookedRoomID}">
    <button><spring:message code="label.back_to_booking"/></button>
</form>
</body>
</html>
