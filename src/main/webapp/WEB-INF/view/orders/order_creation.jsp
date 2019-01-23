<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title><spring:message code="label.order_creation"/></title>

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
            <div class="greenButton"><spring:message code="label.registration"/></div>
        </a>
        <pre>   </pre>
        <a href="<c:url value="/login"/>">
            <div class="blueButton"><spring:message code="label.login"/></div>
        </a>
    </div>
</div>


<div>
    <h2 align="center"><spring:message code="label.room_booking"/></h2>
</div>
<div>
    <div align="left">
        <table id="datatable" class="tg">
            <thead>
            <tr>
                <th width="100"><spring:message code="label.parameter"/></th>
                <th width="500"><spring:message code="label.info"/></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><spring:message code="label.id"/></td>
                <td><c:out value="${roomToBook.id}"/></td>
            </tr>
            <tr>
                <td><spring:message code="label.room_type"/></td>
                <td><c:out value="${roomToBook.roomType}"/></td>

            </tr>
            <tr>
                <td><spring:message code="label.room_beds_amount"/></td>
                <td><c:out value="${roomToBook.bedsAmount}"/></td>
            </tr>
            <tr>
                <td><spring:message code="label.room_area"/></td>
                <td><c:out value="${roomToBook.area}"/></td>
            </tr>
            <tr>
                <td><spring:message code="label.room_daily_cost"/></td>
                <td><c:out value="${roomToBook.dailyCost}"/></td>
            </tr>
            <tr>
                <td><spring:message code="label.info"/></td>
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
            <spring:message code="label.calendar_with_free_dats"/>
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
    <h3><spring:message code="label.choose_dates"/></h3>
</div>

<form method="post" action="<c:url value="/order_confirm"/>">

    <label>
        <spring:message code="label.order_entry_date"/> <br>
        <input type="date" id="entryDate" name="entryDate"
               value="<c:out value="${requestScope.minDate}"/>"
               min="<c:out value="${requestScope.minDate}"/>"
               max="<c:out value="${requestScope.maxDate}"/>">
    </label><br>

    <label>
        <spring:message code="label.order_leave_date"/> <br>
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
