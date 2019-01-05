<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<h2>All orders</h2><br/>
<c:forEach var="order" items="${requestScope.orders}">
<ul>
    <li>Order Id: <c:out value="${order.id}"/></li>
    <li>Order Status: <c:out value="${order.status}"/></li>
    <li>Entry Date: <c:out value="${order.entryDate}"/></li>
    <li>Leave Date: <c:out value="${order.leaveDate}"/></li>
    <li>Total Price: <c:out value="${order.totalPrice}"/></li>
    <li>User Name: <c:out value="${order.user.name}"/></li>
    <li>User Surname: <c:out value="${order.user.surname}"/></li>
    <li>User email: <c:out value="${order.user.email}"/></li>
    <li>User id: <c:out value="${order.user.id}"/></li>
    <li>Booked Room Id: <c:out value="${order.bookedRoom.id}"/></li>
</ul>
<hr/>
</c:forEach>

<h2>Order Creation</h2><br/>
<form method="post" action="">

    <p>Choose Room Id to book</p>
    <p><label>
        <select size=1 name="roomId">
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
        Name<br>
        <input type="text" name="name">
    </label><br>

    <label>
        Surname<br>
        <input type="text" name="surname">
    </label><br>

    <label>
        Email<br>
        <input type="text" name="email">
    </label><br>

    <p><input type="submit" value="Create"></p>

</form>
</html>
