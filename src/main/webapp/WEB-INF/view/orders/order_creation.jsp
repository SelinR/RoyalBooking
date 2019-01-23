<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Order creation</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/3_buttons.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common_style.css"/>
    <link rel='stylesheet' href='https://fonts.googleapis.com/icon?family=Material+Icons'>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />

    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>

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
    <h2 align="center">Room booking</h2>
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

<div align="center">
    <div>
        <h4>
            Calendar with free dates
        </h4>
    </div>

    <div><input type="text" name="daterange" value="" />
        <script>
            $(function() {
                $('input[name="daterange"]').daterangepicker({
                    opens: 'left',
                    isInvalidDate: function(date) {
                        <c:forEach var="date" items="${list}">
                        if (date.format('YYYY-MM-DD') == '${date}') {
                            return true;
                        }
                        </c:forEach>
                    }

                });
            });
        </script>
    </div>

<div>
    <h3>Choose dates</h3>
</div>

<form method="post" action="<c:url value="/order_confirm"/>">

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

    <p><label>
        <input type="hidden"  name="bookedRoomID" value="${roomToBook.id}">
        <input type="hidden"  name="totalPrice" value="50">
    </label></p>

    <p><input type="submit" value="Create"></p>

</form>
</div>
</body>
</html>
