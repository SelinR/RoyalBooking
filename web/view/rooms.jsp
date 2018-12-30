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
<form method="post" action="">
    <label><input type="number" name="id"></label>id<br>
    <label><input type="text" name="roomType">Room type</label>Возраст<br>
    <label><input type="number" name="bedsAmount">Beds</label>Возраст<br>
    <label><input type="number" name="area">Area</label>Возраст<br>
    <label><input type="number" name="dailyCost">Daily cost</label>Возраст<br>
    <label><input type="text" name="additionalInfo">Additional info</label>Возраст<br>
    <input type="submit" value="Ok" name="Ok"><br>
</form>

</body>
</html>
