<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Room page</title>

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


<div align="center">
    <form method="GET" action="<c:url value="/room/${room.id}"/>">
        <button type="submit" value="book">Book room</button>
    </form>
</div>

</body>

</html>