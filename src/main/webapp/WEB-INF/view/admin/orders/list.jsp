<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
\<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.epam.royalbooking.enums.OrderStatus"%>
<html>
<head>
    <meta content="text/html" charset="UTF-8"/>
    <title>Orders</title>
    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }
    </style>
</head>

<body>
<h1>All orders</h1>
<table class="tg">
    <tr>
        <th width="60">Order ID</th>
        <th width="60">Order status</th>
        <th width="80">Entry date</th>
        <th width="80">Leave date</th>
        <th width="60">Total price</th>
        <th width="60">User ID</th>
        <th width="60">Booked room ID</th>
        <th width="60">Edit</th>
        <th width="60">Delete</th>
    </tr>
    <c:forEach var="order" items="${requestScope.orders}">
        <tr align="center">
            <td><c:out value="${order.id}"/></td>
            <td><c:out value="${order.status}"/></td>
            <td><c:out value="${order.entryDate}"/></td>
            <td><c:out value="${order.leaveDate}"/></td>
            <td><c:out value="${order.totalPrice}"/></td>
            <td><c:out value="${order.userID}"/></td>
            <td><c:out value="${order.bookedRoomID}"/></td>
            <td><a href="<c:url value='/admin/order/${order.id}'/>">Edit</a></td>
            <td><a href="<c:url value='/admin/orders/delete/${order.id}'/>">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<h2>Create Order</h2>

<c:url var="addAction" value="/admin/orders/add"/>

<form:form method="post" action="${addAction}" modelAttribute="order">
    <table class="tg">
        <tr>
            <td>Room ID</td>
            <td>
                <label>
                    <select size=1 name="bookedRoomID">
                        <c:forEach var="room" items="${requestScope.rooms}">
                            <option><c:out value="${room.id}"/></option>
                        </c:forEach>
                    </select>
                </label>
            </td>
        </tr>
        <tr>
            <td>Entry Date</td>
            <td>
                <label>
                    <input type="date" id="entryDate" name="entryDate"
                           value="<c:out value="${requestScope.minDate}"/>"
                           min="<c:out value="${requestScope.minDate}"/>"
                           max="<c:out value="${requestScope.maxDate}"/>">
                </label>
            </td>
        </tr>
        <tr>
            <td>Leave Date</td>
            <td>
                <label>
                    <input type="date" name="leaveDate"
                           value="<c:out value="${requestScope.minDate}"/>"
                           min="<c:out value="${requestScope.minDate}"/>"
                           max="<c:out value="${requestScope.maxDate}"/>">
                </label>
            </td>
        </tr>
        <tr>
            <td>User ID</td>
            <td>
                <label>
                    <select size=1 name="UserID">
                        <c:forEach var="user" items="${requestScope.users}">
                            <option><c:out value="${user.id}"/></option>
                        </c:forEach>
                    </select>
                </label>
            </td>
        </tr>
        <tr>
            <td>Order Status</td>
            <td>
                <label>
                    <form:select path="status" id="status">
                        <c:forEach var="statusValue" items="${OrderStatus.values()}">
                            <form:option value="${statusValue}">${statusValue}</form:option>
                        </c:forEach>
                    </form:select>
                </label>
            </td>
        </tr>
    </table>
    <br>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>