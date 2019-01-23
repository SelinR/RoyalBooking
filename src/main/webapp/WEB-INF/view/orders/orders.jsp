<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta content="text/html" charset="UTF-8"/>
    <title><spring:message code="label.orders"/></title>
</head>

<body bgcolor="#fff8dc">
<h2><spring:message code="label.orders"/></h2><br/>
<c:forEach var="order" items="${requestScope.orders}">
<ul>
    <li><spring:message code="label.id"/>: <c:out value="${order.id}"/></li>
    <li><spring:message code="label.order_status"/>: <c:out value="${order.status}"/></li>
    <li><spring:message code="label.order_entry_date"/>: <c:out value="${order.entryDate}"/></li>
    <li><spring:message code="label.order_leave_date"/>: <c:out value="${order.leaveDate}"/></li>
    <li><spring:message code="label.order_total_price"/>: <c:out value="${order.totalPrice}"/></li>
    <li><spring:message code="label.order_user_id"/>: <c:out value="${order.userID}"/></li>
    <li><spring:message code="label.order_booked_room_id"/>: <c:out value="${order.bookedRoomID}"/></li>
</ul>
<hr/>
</c:forEach>

<h2><spring:message code="label.order_creation"/></h2><br/>
<form method="post" action="<c:url value="/orders"/>">

    <p><spring:message code="label.order_choose_room_id_to_book"/></p>
    <p><label>
        <select size=1 name="bookedRoomID">
        <c:forEach var="room" items="${requestScope.rooms}">
            <option><c:out value="${room.id}"/></option>
        </c:forEach>
    </select>
    </label></p>

    <label>
        <spring:message code="label.order_entry_date"/> <br>
        <input type="date" id="entryDate" name="entryDate"
               value="<c:out value="${requestScope.minDate}"/>"
               min="<c:out value="${requestScope.minDate}"/>"
               max="<c:out value="${requestScope.maxDate}"/>">
    </label><br>

    <label>
        <spring:message code="label.order_leave_date"/> <br>
        <input type="date" name="leaveDate"
               value="<c:out value="${requestScope.minDate}"/>"
               min="<c:out value="${requestScope.minDate}"/>"
               max="<c:out value="${requestScope.maxDate}"/>">
    </label><br>

    <label>
        <spring:message code="label.id"/><br>
        <input type="number" name="userID">
    </label><br>

    <p><input type="submit" value="Create"></p>

</form>
</html>
