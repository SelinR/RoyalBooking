<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="label.order_confirm"/></title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/3_buttons.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common_style.css"/>
    <link rel='stylesheet' href='https://fonts.googleapis.com/icon?family=Material+Icons'>

</head>
<body>
<c:import url="/WEB-INF/view/header/header_without_i18n.jsp"/>
<span style="float: right">
        <a href="<c:url value="/order_confirm/${order.bookedRoomID}/${order.entryDate}/${order.leaveDate}?lang=en"/>">en</a>
        |
        <a href="<c:url value="/order_confirm/${order.bookedRoomID}/${order.entryDate}/${order.leaveDate}?lang=ru"/>">ru</a>
</span>

<div>
    <h2 align="center"><spring:message code="label.order_details"/></h2>
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
                <td><spring:message code="label.order_booked_room_id"/></td>
                <td><c:out value="${order.bookedRoomID}"/></td>
            </tr>
            <tr>
                <td><spring:message code="label.order_entry_date"/></td>
                <td><c:out value="${order.entryDate}"/></td>

            </tr>
            <tr>
                <td><spring:message code="label.order_leave_date"/></td>
                <td><c:out value="${order.leaveDate}"/></td>
            </tr>
            <tr>
                <td><spring:message code="label.order_total_price"/></td>
                <td><c:out value="${order.totalPrice}"/></td>
            </tr>


            </tbody>
        </table>
    </div>
</div>

<form method="post" action="<c:url value="/order_save"/>">
    <input type="hidden" name="bookedRoomID" value="${order.bookedRoomID}">
    <input type="hidden" name="totalPrice" value="${order.totalPrice}">
    <input type="hidden" name="entryDate" value="${order.entryDate}">
    <input type="hidden" name="leaveDate" value="${order.leaveDate}">
    <input type="hidden" name="userID" value="${order.userID}">
    <input type="submit" class="blueButton" value="<spring:message code="label.order_confirm"/>">
</form>

</body>
</html>
