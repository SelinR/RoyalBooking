<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profile</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/3_buttons.css"/>
    <link rel='stylesheet' href='https://fonts.googleapis.com/icon?family=Material+Icons'>

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

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>

</head>
<body>
<div class="loginContainer" align="left">

    <div style="display: flex">
        <a href="<c:url value="/registration"/>">
            <div class="greenButton">Register</div>
        </a>
        <pre>   </pre>
        <a href="<c:url value="/login"/>">
            <div class="blueButton">Login</div>
        </a>
    </div>
</div>
<table>
    <tr>
        <td valign="top">
            <h2> Profile details </h2>
            <table id="datatable" class="tg">
                <tr>
                    <th width="140"></th>
                    <th width="200">Information</th>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><c:out value="${user.name}"/></td>
                </tr>
                <tr>
                    <td>Surname</td>
                    <td><c:out value="${user.surname}"/></td>
                </tr>
                <tr>
                    <td>Country</td>
                    <td><c:out value="${user.country}"/></td>
                </tr>
                <tr>
                    <td>Birthday</td>
                    <td><c:out value="${user.birthday}"/></td>
                <tr>
                    <td>Phone</td>
                    <td><c:out value="${user.phone}"/></td>
                <tr>
                    <td>Email</td>
                    <td><c:out value="${user.email}"/></td>
                </tr>
            </table>
            <br>
            <button onclick="location.href='profile/edit'">Edit</button>
        </td >
        <td width="100"/>
        <td valign="top">
            <h2>Your Orders</h2>
            <table class="tg">
                <tr>
                    <th width="60">Order ID</th>
                    <th width="60">Order status</th>
                    <th width="80">Entry date</th>
                    <th width="80">Leave date</th>
                    <th width="60">Total price</th>
                    <th width="60">Booked room ID</th>
                    <th width="60">Cancel</th>
                </tr>
                <c:forEach var="order" items="${requestScope.orders}">
                    <tr>
                        <td><c:out value="${order.id}"/></td>
                        <td><c:out value="${order.status}"/></td>
                        <td><c:out value="${order.entryDate}"/></td>
                        <td><c:out value="${order.leaveDate}"/></td>
                        <td><c:out value="${order.totalPrice}"/></td>
                        <td><a href="/room/${order.bookedRoomID}"><c:out value="${order.bookedRoomID}"/></a></td>
                        <td><button onclick="location.href='profile/cancel/${order.id}'">Cancel</button></td>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
</table>
</body>
</html>
