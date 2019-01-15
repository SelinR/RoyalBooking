<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rooms</title>
</head>

<body>

<h2>Все комнаты</h2><br />
<c:forEach var="room" items="${requestScope.rooms}">
    <ul>
        <li>Id: <c:out value="${room.id}"/></li>
        <li>RoomType: <c:out value="${room.roomType}"/></li>
        <li>Beds: <c:out value="${room.bedsAmount}"/></li>
        <li>Area: <c:out value="${room.area}"/></li>
        <li>Daily cost: <c:out value="${room.dailyCost}"/></li>
        <li>Additional info: <c:out value="${room.additionalInfo}"/></li>
    </ul>
    <hr />
</c:forEach>

<h2>Создание новой комнаты</h2><br />
<form method="post" action="<c:url value="/view/rooms"/>">
    <label>
        Room type<br>
        <input type="radio" name="roomType" value="BASIC" checked> Basic
        <input type="radio" name="roomType" value = "FAMILY"> Family
        <input type="radio" name="roomType" value = "LUXURY"> Luxury
        <input type="radio" name="roomType" value="PENTHOUSE"> Penthouse
    </label><br>
    <label>
        Beds <br>
        <input type="number" name="bedsAmount" step="1" required>
    </label><br>
    <label>
        Area <br>
        <input type="number" name="area" step="0.1" required=>
    </label><br>
    <label>
        Daily cost <br>
        <input type="number" name="dailyCost" step="0.1" required>
    </label><br>
    <label>
        Additional info <br>
        <input type="text" name="additionalInfo">
    </label><br>

    <input type="submit" value="Ok" name="Ok"><br>
</form>

</body>
</html>
