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
<c:import url="/WEB-INF/view/header/header.jsp"/>


<div>
    <h2 align="center"><spring:message code="label.room_booking"/></h2>
</div>
<div>
    <div align="center">
        <table id="datatable" class="tg">
            <thead>
            <tr>
                <th width="100"><spring:message code="label.parameter"/></th>
                <th width="250"><spring:message code="label.info"/></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><spring:message code="label.room_type"/></td>
                <td>
                    <c:if test="${room.roomType == 'BASIC'}">
                        <spring:message code="label.room_basic"/>
                    </c:if>
                    <c:if test="${room.roomType == 'FAMILY'}">
                        <spring:message code="label.room_family"/>
                    </c:if>
                    <c:if test="${room.roomType == 'LUXURY'}">
                        <spring:message code="label.room_luxury"/>
                    </c:if>
                    <c:if test="${room.roomType == 'PENTHOUSE'}">
                        <spring:message code="label.room_penthose"/>
                    </c:if>
                </td>

            </tr>
            <tr>
                <td><spring:message code="label.room_beds_amount"/></td>
                <td><c:out value="${room.bedsAmount}"/></td>
            </tr>
            <tr>
                <td><spring:message code="label.room_area"/></td>
                <td><c:out value="${room.area}"/></td>
            </tr>
            <tr>
                <td><spring:message code="label.room_daily_cost"/></td>
                <td><c:out value="${room.dailyCost}"/></td>
            </tr>
            <tr>
                <td><spring:message code="label.info"/></td>
                <td><c:out value="${room.additionalInfo}"/></td>
            </tr>

            </tbody>
        </table>
    </div>
</div>

<div align="center">
    <div>
        <h4>
            <c:if test="${param.error != null}">
                <p style='color:red'>
                    <spring:message code="label.bad_dates"/>
                </p>
            </c:if>
            <p><spring:message code="label.choose_dates"/></p>
        </h4>
    </div>

    <div>
        <form method="post" action="<c:url value="/order/confirm/${room.id}"/>">
            <label id="dates">
                <input type="text" name="dateRange" value="" />
                    <script>
                        $(function() {
                            $('input[name="dateRange"]').daterangepicker({
                                opens: 'left',
                                minDate: moment(),
                                isInvalidDate: function(date) {
                                    <c:forEach var="date" items="${list}">
                                        if (date.format('YYYY-MM-DD') === '${date}') {
                                            return true;
                                        }
                                    </c:forEach>
                                }
                            });
                        });
                    </script>
            </label>
                <input type="submit" class="blueButton" value="<spring:message code="label.create"/>">
        </form>
    </div>
</div>
</body>
</html>
