<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Order creation</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/2_buttons.css"/>
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


<div>
    <h2>Room booking</h2>
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
                <td>Room id</td>
                <td><c:out value="${roomToBook.id}"/></td>
            </tr>
            <tr>
                <td>Room type</td>
                <td><c:out value="${roomToBook.roomType}"/></td>

            </tr>
            <tr>
                <td>Beds amount</td>
                <td><c:out value="${roomToBook.bedsAmount}"/></td>
            </tr>
            <tr>
                <td>Area</td>
                <td><c:out value="${roomToBook.area}"/></td>
            </tr>
            <tr>
                <td>Daily Cost</td>
                <td><c:out value="${roomToBook.dailyCost}"/></td>
            </tr>
            <tr>
                <td>Additional info</td>
                <td><c:out value="${roomToBook.additionalInfo}"/></td>
            </tr>

            </tbody>
        </table>
    </div>
    <div align="right"> placeholder for image</div>
</div>

<div>
    <h4>Choose date</h4>
</div>
<form method="post" action="<c:url value="/order_confirm"/>">
<div>
    <label>
        Entry Date <br>
        <input type="date" name="entryDate"
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
</div>

<label>
    User ID<br>
    <input type="number" name="userID">
</label><br>
<label>
    <div align="center">
        <input type="hidden" name="totalPrice" value="${roomToBook.dailyCost}">
        <input type="hidden" name="status" value="${roomToBook.roomType}">
        <input type="hidden" name="bookedRoomId" value="${roomToBook.id}">
        <button type="submit" value="book">Create order</button>
    </div>
</label>

<%--<p><input type="submit" value="Create"></p>--%>

</form>

<h2>Order Creation</h2><br/>
<form method="post" action="<c:url value="/order_confirm"/>">

    <p>Choose Room Id to book</p>
    <p><label>
        <input type="hidden"  name="bookedRoomID" value="${roomToBook.id}">
        <input type="hidden"  name="totalPrice" value="50">
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

</body>

</html>
