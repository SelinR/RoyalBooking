<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Room page</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/3_buttons.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/simple-tables-style.css"/>
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


<h2>Room info</h2>
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
                <td>Room id</td>
                <td><c:out value="${room.id}"/></td>
            </tr>
            <tr>
                <td>Room type</td>
                <td><c:out value="${room.roomType}"/></td>

            </tr>
            <tr>
                <td>Beds amount</td>
                <td><c:out value="${room.bedsAmount}"/></td>
            </tr>
            <tr>
                <td>Area</td>
                <td><c:out value="${room.area}"/></td>
            </tr>
            <tr>
                <td>Daily Cost</td>
                <td><c:out value="${room.dailyCost}"/></td>
            </tr>
            <tr>
                <td>Additional info</td>
                <td><c:out value="${room.additionalInfo}"/></td>
            </tr>


            </tbody>
        </table>
    </div>
    <div align="right"> placeholder for image</div>
</div>

<form method="post" action="<c:url value="/admin/room_update"/>">


    <label>
        Room type<br>
        <input type="radio" name="roomType" value="BASIC"
               <c:if test="${room.roomType.toString() == 'BASIC'}">checked</c:if> > Basic
        <input type="radio" name="roomType" value="FAMILY"
               <c:if test="${room.roomType.toString() == 'FAMILY'}">checked</c:if> > Family
        <input type="radio" name="roomType" value="LUXURY"
               <c:if test="${room.roomType.toString() == 'LUXURY'}">checked</c:if> > Luxury
        <input type="radio" name="roomType" value="PENTHOUSE"
               <c:if test="${room.roomType.toString() == 'PENTHOUSE'}">checked</c:if> > Penthouse

    </label><br>
    <label>
        Beds <br>
        <input type="number" name="bedsAmount" step="1" value="${room.bedsAmount}" required>
    </label><br>
    <label>
        Area <br>
        <input type="number" name="area" step="0.1" value="${room.area}" required=>
    </label><br>
    <label>
        Daily cost <br>
        <input type="number" name="dailyCost" step="0.1" value="${room.dailyCost}" required>
    </label><br>
    <label>
        Additional info <br>
        <input type="text" name="additionalInfo" value="${room.additionalInfo}">
    </label><br>
    <input type="hidden" name="id" value="${room.id}">
    <input type="submit" value="Save changes"><br>
</form>
<pre>

</pre>
<form method="get" action="<c:url value="/admin/rooms_list"/>">
    <button>Back to Room list</button>
</form>
</body>

</html>