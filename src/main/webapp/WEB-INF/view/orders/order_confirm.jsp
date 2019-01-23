<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>OrderConfirmation</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/3_buttons.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common_style.css"/>
    <link rel='stylesheet' href='https://fonts.googleapis.com/icon?family=Material+Icons'>

</head>
<body>

<div>
    <h2 align="center">Order details</h2>
</div>
<div>
    <div align="left">
        <table id="datatable" class="tg">
            <thead>
            <tr>
                <th width="100">param</th>
                <th width="500">Info</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Booked room id</td>
                <td><c:out value="${order.bookedRoomID}"/></td>
            </tr>
            <tr>
                <td>Entry date</td>
                <td><c:out value="${order.entryDate}"/></td>

            </tr>
            <tr>
                <td>Leave date</td>
                <td><c:out value="${order.leaveDate}"/></td>
            </tr>
            <tr>
                <td>Total price</td>
                <td><c:out value="${order.totalPrice}"/></td>
            </tr>


            </tbody>
        </table>
    </div>
    <div align="right"> placeholder for image</div>
</div>

<form method="post" action="<c:url value="/order_save"/>">
    <input type="hidden" name="bookedRoomID" value="${order.bookedRoomID}">
    <input type="hidden" name="totalPrice" value="${order.totalPrice}">
    <input type="hidden" name="entryDate" value="${order.entryDate}">
    <input type="hidden" name="leaveDate" value="${order.leaveDate}">
    <input type="hidden" name="userID" value="${order.userID}">
    <input type="submit" value="Confirm order">
</form>

</body>
</html>
