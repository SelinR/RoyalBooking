<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Room page</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/3_buttons.css"/>
    <link rel='stylesheet' href='https://fonts.googleapis.com/icon?family=Material+Icons'>
</head>

<body>
<div class="loginContainer">
    <sec:authorize access="isAnonymous()">
        <%--2 buttons--%>
        <div style="display: flex">
            <a href="<c:url value="/registration"/>">
                <div class="greenButton" align="right">Register</div>
            </a>
            <pre>   </pre>
            <a href="<c:url value="/login"/>">
                <div class="blueButton" align="right">Login</div>
            </a>
        </div>
    </sec:authorize>

    <%-- And logout for authenticated users --%>
    <sec:authorize access="isAuthenticated()">
        <div style="display: flex">
            <a href="<c:url value="/logout"/>">
                <div class="redButton" align="right">Logout</div>
            </a>
        </div>
    </sec:authorize>
</div>

<h2>Room creation</h2><br/>
<form method="post" action="<c:url value="/admin/room_add"/>">
    <label>
        Room type<br>
        <input type="radio" name="roomType" value="BASIC" checked> Basic
        <input type="radio" name="roomType" value="FAMILY"> Family
        <input type="radio" name="roomType" value="LUXURY"> Luxury
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

    <input type="submit" value="Create"><br>
</form>
<pre>

</pre>
<form method="get" action="<c:url value="/admin/rooms_list"/>">
    <button>Back to Room list</button>
</form>
</body>

</body>
</html>
