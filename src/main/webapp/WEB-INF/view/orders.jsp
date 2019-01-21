<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta content="text/html" charset="UTF-8"/>
    <title>Orders</title>
</head>

<body bgcolor="#fff8dc">
<h2>All orders</h2><br/>
<c:forEach var="order" items="${requestScope.orders}">
<ul>
    <li>Order Id: <c:out value="${order.id}"/></li>
    <li>Order Status: <c:out value="${order.status}"/></li>
    <li>Entry Date: <c:out value="${order.entryDate}"/></li>
    <li>Leave Date: <c:out value="${order.leaveDate}"/></li>
    <li>Total Price: <c:out value="${order.totalPrice}"/></li>
    <li>User id: <c:out value="${order.userID}"/></li>
    <li>Booked Room Id: <c:out value="${order.bookedRoomID}"/></li>
</ul>
<hr/>
</c:forEach>

<h2>Order Creation</h2><br/>
<form method="post" action="<c:url value="/orders"/>">

    <p>Choose Room Id to book</p>
    <p><label>
        <select size=1 name="bookedRoomID">
        <c:forEach var="room" items="${requestScope.rooms}">
            <option><c:out value="${room.id}"/></option>
        </c:forEach>
    </select>
    </label></p>

    <label>
        Entry Date <br>
        <input type="date" id="entryDate" name="entryDate"
               value="<c:out value="${requestScope.minDate}"/>"
               min="<c:out value="${requestScope.minDate}"/>"
               max="<c:out value="${requestScope.maxDate}"/>">
    </label><br>

    <label>
        Leave Date <br>
        <input type="date" name="leaveDate"
               value="<c:out value="${requestScope.minDate}"/>"
               min="<c:out value="${requestScope.minDate}"/>"
               max="<c:out value="${requestScope.maxDate}"/>">
    </label><br>

    <label>
        User ID<br>
        <input type="number" name="userID">
    </label><br>

    <p><input type="submit" value="Create"></p>

</form>
</html>
